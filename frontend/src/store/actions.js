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
  FETCH_BOARD,
  SET_ACCESS_TOKEN,
  SET_MY_INFO,
  /* eslint-disable no-unused-vars */
  DESTROY_ACCESS_TOKEN,
  DESTROY_MY_INFO,
  /* Crawl */
  FINDONE,
  FINDHOME,
  CRAWLSTART
} from './mutation-types'
// mutation-tyes에서 사용하겠다고 설명.
// 여기서 mutation들을 호출하는 것.

import axios from 'axios'
import router from '../router'

export default {
  // 양이 많아서 async(비동기 처리) 해줘야 함.
  async crawlFind ({ commit }, category) {
    axios.get('http://localhost:7777/' + `${category}`)
      .then(({ data }) => {
        commit('CRAWLSTART', data)
        if (window.location.pathname !== '/CrawlCategory') {
          router.push('/CrawlCategory')
        }
      })
  },
  async crawlFindOne ({ commit }, newsNo) {
    axios.get('http://localhost:7777/news/' + `${newsNo}`)
      .then(({ data }) => {
        console.log('/news/newsNo res: ' + data)
        commit('FINDONE', data)
        router.push('/CrawlCategory/news')
      })
  },
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
  },
  login ({ commit }, payload) {
    console.log('Actions login()')
    // security/SecurityConstants를 확인하면 api 어디로 보내는지 알 수 있음.
    // 인증 요청 들어온 것을 Post로 보냈음.
    // 요청이
    return axios.post(`http://localhost:7777/api/authenticate?username=${payload.userid}&password=${payload.password}`, {
      username: payload.userid,
      password: payload.password
    }).then(res => {
      console.log('Actions after post')
      const { authorization } = res.headers
      const accessToken = authorization.substring(7)
      console.log('Access Token: ', accessToken)

      commit(SET_ACCESS_TOKEN, accessToken)

      return axios.get('http://localhost:7777/users/myinfo')
    // 잘 요청이 오면 data로 보내주세요.
    }).then(res => {
      console.log('After Get Auth Info')
      commit(SET_MY_INFO, res.data)
    })
  },
  loginByToken ({ commit }, token) {
    commit(SET_ACCESS_TOKEN, token)
    return axios.get('http://localhost:7777/users/myinfo')
      .then(res => {
        commit(SET_MY_INFO, res.data)
      })
  },
  logout ({ commit }) {
    commit(DESTROY_MY_INFO)
    commit(DESTROY_ACCESS_TOKEN)
  }
}
// actions: {
//  generateRandomNumber ({ commit }) {
//    console.log(commit)
//
//    axios.get('http://localhost:7777/random')
//      .then((res) => {
//        commit('successGenRandNum',
//          parseInt(res.data.randNumber))
//      })
//      .catch((res) => {
//        commit('failGenRandNum', res)
//      })
//  },
//  addTodo (context, payload) {
//    context.commit('ADD_TODO', payload)
//  },
//  removeTodo (context, payload) {
//    context.commit('REMOVE_TODO', payload)
//  },
//  clearAll (context, payload) {
//    context.commit('CLEAR_ALL')
//  }
