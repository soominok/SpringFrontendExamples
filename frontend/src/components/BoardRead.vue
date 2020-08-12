<template>
  <div>
    <h3>Board Detailed View</h3>
    <table>
      <tr>
        <td>No</td>
        <!-- 읽기전용 -->
        <td><input type="text" :value="board.boardNo" readonly></td>
      </tr>
      <tr>
        <td>Registration Date</td>
        <td><input type="text" :value="board.regDate" readonly></td>
      </tr>
      <tr>
        <td>Title</td>
        <td><input type="text" v-model="board.title" readonly></td>
      </tr>
      <tr>
        <td>Writer</td>
        <td><input type="text" v-model="board.writer" readonly></td>
      </tr>
      <tr>
        <td>Content</td>
        <td><textarea v-model="board.content" rows="5" readonly></textarea></td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  name: 'BoardRead',
  // 자동으로 연결되니까 props가 붙을 수 있는 것. (router/index.js에 props로 연결해줘서!!)
  props: {
    board: {
      type: Object,
      required: true
    }
  }
}
</script>
