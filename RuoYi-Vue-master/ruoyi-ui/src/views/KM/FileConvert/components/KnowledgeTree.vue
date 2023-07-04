<template>

  <div style="padding: 0px">
    <span class="treeFont">装药总装工艺知识分类树</span>
    <el-input
      v-model="filterText"
      class="KnowledgeSearch"
      clearable
      placeholder="输入关键字进行检索"
      @change="searchDomain"
      @select="handleSelect"
    >
    </el-input>
    <el-tree
      ref="tree"
      :data="treeData"
      :filter-node-method="filterNode"
      :highlight-current=true
      :props="defaultProps"
      class="filter-tree"
      node-key="leafId"
      @node-click="clickSearch"
    >
    </el-tree>
  </div>

</template>

<script>
import * as treeManagement from '@/api/system/treeManagement'

export default {
  name: 'KnowledgeTree',

  data() {
    return {
      filterText: '',
      treeData: [],
      defaultProps: {
        children: 'subLeafs',
        label: 'leafName'
      },
    }
  },

  created() {
    this.initKnowledgeTree()
  },

  watch: {
    filterText(val) {
      this.$refs.tree.filter(val)
    }
  },

  methods: {
    filterNode(value, data) {
      if (!value) return true
      return data.leafName.indexOf(value) !== -1
    },
    querySearch(queryString, cb) {
      let texts = this.filterText
      let results = queryString ? texts.filter(this.createFilter(queryString)) : texts
      cb(results)
    },
    createFilter(queryString) {
      return (texts) => {
        return (texts.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0)
      }
    },
    handleSelect(item) {
      console.log(item)
    },
    searchDomain() {
      this.$emit('searchDomain', this.filterText)
    },
    clickSearch(obj, node, tree) {
      let val = obj.leafName
      this.$emit('searchDomain', val)
    },
    initKnowledgeTree() {
      this.treeData = []
      treeManagement.getAllRootNode().then(result => {
        let data = result.data
        data.forEach((item) => {
          treeManagement.getTreeManagement(item.leafId).then(result => {
            if (result.code === 200) {
              this.treeData.push(result.data)
            }
          })
        })
      })
    }
  }
}
</script>

<style scoped>
.KnowledgeSearch {
  border-radius: 5px;
  box-shadow: 2px 2px 2px 0 rgba(0, 0, 0, 0.2);
  margin: 5px;
  line-height: 0px;
}

.treeFont {
  margin: 5px;
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
}

.filter-tree {
  width: 300px;
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
  margin: 5px;
}

</style>
