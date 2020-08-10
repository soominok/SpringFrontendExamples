<template>
  <div class="home">
    <div id="header">
      <router-link :to="{ name: 'Home' }"
          class="nav-link"
          active-class="active">
        Home
      </router-link>
      <router-link :to="{ name: 'About' }"
          class="nav-link"
          active-class="active">
        About Us
      </router-link>
      <router-link :to="{ name: 'Test' }"
          class="nav-link"
          active-class="active">
        Test
      </router-link>
    </div>
    <h2>This is an Test Page</h2>
    <div id="app">
      <p v-if="seen">You can see it!</p>
      <p v-bind:key=todo v-for="todo in todos">
        {{ todo.text }}
      </p>
      <p>{{ message }}</p>
      <button v-on:click="reverseMsg">Reverse Message</button>
      <p>{{ message }}</p>
      <input v-model="message"><br>
      <button @click="incCnt">{{ cnt }}</button><br>
      <!-- border-collapse는 테두리와 셀 사이 간격 삭제 -->
      <p>count 기반 순회</p>
      <table border="1" style="border-collapse:collapse;">
        <tr v-bind:key=idx v-for="idx in count">
          <td>{{ msg }}</td>
          <td>{{ idx }}</td>
        </tr>
      </table>

      <!-- list는 function이기 때문에 ()가 필요함 -->
      <p>list() 기반 순회</p>
      <table border="1" style="border-collapse:collapse;">
        <tr v-bind:key=idx v-for="idx in list()">
          <td>{{ msg }}</td>
          <td>{{ idx }}</td>
        </tr>
      </table>

      <p>(key, value) 기반 순회</p>
      <table border="1" style="border-collapse:collapse;">
        <tr v-bind:key="(value, key)" v-for="(value, key) in data">
          <td>{{ value }}</td>
          <td>{{ key }}</td>
        </tr>
      </table>

      <!-- this.$store가 결국 Vuex를 사용하겠단 의미 -->
      <!-- spring에서 만든 random을 Vue에서 받아서 화면에 출력 -->
      <b>count: {{ this.$store.state.count }}</b><br>
      <b>count^2: {{ this.$store.getters.count }}</b><br>
      <b>weight: {{ this.$store.getters.weight }}</b><br>
      <input type="button" @click="increment()" value="inc"/>
      <input type="button" @click="decrement()" value="dec"/><br>
      <b>random: {{ this.$store.getters.random }}</b><br>
      <input type="button" @click="randomNumber()" value="random"/><br>

      <global-component v-bind:initial-counter="counter">
      </global-component>

      <local-component v-bind:num="value"></local-component>
      <button v-on:click="plus">Click</button><br>
    </div>
  </div>
</template>

<script>
import Vue from 'vue'
/* eslint-disable no-unused-vars */
import store from '../store'
import cookies from 'vue-cookies'
import GlobalComponent from '../components/GlobalComponent.vue'
import LocalComponent from '../components/LocalComponent.vue'

Vue.component(GlobalComponent.name, GlobalComponent)

Vue.use(cookies)

export default {
  components: {
    'local-component': LocalComponent
  },
  data: function () {
    return {
      counter: 3,
      value: 1,
      msg: 'Test',
      count: 7,
      list: function () {
        var list = []
        for (var i = 1; i < this.count; i += 2) {
          list.push(i)
        }
        return list
      },
      message: 'Test Page',
      seen: true,
      todos: [
        { text: 'JavaScript' },
        { text: 'Golang' },
        { text: 'C++' }
      ],
      cnt: 0,
      data: {
        bird: 'raven',
        animal: 'tiger',
        fish: 'tuna',
        proj: 'cnn',
        major: 'EE'
      }
    }
  },

  methods: {
    reverseMsg: function () {
      this.message = this.message.split('').reverse().join('')
    },
    incCnt: function () {
      this.cnt++
    },

    increment: function () {
      // commit은 무조건 내부에 있는 것을 감.
      this.$store.commit('increment')
      this.$cookies.set('value', this.$store.state.count)
    },
    decrement: function () {
      this.$store.commit('decrement')
      this.$cookies.set('value', this.$store.state.count)
    },
    randomNumber: function () {
      // dispatch는 무조건 action으로 간다. (해당 내용의 실행을 보장한다. -> 동기 처리 - 바로바로 반영가능한 것)
      // commit은 비동기 처리가 가능하다. (실행했을 때 나오든 말든 뿌리고 간다.)
      // 스레드 동기 vs. 비동기 개념 다시 확인하기!
      // 서버에다가 랜덤 넘버 요청한 것. -> store.index으로 감.
      this.$store.dispatch('generateRandomNumber')
    },
    plus: function () {
      this.value++
    }
  },
  created: function () {
    this.$store.state.count = this.$cookies.get('value')
  }
}
</script>
