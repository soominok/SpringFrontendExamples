<template>
  <div align="center">
    <h2>Board Register</h2>
    <board-register-form @submit="onSubmit"/>
  </div>
</template>

<script>
import BoardRegisterForm from '@/components/BoardRegisterForm'
import axios from 'axios'

export default {
  name: 'BoardRegisterPage',
  components: {
    BoardRegisterForm
  },
  methods: {
    onSubmit (payload) {
      console.log('BoardRegisterPage onSubmit()')
      const { title, writer, content } = payload
      // 이 정보는 챙겨야하니까 post로 보내기
      // requestMapping이 boards에 걸려있으니까 port번호 7777 다음에 써줌.
      // VueBoardController를 보면 @RequestMapping("/boards")라고 써져있는 부분을 가져오는 것.
      // { } 여기 순서 주의하기!! - BoardRegisterForm.vue에 써준 순서와 같아야 함.
      axios.post('http://localhost:7777/boards', { title, writer, content })
        .then(res => {
          console.log(res)
          // 성공했다는 알림 띄움.
          alert('Register Success')
          this.$router.push({
            name: 'BoardReadPage',
            params: { boardNo: res.data.boardNo.toString() }
          })
        })
        // 에러 발생했을 때 에러메세지 띄워줌
        .catch(err => {
          alert(err.response.data.message)
        })
    }
  }
}
</script>
