<template>
  <div>
    <h3>Todo Footer</h3>
    <button v-on:click="clearTodo">Delete All</button>
  </div>
</template>

<script>
export default {
  methods: {
    clearTodo () {
    // $emit은 store 쪽으로 보내는 것. (아직 안 만들어서 연결은 안됨!)
      console.log('clearTodo')
      this.$emit('removeAll')
    }
  }
}
</script>

<style scoped>
  div {
    background-color: #99CCFF
  }
</style>
