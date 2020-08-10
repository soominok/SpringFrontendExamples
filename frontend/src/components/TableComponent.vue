<template>
  <table>
    <!-- 정보 전달을 하기 위한 vue 전용 component를 만들어 주기 위해서 tr-component를 생성.
     그냥 tr은 큐?밖에 못 만들기 때문!!
     Concave.vue에서 tableData를 받아왔기 때문에 가능
     v-model="propTurn"써서 양쪽 연결시켜 줌.
     rowDate와 row-data를 연결, index와 row-index 연결 -->
    <tr-component v-for="(rowData, index) in tableData"
        :key="index"
        :row-data="rowData"
        :row-index="index"
        :table-data="tableData"
        :turn="turn"
        :winner="winner"
        v-on:updateTurn="updateTurn"
        v-on:updateTableData="updateTableData"
        v-on:updateWinner="updateWinner"
        v-model="propTurn">
    </tr-component>
  </table>
</template>

<script>
import TrComponent from './TrComponent.vue'

export default {
  data () {
    return {
      propTurn: this.turn,
      propWin: this.winner
    }
  },
  components: {
    TrComponent
  },
  // 하위에서 상위로 연결시키는 것
  props: {
    tableData: Array,
    turn: String,
    winner: String
  },
  updated: function () {
    console.log('tableData: ' + this.tableData +
                ', turn: ' + this.turn +
                ', winner: ' + this.winner)
  },
  methods: {
    updateTurn: function (val) {
      console.log('TableComponent updateTurn: ' + val)
      this.propTurn = val
      this.$emit('updateTurn', this.propTurn)
    },
    updateTableData: function () {
      this.$emit('updateTableData')
    },
    updateWinner: function (val) {
      this.propWin = val
      this.$emit('updateWinner', this.propWin)
    }
  }
}
</script>

<style>
table {
  border-collapse: collapse;
}
td {
  border: 1px solid black;
  width: 80px;
  height: 80px;
  text-align: center;
}
</style>
