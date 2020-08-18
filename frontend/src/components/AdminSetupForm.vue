<template>
  <!-- submit이 받아오는 것을 받아치는 것!? -->
  <form @submit.prevent="submit">
    <table>
      <tr>
        <td>Admin Id</td>
        <!-- SetupPage로 가려면 emit써야하는데, 그럼 methods 작성해주고 해야 해서 그냥 양방향으로 설정 -->
        <td><input type="text" v-model="userId"></td>
      </tr>
      <tr>
        <td>Password</td>
        <td><input type="password" v-model="userPw"></td>
      </tr>
      <tr>
        <td>Admin Name</td>
        <td><input type="text" v-model="userName"></td>
      </tr>
    </table>

    <div>
      <button type="submit">Register</button>
    </div>
  </form>
</template>

<script>
export default {
  name: 'AdminSetupForm',
  data () {
    return {
      userId: '',
      userName: '',
      userPw: ''
    }
  },
  methods: {
    submit () {
      console.log('this: ' + this.userId +
                  ', ' + this.userName +
                  ', ' + this.userPw)
      const { userId, userName, userPw } = this
      // 여기에 모아서 한번에 보내는 것.
      this.$emit('submit', { userId, userName, userPw })
    }
  }
}
</script>
