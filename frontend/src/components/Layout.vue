<template>
  <v-app id="inspire">
    <!-- 액션바 같은 것을 만들 수 있음 (맨 위에 있는!)
         역할이 위에 사회, 경제, IT 등을 표시하는 역할을 함.
         dark : 테마를 약간 옵션 주는 것 (살짝 어둡게) / dark를 빼니까 삼선이 검정으로 바뀜.
                자동으로 글씨를 흰색으로 바꿔 줌.
                사회, 정치, 경제, 국제, 문화, IT는 default값을 줘서 안 바뀐 것!-->
    <v-app-bar
      app
      clipped-right
      color="blue"
      dark
    >
    <!-- 홈으로 이동, 카테고리별 분류 보기 등으로 뜨는 부분의 역할!
         v-navigation-drawer를 서포트해주는 역할 -->
    <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
    <!-- Crwal Project를 표시하는 역할 (홈페이지 왼쪽 위 글씨) -->
    <v-toolbar-title><div @click="home">Crwal Project</div></v-toolbar-title>
    <!-- slot name="menubar"를 서포트해줌!
         #menubar는 CrawlCategory.vue에 있음. 이 정보들을 여기에 적용하겠다는 것!
         v-spacer가 있으니까 간격을 가지고 정보가 보이는 것! -->
    <v-spacer></v-spacer>
    <slot name="menubar"></slot>
    </v-app-bar>
    <!-- navigation을 넣으면 조금 더 이쁘게 만들 수 있을 것!
         list와 icon을 만드는 것 -->
    <v-navigation-drawer
      v-model="drawer"
      app
    >
      <!-- dense : list를 균일하게 보여줌. -->
      <v-list dense>
        <v-list-item @click.stop="left = !left">
          <v-list-item-action>
            <v-icon>mdi-exit-to-app</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title @click="category">홈으로 이동</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>

      <v-list dense>
        <v-list-item @click.stop="left = !left">
          <v-list-item-action>
            <v-icon>mdi-exit-to-app</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title @click="category">카테고리별 분류 보기</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>

      <v-list dense>
        <v-list-item @click.stop="left = !left">
          <v-list-item-action>
            <div>>>></div>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title @click="suggest">추천 뉴스 보기</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>

      <v-list dense>
        <v-list-item @click.stop="left = !left">
          <v-list-item-action>
            <v-icon>mdi-exit-to-app</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title @click="home">저장된 뉴스 보기</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

  <!-- 가운데 배치 또는 수평적 배치 가능.
       중앙 배치를 하면서도 수평적인 구조를 가질 수 있게 서포트하는 역할 (Layout 역할임!)
       CrawlCategory.vue를 보면 #content 부분이 있음. 여기로 배치 되는 것. (Layout이 얘를 감싸고 있으니까 maunbar, content를 이곳으로 전달할 수 있는 것) -->
    <v-container>
      <v-content id="content">
        <slot name="content" class="font">
        </slot>
      </v-content>
    </v-container>

    <!-- 하단에 꼬리부분 장식하는 역할 -->
    <v-footer
      app
      color="blue"
      class="white--text"
    >
      <v-spacer></v-spacer>
      <!-- ® 무늬가 들어가는 것이 &copy;인 것! -->
      <span>&copy; Crawl Project</span>
    </v-footer>
  </v-app>
</template>

<script>
import router from '../router'

export default {
  // return 대신에 => 를 쓴 것!
  data: () => ({
    drawer: false,
    left: false
  }),
  methods: {
    home () {
      // location.path가 router이면 /로 보내고,
      (window.location.pathname !== '/') ? router.push('/') : router.go(0)
    },
    category () {
      (window.location.pathname !== '/CrawlCategory') ? router.push('/CrawlCategory') : router.go(0)
    },
    suggest () {
      (window.location.pathname !== '/suggestednews/list') ? router.push('/suggestednews/list') : router.go(0)
    },
    savednews () {
      (window.location.pathname !== '/savednews/list') ? router.push('/savednews/list') : router.go(0)
    }
  }
}
</script>
