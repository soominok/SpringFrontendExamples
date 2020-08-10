<template>
  <div class="concave">
    <div>{{ turn }}님의 턴입니다.</div>
    <!-- v-bind 생략하고 :table-data 이런식으로 적어도 됨.
         table-component는 view 객체를 만들어야 함. -->
    <table-component :table-data="tableData"
      v-bind:turn="turn"
      v-bind:winner="winner"
      v-on:updateTurn="updateTurn"
      v-on:updateTableData="updateTableData"
      v-on:updateWinner="updateWinner">
    </table-component>
    <div v-if="winner">{{ winner }}님의 승리!</div>
  </div>
</template>

<script>
import TableComponent from '../components/TableComponent.vue'

export default {
  components: {
    TableComponent
  },
  // 가장 하위에 있는 데이터
  data () {
    return {
      turn: 'O',
      winner: '',
      tableData: [
        ['', '', ''],
        ['', '', ''],
        ['', '', '']
      ]
    }
  },
  updated: function () {
    console.log('tableData: ' + this.tableData +
                ', turn: ' + this.turn +
                ', winner: ' + this.winner)
  },
  methods: {
    // ~~님 turn입니다. 를 표시해 주기 위함.
    updateTurn: function (val) {
      console.log('Concave updateTurn: ' + val)
      this.turn = val
    },
    // 게임 이겼을 때 테이블 초기화시키는 작업.
    updateTableData: function () {
      this.tableData = [
        ['', '', ''],
        ['', '', ''],
        ['', '', '']
      ]
    },
    updateWinner: function (val) {
      this.winner = val
    }
  }
}
</script>
