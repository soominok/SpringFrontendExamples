<template>
  <div id="board">
    <h2>Board List</h2>
    <!-- 내용 추가하는 것이 필요함.
         내용 등록이 필요하기 때문에 BoardRegisterPage로 써줌-->
    <router-link :to="{ name: 'BoardRegisterPage' }">
      Create New Board
    </router-link>
    <!-- board list가 보여야 하기 때문에 -->
    <board-list/>
  </div>
</template>

<script>
import BoardList from '@/components/BoardList'

export default {
  name: 'BoardListPage',
  components: {
    BoardList
  }
}
</script>
