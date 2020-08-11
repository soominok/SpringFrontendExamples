<template>
  <div>
    <h3>Board Modfied Form</h3>
    <!-- 누르면 수정이 완료되게 되는 것 -->
    <button type="submit">Modification</button>
    <!-- 누르면 되돌아 가는 것 -->
    <router-link :to="{ name: 'BoardReadPage', params: { boardNo: 1} }">
      Cancel
    </router-link>
  </div>
</template>

<script>
export default {
  name: 'BoardModfiyForm'
}
</script>
