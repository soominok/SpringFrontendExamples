<template>
<!--  todoItem에 대한 입력과 toggleTodo의 입력을 구분해서 쓸 수 있음. -->
  <div class="todo">
    <!-- isEditing이 거짓이라면 다음과 같은 실행을 할 것.
    v-else를 통해서 그렇지 않다면의 실행을 정해줌 -->
    <li>
      <span v-if="!isEditing" v-on:dblclick="handleDblClick">
        {{ todoItem.content }}
      </span>
      <input v-else type="text" ref="content"
          v-bind:value="todoItem.content"
          v-on:blur="handleBlur"
          v-on:keydown.enter="updateTodo"/>
       <!-- checked가 todoItem.done과 bind 되어 있음. -->
      <input type="checkbox"
          v-bind:checked="todoItem.done"
          v-on:change="toggleTodoStatus()">
      <button v-on:click="removeTodo">Delete</button>
    </li>
  </div>
</template>
  <!-- props:
   컴포넌트 통신할 때 사용하는 것이 props! 컴포넌트끼리 데이터 전달할 수 있는 기능 (properties)
   bind해서
   TodoList에서 bind해서 보내면서 TodoItem에서 props를 쓰면서 여기서 맵핑할 수 있음. (bind를 걸고 props로!!)
   상위컴포넌트 : TodoItem, 하위컴포넌트: TodoList ????? (상위 컴포넌트가 하위 컴포넌트로 ??)
   props에서 받기 위해서 <TodoList.vue>(하위 컴포넌트)에서 v-bind로 먼저 해주는 것 (probs를 v-bind로 연결한 것)
   TodoItem에 있는 props가 TodoList 때문에 연결된다고 생각해야 함. (TodoList가 TodoItem으로 연결된다고 생각하면 안됨!!!)

   updateTodo:
   이벤트로 날아와야 하니까 e.target으로 const content = e.target.value.trim()  -->
<script>
export default {
  name: 'TodoItem',

  props: {
    todoItem: {
      type: Object
    },
    editingId: {
      type: Number
    }
  },
  computed: {
    isEditing () {
      return this.todoItem.id === this.editingId
    }
  },
  methods: {
    removeTodo () {
      const id = this.todoItem.id
      this.$emit('removeTodo', id)
    },
    handleDblClick () {
      const { id } = this.todoItem

      this.$emit('setEditingId', id)

      this.$nextTick(() => {
        this.$refs.content.focus()
      })
    },
    handleBlur () {
      this.$emit('resetEditingId')
    },
    UpdateTodo (e) {
      const id = this.todoItem.id
      const content = e.target.value.trim()

      if (content.length <= 0) {
        return false
      }

      this.$emit('UpdateTodo', content, id)

      this.$refs.content.blur()
    },
    toggleTodoStatus () {
      const id = this.todoItem.id

      this.$emit('toggleTodoStatus', id)
    }
  }
}
</script>
