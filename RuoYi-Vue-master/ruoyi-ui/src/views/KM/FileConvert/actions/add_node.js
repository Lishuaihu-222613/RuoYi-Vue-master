import * as kgBuilderApi from '@/api/system/KgBuilder'

export default{

  getDefaultCfg () {
    return {
      trigger: 'shift'
    }
  },
  getEvents () {
    return {
      'canvas:dblclick': 'onClick' // 双击画布添加节点
    }
  },
  onClick (ev) {
    let obj = {
      label: '',
      x: ev.x,
      y: ev.y
    }
    obj.domain = this.graph.get('domain')
    kgBuilderApi.createNode(obj).then(result => {
      if (result.code === 200) {
        alert('创建节点成功！')
        this.graph.addItem('node', result.data)
      }
      else{
        alert('创建节点失败！')
      }
    })
  }
}
