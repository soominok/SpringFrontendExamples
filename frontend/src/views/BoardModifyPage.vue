<template>
  <div align="center">
  <!-- 실제 작업을 컴포넌트쪽으로 넘긴 것 -->
    <h2>Board Modification</h2>
    <!-- board를 bind 시켜서 낱개를 가져와야 함.
        그리고 @submit -->
    <board-modify-form v-if="board" :board="board" @submit="onSubmit"/>
    <!-- 만약에 board가 없다면 Loading...을 띄우기 -->
    <p v-else>Loading ...</p>
    <!-- board-modify-form 컴포넌트에 넘긴 것 -->
  </div>
</template>

<script>
import BoardModifyForm from '@/components/BoardModifyForm'
import { mapState, mapActions } from 'vuex'
import axios from 'axios'

export default {
  name: 'BoardModifyPage',
  components: {
    BoardModifyForm
  },
  props: {
    // boardNo는 router/index.js에서 params에 props로 전달되게 해놨음.
    // 자동으로 맵핑되게 되어 있는 것!!
    boardNo: {
      type: String,
      required: true
    }
  },
  computed: {
    ...mapState(['board'])
  },
  created () {
    console.log('BoardModifyPage created()')
    // 이 객체(페이지)가 생성이 될 때 board 하나를 boardNo로 물어와야 함.
    this.fetchBoard(this.boardNo)
      // catch가 발생한다면 정보 메세지를 보내기
      .catch(err => {
        alert(err.response.data.message)
        // 다시 원래 페이지로 되돌아 감.
        this.$router.back()
      })
  },
  methods: {
    onSubmit (payload) {
      const { title, content } = payload
      console.log('BoardModifyPage payload: ' + payload)
      // PutMappting한거 가져옴.
      // 조심해야 할 게 `` 써줘야 함.
      axios.put(`http://localhost:7777/boards/${this.boardNo}`, { title, content })
        // 처리정보를 res로 받아오기
        .then(res => {
          alert('Modify Success')
          console.log('res: ' + res.data)
          this.$router.push({
            name: 'BoardReadPage',
            // res.data에 boardNo를 toString으로 받아옴.
            params: { boardNo: res.data.boardNo.toString() }
          })
        })
        .catch(err => {
          alert(err.response.data.message)
        })
    },
    ...mapActions([
      // board(게시판) 하나를 fetch(가져오는 것) 하는 것.
      'fetchBoard'
    ])
  }
}
</script>
