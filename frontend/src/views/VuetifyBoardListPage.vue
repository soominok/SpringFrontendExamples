<template>
  <div align="center">
    <h2>Vuetify Real Board List</h2>
    <!-- Vue와 React의 장점은 재활용이 가능함!
         이미 컴포넌트 만들어놓은거 쓸 수 있음!!
         BoardListPage.vue꺼를!! 그럼, 이제 pageArray만 만들어주면 됨! -->
    <router-link :to="{ name: 'BoardRegisterPage' }">
      Create New Board
    </router-link>
    <!-- list-array는 vuetify-board-list-page-form에 전달되는 것 -->
    <vuetify-board-list-page-form :list-array="pageArray"/>
  </div>
</template>

<script>
import axios from 'axios'
import VuetifyBoardListPageForm from '@/components/VuetifyBoardListPageForm.vue'

export default {
  name: 'VuetifyBoardListPage',
  components: {
    VuetifyBoardListPageForm
  },
  data () {
    return {
      pageArray: []
    }
  },
  // 빠르게 하기 위해서 여기에 그냥 axios 해버리기!
  // BoardListPage.vue => actions.js => axios.get 부분 가져오기
  // 원래는 actions.js에 적어줘야 함!!! 그냥 빠르게 끝내기 위해서 여기에 적기!
  // 게시판 글을 요청함. -> 요청한 것을 pageArray에 전달함.
  // actions, mutations, states 등으로 나눠서 적어주기!
  created () {
    axios.get('http://localhost:7777/boards')
      .then(res => {
        console.log(res)
        this.pageArray = res.data
      })
      .catch(err => {
        console.log(err)
      })
  }
}
</script>
