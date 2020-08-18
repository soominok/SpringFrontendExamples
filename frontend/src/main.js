import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import cookies from 'vue-cookies'
import vuex from 'vuex'
import vuetify from './plugins/vuetify'

Vue.config.productionTip = false

Vue.use(cookies)
// 여차하면 vuex로 직접 접근해야할 수 있으니까 추가하기.
Vue.use(vuex)

function init () {
  const savedToken = null
  // const savedToken = cookies.get('accessToken')

  if (savedToken) {
    return store.dispatch('loginByToken', savedToken)
  } else {
    // Promise는 비동기 처리 (동기처리는 블락킹을 함 = 동작을 멈춤)
    // 혹시 문제가 있어도 멈추지 않고 동작함. (안정적으로 처리하기 위한 것이 Promise)
    // 스레드, 컨텍스트 스위칭, 뮤텍스, 세마포어, 비동기 처리, 동기 처리를 잘 이해하고 있기!!
    return Promise.resolve()
  }
}

// init을 호출하면서 객체를 불러오겠다는 의미
init().then(() => {
  new Vue({
    router,
    store,
    vuetify,
    render: h => h(App)
  }).$mount('#app')
})
