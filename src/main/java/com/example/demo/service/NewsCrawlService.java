package com.example.demo.service;

import com.example.demo.entity.ClickedNews;
import com.example.demo.entity.HomeNews;
import com.example.demo.entity.News;
import com.example.demo.repository.ClickedNewsRepository;
import com.example.demo.repository.HomeNewsRepository;
import com.example.demo.repository.NewsRepository;
import lombok.extern.java.Log;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Lazy
@Log
public class NewsCrawlService {
    // 크롤링을 할 준비를 해야 함.
    @Autowired
    NewsRepository newsRepository;

    @Autowired
    HomeNewsRepository homeNewsRepository;

    @Autowired
    ClickedNewsRepository clickedNewsRepository;

    private Document document;

    // URL Connecting
    // 크롤링 하기 위해서 가장 필요한 것이 URL 커넥팅하는 것!
    public Document connectUrl(String url) {

        // 이 부분이 가장 중요함!!!
        log.info("connectUrl(): " + url);

        try {
            Connection.Response homepage = Jsoup.connect(url).method(Connection.Method.GET)
                    // Firefox, Crome, Safari
                    .userAgent("Mozila/5.0 (X11; Linux x86_64; rv:10.0) Gecko/20100101 " +
                            "Firefox/10.0 AppleWebKit/537.36 (KHTML, like Gecko) " +
                            "Chrome/51.0.2704.103 Safari/537.36")
                    // 이걸 베이스로 실행하겠습니다.
                    .execute();

            document = homepage.parse();
        } catch (Exception e) {
            System.out.println("Error in mainCrawler");
        }
        return document;
    }

    // ---------------크롤링할 준비 됨!--------------------------

    public List<News> newsFindAll() {
        log.info("newsFindAll()");

        return newsRepository.findAll();
    }

    public List<HomeNews> homeNewsFindAll() {
        log.info("homeNewsFindAll()");

        return homeNewsRepository.findAll();
    }

    // crawlingHome부터 생성
    public void crawlingHome() {
        log.info("crawlingHome()");

        homeNewsRepository.deleteAll();
        document = connectUrl("https://news.daum.net/");

        // 땡겨올 HTML 태그를 가져와야 함.
        // 사이트 들어가서 F11 누르고, inspect로 들어가서 HTML 태그를 바로 찾아줌.
        Elements total = document.select("strong.tit_thumb>a.link_txt");

        Elements image = document.select("div.item_issue>a.link_thumb>img.thumb_g");

        // 일단 없으니까 null
        HomeNews homeNews = null;

        for (int i = 0; i < total.size(); i++) {
            homeNews = new HomeNews();
            homeNews.setHomeNewsNo(String.valueOf(i + 1));
            // 크롤링을 통해서 정보를 얻어서 text로 뿌림
            homeNews.setTitle(total.get(i).text());
            // 링크를 태워서 가져오는 것.
            homeNews.setAddress(total.get(i).attr("href"));
            homeNews.setImage(image.get(i).attr("src"));
            homeNewsRepository.save(homeNews);

        }
    }

    // category 파악할 수 있게 함.
    public void mainCrawler(String category) {
        log.info("mainCrawler(): " + category);

        document = connectUrl("http://news.daum.net/" + category);
        newsRepository.deleteAll();

        // 마지막에는 a관련 태그들이 들어와야함.
        // 이런식으로 트리형식으로 써주면 됨!
        daumNews(document.select("div.item_mainnews>div.cont_thumb>strong.tit_thumb>a"), category);
        daumNews(document.select("ul.item_mainnews>li>div.cont_thumb>strong.tit_thumb>a"), category);
        daumNews(document.select("strong.tit_mainnews>a"), category);
        daumNews(document.select("ul.list_issue>li>a.link_txt"), category);
    }

    public void daumNews(Elements elements, String category) {
        log.info("daumNews(): elements - " + elements + ", category - " + category);

        News news = null;

        for (int i = 0; i < elements.size(); i++) {
            news = new News();

            news.setNewsNo(String.valueOf(newsRepository.findAll().size() + 1));
            news.setAddress(elements.get(i).attr("href"));
            news.setCategory(category);
            // 취향 분석을 하려면 제목이 있어야 함.
            news.setTitle(elements.get(i).text());

            // JPA를 통해서 news를 저장 함!
            newsRepository.save(news);
        }
    }

    public ClickedNews crawlingOne(String newsNo) {
        log.info("crawlingOne(): " + newsNo);

        News news = newsRepository.findByNewsNo(newsNo);

        ClickedNews clickedNews = new ClickedNews();

        clickedNews.setTitle(news.getTitle());
        clickedNews.setCategory(news.getCategory());
        clickedNews.setAddress(news.getAddress());
        // 전체 No와 동일하지 않을 수 있음. (관심있는 정보들이 다르니까)
        clickedNews.setClickedNewsNo(String.valueOf(clickedNewsRepository.findAll().size() + 1));

        // 클릭한 기사에 대한 정보를 얻으려는 것이니까!
        // 실제 정보를 보여줘야 하니까!
        document = connectUrl(clickedNews.getAddress());

        // 어떤 정보를 얻을지는 다시 웹사이트에 들어가서 inspact 해서 span 부분을 확인해야 함.
        // 어떤 정보를 가져올지 확인해야하니까 span으로 해야 함.
        clickedNews.setDate(document.select("span.num_date").text());
        // 본문 전체니까 다시 웹사이트 가서 inspact 확인해서 div article_view와 같은 부분을 가서 figure은 그림이니까 그 나머지!
        clickedNews.setContents(document.select("div.article_view>section>:not(figure)").text());

        // 클릭한 정보를 JPA를 통해서 저장
        clickedNewsRepository.save(clickedNews);

        // 클릭한 정보를 return
        return clickedNews;
    }
}
