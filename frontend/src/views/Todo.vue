<template>
  <div calss="todo">
    <todo-header></todo-header>
    <!-- 아래와 같이 v-on:click이 아니라 addTodo로 이름을 트리거 할 수 있음! -->
    <todo-input v-on:addTodo="onAddTodo"></todo-input>
    <todo-filter></todo-filter>
    <todo-list v-on:removeTodo="onRemoveTodo"
              v-on:updatedTodo="onEditTodo"
              v-on:toggleTodoStatue="onToggleTodoStatus">
    </todo-list>
    <todo-footer v-on:removeAll="onClearAll"></todo-footer>
    <b>random: {{ this.$store.getters.random }}</b><br>
    <input type="button" @click="randomNumber()" value="random"/><br>
  </div>
</template>

<script>
import TodoHeader from '../components/TodoHeader.vue'
import TodoInput from '../components/TodoInput.vue'
import TodoList from '../components/TodoList.vue'
import TodoFooter from '../components/TodoFooter.vue'
import TodoFilter from '../components/TodoFilter.vue'
// import store from '../store'
// state와 actions를 자동으로 mapping해주는 역할.
import { mapActions } from 'vuex'

// components로 받아왔기 때문에 연결이 가는 한 것.
export default {
  name: 'Todo',
  components: {
    'todo-header': TodoHeader,
    'todo-input': TodoInput,
    'todo-list': TodoList,
    'todo-footer': TodoFooter,
    'todo-filter': TodoFilter
  },
  // data () {
  //   return {
  //     todoItems: []
  //   }
  // },
  methods: {
  //  ...mapMutations([
  //    SET_EDITING_ID,
  //    RESET_EDITING_ID
  //  ]),
    ...mapActions([
      'clearAll',
      'addTodo',
      'removeTodo',
      'generateRandomNumber',
      'save',
      'restore',
      'editTodo',
      'toggleTodoStatus'
    ]),
    onEditTodo (content, id) {
      this.editTodo({ id, content })
      this.save()
    },
    onClearAll () {
      this.clearAll()
      this.save()
    },
    // F5를 눌러도 내용이 지워지지 않음.
    onAddTodo (content) {
      const todoItem = { content }

      this.addTodo(todoItem)
      this.save()
    },
    onToggleTodoStatus (id) {
      this.toggleTodoStatus(id)
      this.save()
    },
    onRemoveTodo (todoItem, idx) {
      this.removeTodo(idx)
      this.save()
    },
    randomNumber () {
      this.generateRandomNumber()
    },
    // 생성 시점에 복원한다.
    created () {
      this.restore()
    }
    // clearAll () {
    //   this.todoItems = []
    //   store.dispatch('clearAll')
    // },
    // addTodo (todoItem) {
    //   this.todoItems.push(todoItem)
    //   store.dispatch('addTodo', todoItem)
    // },
    // removeTodo (todoItem, idx) {
    //   this.todoItems.splice(idx, 1)
    //   store.dispatch('removeTodo', idx)
    // }
  },
  // 데이터량이 너무 많거나 할 경우에는 여기서 하면 안됨.
  // todoItem에 들어가 듯 100개 안될 정도는 무난하게 처리 가능.
  computed: {
  //  ...mapState([
  //    'todoItems'
  //  ])
    // todoItems () {
    //   return store.state.todoItems
    // }
  }
}
</script>

<style>
  body {
    text-align: center;
    background-color: #C8D7FF;
  }
</style>
//#FFFACD
