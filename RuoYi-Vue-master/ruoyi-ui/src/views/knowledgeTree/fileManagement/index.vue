<template>
  <div>
    <el-button type="text" @click="dialogVisible = true">点击打开 Dialog</el-button>
    <FilePreview url="12313" :visible="dialogVisible"></FilePreview>
  </div>


</template>

<script>

export default {
  name: 'fileManagement',
  data() {
    return {
      dialogVisible: false
    };
  },
}
</script>

<style scoped>

</style>
