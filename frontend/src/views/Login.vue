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
    <h2>로그인을 해주세요.</h2>
    <div id="app">
      <p>ID : <input v-model="id" autofocus placeholder="ID를 입력해주세요."></p>
      <p>PW : <input v-model="pw" placeholder="PW를 입력해주세요."></p>
      <button v-on:click="loginsuccess">로그인</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <button v-on:click="createId">회원가입</button>
    </div>
  </div>
</template>

<script>
import Vue from 'vue'
/* eslint-disable no-unused-vars */
import store from '../store'
import cookies from 'vue-cookies'

Vue.use(cookies)

export default {
  data: function () {
    return {
    }
  },
  methods: {
    createId: function () {
      this.$store.dispatch('createIdPw')
    }
  }
}
