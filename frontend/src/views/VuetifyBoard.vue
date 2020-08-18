<template>
  <div align="center">
    <h2>Vuetify Board List</h2>
    <!-- 그냥 보여주기만 할거니까 -->
    <vuetify-board-list-form :list-array="pageArray"/>
  </div>
</template>

<script>
import axios from 'axios'
import VuetifyBoardListForm from '@/components/VuetifyBoardListForm.vue'
export default {
  name: 'VuetifyBoard',
  components: {
    VuetifyBoardListForm
  },
  data () {
    return {
      pageArray: []
    }
  },
  created () {
    axios.get('http://sample.bmaster.kro.kr/contacts')
      .then(res => {
        // 잘 찍혔는지 확인해보기!
        console.log(res)
        this.pageArray = res.data.contacts
      })
      .catch(err => {
        console.log(err)
      })
  }
}
</script>
