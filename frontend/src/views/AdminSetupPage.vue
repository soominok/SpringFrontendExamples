<template>
  <div align="center">
    <h2>Register First Adminstartor</h2>
    <!-- AdminSetupForm에서 받은 submit -->
    <admin-setup-form @submit="onSubmit"/>
  </div>
</template>

<script>
// Spring에 요청을 보내기 위한 axios
import axios from 'axios'
// admin-setup-form을 사용하기 위한 AdminSetupForm
import AdminSetupForm from '@/components/AdminSetupForm.vue'

export default {
  name: 'AdminSetupPage',
  components: {
    AdminSetupForm
  },
  methods: {
    onSubmit (payload) {
    // userId, userName, userPw는 spring jpa에서 만든 것임!
      console.log('payload: ' + payload.userId +
                  ', ' + payload.userName +
                  ', ' + payload.userPw)
      const { userId, userName, userPw } = payload
      // MemberController에서 Admin관련한 것이 post이고, Url이 /users/setup이니까!
      axios.post('http://localhost:7777/users/setup',
        { userId, userName, userPw })
        .then(res => {
          alert('Register Success')
          // 로그인한 상태가 아니니까 기존 Home 페이지가 보일 것.
          this.$router.push({
            name: 'Home'
          })
        })
        .catch(err => {
          alert(err.response.data.message)
        })
    }
  }
}
</script>
