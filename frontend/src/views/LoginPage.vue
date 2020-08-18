<template>
  <div align="center">
    <h2>Login</h2>
    <login-form @submit="onSubmit"/>
  </div>
</template>

<script>
import LoginForm from '@/components/LoginForm.vue'
import { mapActions } from 'vuex'

export default {
  name: 'LoginPage',
  components: {
    LoginForm
  },
  methods: {
    onSubmit (payload) {
      console.log('loginPage onSubmit()')
      // 여기 login에서는 액세스 토큰을 받아와야 함.
      this.login(payload).then(res => {
        alert('Login Success')
        this.$router.push({ name: 'Home' })
      }).catch(err => {
        // alert(err.response.data.message)
        alert('Something Fail: ', err)
      })
    },
    ...mapActions(['login'])
  }
}
</script>
