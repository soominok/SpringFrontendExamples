<template>
  <div id="board">
    <h2>Board List</h2>
    <!-- 내용 추가하는 것이 필요함.
         내용 등록이 필요하기 때문에 BoardRegisterPage로 써줌-->
    <router-link :to="{ name: 'BoardRegisterPage' }">
      Create New Board
    </router-link>
    <!-- board list가 보여야 하기 때문에 -->
    <!-- 질문~!!! :boards="boards"??? Spring 연결?? DB 연동하기 위해서?? -->
    <board-list :boards="boards"/>
  </div>
</template>

<script>
import BoardList from '@/components/BoardList'
import { mapState, mapActions } from 'vuex'

export default {
  name: 'BoardListPage',
  components: {
    BoardList
  },
  computed: {
    ...mapState(['boards'])
  },
  mounted () {
    this.fetchBoardList()
  },
  methods: {
    ...mapActions(['fetchBoardList'])
  }
}
</script>
