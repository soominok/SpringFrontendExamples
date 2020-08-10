<template>
  <div>
    <h3 class="monospace">Todo List</h3>
    <ul>
      <todo-item v-for="todoItem in todoItems"
          v-bind:key="todoItem.id"
          v-bind:todoItem="todoItem"
          v-bind:editingId="editingId"
          v-on:removeTodo="onRemoveTodo"
          v-on:updateTodo="onUpdateTodo"
          v-on:setEditingId="SET_EDITING_ID"
          v-on:resetEditingId="RESET_EDITING_ID"
          v-on:toggleTodoStatus="onToggleTodoStatus"/>
    </ul>
  </div>
</template>

<script>
import TodoItem from './TodoItem.vue'
import { mapState, mapMutations, mapGetters } from 'vuex'
import { SET_EDITING_ID, RESET_EDITING_ID } from '../store/mutation-types'
export default {
  components: {
    'todo-item': TodoItem
  },
  // 단순계산할 때 사용.
  computed: {
    todoItems () {
      console.log('todoItems : ' + this.filteredTodoItems)
      return this.filteredTodoItems
    },
    ...mapState([
      'editingId'
    ]),
    // 값을 가져올 때는 Getters를 쓰고, 실행할 때 Actions를 씀!
    ...mapGetters([
      'filteredTodoItems'
    ])
  },
  methods: {
    ...mapMutations([
      SET_EDITING_ID,
      RESET_EDITING_ID
    ]),
    onRemoveTodo (id) {
      this.$emit('removeTodo', id)
    },
    onUpdateTodo (content, id) {
      this.$emit('updateTodo', id)
    },
    onToggleTodoStatus (id) {
      this.$emit('toggleTodoStatus', id)
    }
  }
}

</script>

<style scoped>
  div {
    background-color: #6699FF
  }
  .monospace {
    font-family: "Lcida Console", Courier, monospace;
    text-decoration: underline;
  }
</style>
