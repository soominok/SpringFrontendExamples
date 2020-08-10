<template>
  <div>
    <h3>Todo Input</h3>
    <!-- newTodoItem과 연결되도록 해줌.
    key가 enter 눌리면 addTodo가 실행되도록 함. -->
    <input type="text" v-model="newTodoItem"
      v-on:keypress.enter="addTodo">
    <button v-on:click="addTodo">Add</button>
  </div>
</template>

<script>

export default {
  data () {
    return {
      newTodoItem: ''
    }
  },
  methods: {
    addTodo () {
    // trim() 공백 제거하는 부분
    // 공백 아닐 경우에 addTodo가 실행 됨.
      if (this.newTodoItem !== '') {
        var value = this.newTodoItem && this.newTodoItem.trim()
        console.log('addTodo: ' + value)
        this.$emit('addTodo', value)
        this.clearInput()
      }
    },
    clearInput () {
      this.newTodoItem = ''
    }
  }
}

</script>

// css를 보면 스타일링을 좀 더 할 수 있다.
<style scoped>
  div {
    background-color: #33CCCC
  }
</style>
