<template>

  <div style="padding: 0px">
    <span class="treeFont">装药总装工艺知识结构树</span>
    <el-autocomplete
      class="KnowledgeSearch"
      placeholder="输入关键字进行检索"
      :fetch-suggestions="querySearch"
      v-model="filterText"
      :trigger-on-focus="false"
      @select="handleSelect"
      @change="searchDomain"
      clearable
      :autofocus = true
    >
    </el-autocomplete>
    <el-tree
      class="filter-tree"
      :data="treeData"
      :props="defaultProps"
      :filter-node-method="filterNode"
      :highlight-current= true
      @node-click="clickSearch"
      indent="4"
      ref="tree">
    </el-tree>
  </div>

</template>

<script>
import * as tree from '@/api/system/treeManagement'
import * as knowledgeTree from "@/api/knowledgeTree/domainManagement";
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
        hasChildren:'hasSubLeaf',
        children: 'subLeafs',
        label: 'name'
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
    searchDomain(){
      this.$emit('searchDomain',this.filterText);
    },
    clickSearch(obj,node,tree){
      let val = obj.name;
      this.$emit('searchDomain',val);
    },
    initKnowledgeTree() {
      // knowledgeTree.listDomainManagement().then(response => {
      //   this.treeData = this.handleTree(response.data, "id", "parentId");
      // });
      tree.getAllRootNode().then(result =>{
        this.treeData = result.data
      })
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
  width: 300px;
  font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
  margin: 5px;
}

</style>
