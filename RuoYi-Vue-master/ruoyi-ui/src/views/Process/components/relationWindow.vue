<template>
  <el-row>
    <p style="text-align: center; margin: 0 0 20px">关系选择</p>
    <el-select v-model="relation" clearable placeholder="请选择工步关系" style="right :0px"
               @change="selectRelationKind"
    >
      <el-option
        v-for="item in relations"
        :key="item.value"
        :label="item.label"
        :value="item.value"
      >
      </el-option>
    </el-select>
  </el-row>
  <div style="text-align: center">
    <el-transfer
      v-model="selectedRelations"
      :button-texts="['移除', '添加']"
      :data="steps"
      :format="{noChecked: '${total}',hasChecked: '${checked}/${total}'}"
      :titles="['待选工步', '已选工步']"
      filterable
      :filter-method="filterMethod"
      style="text-align: left; display: inline-block"
    >
      <span slot-scope="{ option }">{{ option.number }}-{{ option.label }}</span>
      <el-button slot="right-footer" class="transfer-footer" size="small" @click="modifyRelation">操作</el-button>
    </el-transfer>
  </div>


</template>

<script>
export default {
  name: 'relationWindow'
}
</script>

<style scoped>

</style>
