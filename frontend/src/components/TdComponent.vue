// 사용자가 오목 판을 클릭했을 때 거기에 표시해주는 역할
<template>
  <td @click="onClickTd">{{ cellData }}</td>
</template>

<script>
export default {
  data () {
    return {
      game: {
        // TrComponent.vue에서 전달 받은 애들
        propTurn: this.turn,
        propWin: this.winner
      }
    }
  },
  props: {
    // GlobalComponent ===> global-component
    cellData: String,
    rowIndex: Number,
    cellIndex: Number,
    tableData: Array,
    turn: String,
    winner: String
  },
  methods: {
    onClickTd () {
      // cellData(정보가 있으면)가 있으면 그냥 바로 return
      if (this.cellData) return
      // cellIndex와 rowIndex 기반으로 tableData에 채워지는 것.
      // 즉 set에 의해서 오목 판에서 찍히는 것.
      this.$set(this.tableData[this.rowIndex], this.cellIndex, this.turn)

      // let은 변수 역할 (win이라는 변수를 만들었다!)
      let win = false

      // 오목판에 대한 판정 (이겼는지)
      // rowIndex가 같다는 것은 행이 고정된 것. -> 뒤 쪽 0,1,2은 가로줄
      if (this.tableData[this.rowIndex][0] === this.turn &&
          this.tableData[this.rowIndex][1] === this.turn &&
          this.tableData[this.rowIndex][2] === this.turn) {
        win = true
      }
      // cellIndex이 고정된 상태에서 0,1,2는 세로줄
      if (this.tableData[0][this.cellIndex] === this.turn &&
          this.tableData[1][this.cellIndex] === this.turn &&
          this.tableData[2][this.cellIndex] === this.turn) {
        win = true
      }

      // 대각선일 때
      if (this.tableData[0][0] === this.turn &&
          this.tableData[1][1] === this.turn &&
          this.tableData[2][2] === this.turn) {
        win = true
      }

      // 거꾸로 된 대각선
      if (this.tableData[0][2] === this.turn &&
          this.tableData[1][1] === this.turn &&
          this.tableData[2][0] === this.turn) {
        win = true
      }

      // 현재 turn에서 끝난 사람은 win

      // *** 중요!!!
      // emit은 하위에 데이터 전달하는 역할!!! (이벤트 전달 => 상위에서 하위로 데이터를 보내려할 때) (td에서 tr로 보내는 것이 emit)
      // 하위에서 상위로 데이터를 보내려고 할 때는 bind 사용!! (tr에서 td로 보내는 것이 bind)

      // updateWinner가 전달되는 것.
      if (win) {
        this.game.propWin = this.turn
        this.$emit('updateWinner', this.game.propWin)
        // X가 먼저 시작하는 걸로 바꾸려면 밑에 3항 연산자를 바꿔주면 됨.
        this.$emit('updateTurn', 'O')
        this.$emit('updateTableData')
      } else {
        let all = true

        // 모든 셀들이 체크가 되었는가? (체크가 다 되었는데 승부가 안났을 수도 있기 때문에!)
        // 각각의 row를 돌면서 각 셀들이 전부 체크가 되었는지 안되었는지 보겠다는 것.
        this.tableData.forEach(row => {
          row.forEach(cell => {
            // 하나라도 없는게(비어) 있으면 false (대기)
            if (!cell) {
              all = false
            }
          })
        })
        if (all) {
          this.propWin = ''
          this.$emit('updateTurn', 'O')
          this.$emit('updateTableData')
        } else {
          this.game.propTurn = this.turn === 'O' ? 'X' : 'O'
          this.$emit('updateTurn', this.game.propTurn)
        }
      }
    }
  },
  mounted: function () {
    console.log('tableData: ' + this.tableData +
                ', turn: ' + this.turn +
                ', winner: ' + this.winner)
  }
}
</script>
