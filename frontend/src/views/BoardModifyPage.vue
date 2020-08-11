<template>
  <div align="center">
  <!-- 실제 작업을 컴포넌트쪽으로 넘긴 것 -->
    <h2>Board Modification</h2>
    <board-modify-form/>
    <!-- board-modify-form 컴포넌트에 넘긴 것 -->
  </div>
</template>

<script>
import BoardModifyForm from '@/components/BoardModifyForm'

export default {
  name: 'BoardModifyPage',
  components: {
    BoardModifyForm
  }
}
</script>
