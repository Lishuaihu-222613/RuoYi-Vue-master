<template>

  <div style="padding: 0px">
    <span class="treeFont">装配工艺知识结构树</span>
    <el-autocomplete
      class="KnowledgeSearch"
      placeholder="输入关键字进行检索"
      :fetch-suggestions="querySearch"
      v-model="filterText"
      :trigger-on-focus="false"
      @select="handleSelect"
      clearable
      :autofocus = true
    >
    </el-autocomplete>
    <el-tree
      class="filter-tree"
      :data="treeData"
      :props="defaultProps"
      default-expand-all
      :filter-node-method="filterNode"
      :highlight-current= true
      ref="tree">
    </el-tree>
  </div>

</template>

<script>
import * as kgBuilderApi from '@/api/system/KgBuilder'
import * as ruoyiutils from '@/utils/ruoyi'
import { getDomains } from '@/api/system/KgBuilder'
export default {
  name: 'KnowledgeTree',

  data() {
    return {
      filterText: '',
      treeData: [],
      defaultProps: {
        children: 'children',
        label: 'label'
      }
    };
  },

 mounted() {
    this.initKnowledgeTree()
 },

  watch: {
    filterText(val) {
      this.$refs.tree.filter(val);
    }
  },

  methods: {
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    querySearch(queryString, cb) {
      let texts = this.filterText
      let results = queryString ? texts.filter(this.createFilter(queryString)) : texts
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilter(queryString) {
      return (texts) => {
        return (texts.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      };
    },
    handleSelect(item) {
      console.log(item);
    },
    initKnowledgeTree() {
      kgBuilderApi.getDomains().then(result => {
        if (result.code == 200) {
          this.treedata = result.data;
        }
      });
      this.treedata = ruoyiutils.handleTree(treedata);
    }
  }
}
</script>

<style scoped>
.KnowledgeSearch{
  border-radius: 5px;
  box-shadow: 2px 2px 2px 0 rgba(0,0,0,0.2) ;
  margin: 5px;
  line-height: 0px;
}
.treeFont{
  margin: 5px;
  font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
}
.filter-tree{
  width: 252px;
  font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
  margin: 5px;
}

</style>
