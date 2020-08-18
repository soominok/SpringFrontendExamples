export default {
  filteredTodoItems (state) {
    console.log('filteredTodoItems')
    if (!state.filter) {
      return state.todoItems
    }

    if (state.filter === 'A') {
      return state.todoItems.filter(todoItem => {
        return todoItem.done === false
      })
    }

    if (state.filter === 'B') {
      return state.todoItems.filter(todoItem => {
        return todoItem.done === true
      })
    }
  },
  count (state, getters) {
    return Math.pow(state.count, getters.weight)
  },
  weight (state) {
    return state.weight
  },
  random (state) {
    return state.random
  },
  isAuthorized (state) {
    // 액세스 토큰이 없고,
    // 이중부정 -> myinfo가 참/거짓으로 나오게 하기 위해서 이중부정 사용.
    return state.accessToken.length > 0 && !!state.myinfo
  }
}
