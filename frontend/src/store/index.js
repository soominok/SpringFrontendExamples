import Vue from 'vue'
import Vuex from 'vuex'
// import axios from 'axios'

import state from './states'
import actions from './actions'
import mutations from './mutations'
import getters from './getters'

Vue.use(Vuex)

export default new Vuex.Store({
  state,
  actions,
  mutations,
  getters
//  state: {
//    count: 0,
//    weight: 2,
//    random: 0,
//    todoItems: []
//  },
//  // mutations쪽에서 값을 보장해줌.
//  mutations: {
//    increment (state) {
//      state.count++
//    },
//    decrement (state) {
//      state.count--
//    },
//    successGenRandNum (state, payload) {
//      state.random = payload
//    },
//    failGenRandNum () {
//      alert('망함')
//    },
//    ADD_TODO (state, todoItems) {
//      state.todoItems.push(todoItems)
//    },
//    REMOVE_TODO (state, idx) {
//      state.todoItems.splice(idx, 1)
//    },
//    CLEAR_ALL (state) {
//      state.todoItems = []
//    }
//  },
//  getters: {
//    count (state, getters) {
//      return Math.pow(state.count, getters.weight)
//    },
//    weight (state) {
//      return state.weight
//    },
//    random (state) {
//      return state.random
//    }
//  },
//  // dispatch는 무조건 actions으로 간다.
//  // actions를 써주려면 store.dispatch를 써줘야 함!!!
//  // 그래서 addTodo, removeTodo, clearAll를 써주려면 Todo.vue에 store.dispatch('')를 써주기!
//  actions: {
//    generateRandomNumber ({ commit }) {
//      console.log(commit)
//
//      axios.get('http://localhost:7777/random')
//        .then((res) => {
//          commit('successGenRandNum',
//            parseInt(res.data.randNumber))
//        })
//        .catch((res) => {
//          commit('failGenRandNum', res)
//        })
//    },
//    // distpatch하도록 하는 것.
//    addTodo (context, payload) {
//      context.commit('ADD_TODO', payload)
//    },
//    removeTodo (context, payload) {
//      context.commit('REMOVE_TODO', payload)
//    },
//    clearAll (context, payload) {
//      context.commit('CLEAR_ALL')
//    }
//  },
//  modules: {
//  }
})
