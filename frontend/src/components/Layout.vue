<template>
  <v-app id="inspire">
    <!-- 액션바 같은 것을 만들 수 있음 -->
    <v-app-bar
      app
      clipped-right
      color="blue"
      dark
    >
    <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
    <v-toolbar-title><div @click="home">Crwal Project</div></v-toolbar-title>
    <v-spacer></v-spacer>
    <slot name="menubar"></slot>
    </v-app-bar>
    <!-- navigation을 넣으면 조금 더 이쁘게 만들 수 있을 것! -->
    <v-navigation-drawer
      v-model="drawer"
      app
    >
      <!-- dense : list를 균일하게 보여줌. -->
      <v-list dense>
        <v-list-item @click.stop="left = !left">
          <v-list-item-action>
            <div>>>></div>
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

    <v-container>
      <v-content id="content">
        <slot name="content" class="font">
        </slot>
      </v-content>
    </v-container>

    <v-footer
      app
      color="blue"
      class="white--text"
    >
      <v-spacer></v-spacer>
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
