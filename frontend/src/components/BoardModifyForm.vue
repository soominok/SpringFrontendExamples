<template>
  <div>
    <h3>Board Modfied Form</h3>
    <form @submit.prevent="onSubmit">
      <table>
        <tr>
          <td>No</td>
          <!-- 수정이 불가능하게 했음 -->
          <td><input type="text" :value="board.boardNo" disabled></td>
        </tr>
        <tr>
          <td>Registration Date</td>
          <!-- 수정이 불가능하게 했음 -->
          <td><input type="text" :value="board.regDate" disabled></td>
        </tr>
        <tr>
          <td>Title</td>
          <!-- 새로운 정보를 받는 거니까 "title"로 적어줘야 함. -->
          <!-- board.title이라고 하면 원래 가지고 있던 정보를 보내게 되니까 주의해야함!!
               이럴 경우에는 그냥 "title" 로 적어줘야 함 -->
          <td><input type="text" v-model="title"></td>
        </tr>
        <tr>
          <td>Writer</td>
          <!-- 작성자는 글을 한번 적으면 변경 불가해야 함. -->
          <!-- bind(단방향)로 해주는게 좋음. ?????????? -->
          <td><input type="text" :value="board.writer" disabled></td>
        </tr>
        <tr>
          <td>Content</td>
          <td><textarea v-model="content" rows="5"></textarea></td>
        </tr>
      </table>

      <div>
        <button type="submit">Modify</button>
        <!-- 이걸 주는 이유는 params에 자동으로 값 줘서 props로 가도록 하게 하려고 -->
        <router-link :to="{ name: 'BoardReadPage', params: { boardNo: board.boardNo.toString() } }">
          Cancel
        </router-link>
      </div>
    </form>

    <!-- 누르면 수정이 완료되게 되는 것 -->
    <!-- <button type="submit">Modification</button> -->
    <!-- 누르면 되돌아 가는 것 -->
    <!-- <router-link :to="{ name: 'BoardReadPage', params: { boardNo: 1} }">
      Cancel
    </router-link> -->
  </div>
</template>

<script>
export default {
  name: 'BoardModfiyForm',
  props: {
    board: {
      type: Object,
      required: true
    }
  },
  data () {
    return {
      title: '',
      content: ''
    }
  },
  methods: {
    onSubmit () {
      const { title, content } = this
      this.$emit('submit', { title, content })
    }
  },
  created () {
    // 내용과 content를 가져오는 것
    this.title = this.board.title
    this.content = this.board.content
  }
}
</script>
