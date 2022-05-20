<template>
  <div>
    <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">
    <el-button icon="el-icon-s-unfold" @click="changeLabel"></el-button>
    </el-radio-group>
    <el-menu default-active="1-4-1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse">
      <el-tree  :data="data"  indent="0px">

      </el-tree>
    </el-menu>
  </div>

</template>


<script>
export default {

  name: 'Navmenu',

  data() {
    return {
      isCollapse: true,
      data: [{
        label: '一级 1',
        children: [{
          label: '二级 1-1',
          children: [{
            label: '三级 1-1-1'
          }]
        }]
      }, {
        label: '一级 2',
        children: [{
          label: '二级 2-1',
          children: [{
            label: '三级 2-1-1'
          }]
        }, {
          label: '二级 2-2',
          children: [{
            label: '三级 2-2-1'
          }]
        }]
      }, {
        label: '一级 3',
        children: [{
          label: '二级 3-1',
          children: [{
            label: '三级 3-1-1'
          }]
        }, {
          label: '二级 3-2',
          children: [{
            label: '三级 3-2-1'
          }]
        }]
      }]
    };
  },

  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    changeLabel(){
      let token = this.isCollapse;
      if(token == false){
        this.isCollapse = true;
      }else {
        this.isCollapse = false;
      }
    }
  }
}
</script>

<style scoped lang="less">
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>
