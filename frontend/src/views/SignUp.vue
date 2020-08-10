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
      <h2>회원가입을 해주세요.</h2>
      <div id="app">
        <p>ID : <input v-model="id" autofocus placeholder="ID를 생성해주세요."></p>
        <p>PW : <input v-model="pw" placeholder="PW를 생성해주세요."></p>
        <p>NickName: <input v-model="nickname" placeholder="닉네임을 생성해주세요."></p>
        <button v-on:click="makesignup">만들기</button>&nbsp;&nbsp;&nbsp;
        <button v-on:click="cancel">취소</button>
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
      makeid: 'ID를 생성해주세요.',
      makepw: 'PW를 생성해주세요.',
      makenick: '닉네임을 생성해주세요.'
    }
  }
}
