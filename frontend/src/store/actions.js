import {
  successGenRandNum,
  failGenRandNum,
  ADD_TODO,
  REMOVE_TODO,
  CLEAR_ALL,
  RESTORE,
  EDIT_TODO,
  TOGGLE_TODO_STATUS,
  FETCH_BOARD_LIST,
  FETCH_BOARD
} from './mutation-types'
// mutation-tyes에서 사용하겠다고 설명.

import axios from 'axios'

export default {
  fetchBoardList ({ commit }) {
    // VueBoardController에서 RequestMapping("boards")로 연결되는 것
    return axios.get('http://localhost:7777/boards')
      .then(res => {
        // list를 뽑아서 동작하게 해줌.
        commit(FETCH_BOARD_LIST, res.data)
      })
  },
  fetchBoard ({ commit }, boardNo) {
    // fetchBoard가 동작함을 알려줌.
    console.log('fetchBoard ' + commit + ', boardNo = ' + boardNo)
    return axios.get(`http://localhost:7777/boards/${boardNo}`)
      .then(res => {
        console.log('fetchBoard - res: ' + res.data)
        // FETCH_BOARD만 fetch해 올 수 있도록 함
        commit(FETCH_BOARD, res.data)
      })
  },
  editTodo ({ commit }, payload) {
    commit(EDIT_TODO, payload)
  },
  toggleTodoStatus ({ commit }, id) {
    commit(TOGGLE_TODO_STATUS, id)
  },
  save ({ state }) {
    const data = {
      todoItems: state.todoItems,
      nextTodoId: state.nextTodoId
    }
    // state.todoItems의 데이터 정보를 'todo-app-data'속성으로 저장한 것.
    // 문제는 DB를 연동하는 것은 아님!! -> DB 연동되면 Spring의 service, repository가 연결되어야 함. (복잡해짐)
    localStorage.setItem('todo-app-data', JSON.stringify(data))
  },
  restore ({ commit }) {
    const data = localStorage.getItem('todo-app-data')

    if (data) {
      console.log('data: ' + JSON.parse(data))
      commit(RESTORE, JSON.parse(data))
    }
  },
  generateRandomNumber ({ commit }) {
    console.log(commit)

    axios.get('http://localhost:7777/random')
      .then((res) => {
        commit(successGenRandNum,
          parseInt(res.data.randNumber))
      })
      .catch((res) => {
        commit(failGenRandNum, res)
      })
  },
  // distpatch하도록 하는 것.
  addTodo (context, payload) {
    // payload 이름 지정은 상관 없음.
    context.commit(ADD_TODO, payload)
  },
  removeTodo (context, payload) {
    context.commit(REMOVE_TODO, payload)
  },
  clearAll (context, payload) {
    context.commit(CLEAR_ALL)
  }
}
