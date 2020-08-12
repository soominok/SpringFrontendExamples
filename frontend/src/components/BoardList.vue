<template>
  <div>
    <h3>Board List View</h3>
    <table border="1">
      <tr>
        <th align="center" width="80">No</th>
        <th align="center" width="320">Title</th>
        <th align="center" width="100">Writer</th>
        <th align="center" width="180">Registration Date</th>
      </tr>

      <!-- 여기서 boards는 RequestMapping으로 받은 boards인가 -->
      <!-- boards의 타입을 Array로 받아서 여기서 사용 -->
      <!-- 만약 boards가 빈값이라면 List ist empty가 뜨도록 함 -->
      <tr v-if="!boards || (Array.isArray(boards) && boards.length === 0)">
        <td colspan="4">
          List is empty
        </td>
      </tr>

       <!-- 이제는 boardNo를 1로 받는 것이 아니라 board에 있는 boardNo로 받는 것! -->
       <tr v-else v-for="board in boards" :key="board.boardNo">
         <td align="center">{{ board.boardNo }}</td>
         <td align="left">
            <!-- 게시판을 볼때 글을 누르면 이동할 수 있도록 link를 하나 걸어주기 -->
            <!-- 정보는 toString으로. params가 props로 들어가는 것. -->
            <!-- BoardReadPage.vue에 props에 자동으로 들어가는 것. 안적어줬는데도 자동으로 생성이 되는 이유가
                router/index.js에 props:를 따로 지정해줘서 그런 것!! -->
            <!-- 자동으로 props에 들어가게 만들어 주는 역할 -->
            <!-- params: 페이지를 이동하면서 뭔가 데이터를 같이 가져가고 싶을 때 -->
            <router-link :to="{ name: 'BoardReadPage',
                              params: { boardNo: board.boardNo.toString() } }">

              {{ board.title }}
            </router-link>
         </td>
         <!-- 나머지는 작성자 기입 -->
         <td align="right">{{ board.writer }}</td>
         <!-- 등록일을 기입 -->
         <td align="center">{{ board.regDate }}</td>
       </tr>
    </table>
  </div>
</template>

<script>
export default {
  name: 'BoardList',
  // porps도 boards를 받아오기 때문에 BoardListPage.vue에서 boards를 받아와야 화면에 출력 됨
  props: {
    boards: {
      type: Array
    }
  }
}
</script>
