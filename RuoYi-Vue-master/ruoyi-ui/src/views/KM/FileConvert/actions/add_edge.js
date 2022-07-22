import * as kgBuilderApi from '@/api/system/KgBuilder'

let obj = {
  source: '',
  target: '',
  domain: '',
  label:''
}
export default{

  getEvents () {
    return {
      'node:click': 'onClick',
      'mousemove': 'onMousemove',
      'edge:click': 'onEdgeClick'
    }
  },
  onClick (ev) {
    const node = ev.item
    this.item = ev.item
    const graph = this.graph
    const point = {
      x: ev.x,
      y: ev.y
    }

    const model = node.getModel()
    if (this.addingEdge && this.edge) {
      graph.updateItem(this.edge, {
        target: model.id
      })
      console.log(this.edge)
      let initEdge = {
        source : this.edge.getModel().source,
        target : this.edge.getModel().target,
        domain : this.graph.get('domain'),
        label: ''
      }
      kgBuilderApi.createEdge(initEdge).then(result => {
        if (result.code === 200) {
          alert('创建关系成功！')
          this.graph.removeItem(this.edge.getModel())
          this.graph.addItem('edge',result.data)
          this.graph.refresh()
        }
        else{
          alert('创建关系失败！')
        }
      })
      this.edge = null
      this.addingEdge = false
      this.graph.setMode('default');
    } else {
      obj = {
        source: model.id,
        target: point,
      }
      this.edge = graph.addItem('edge', obj)
      this.addingEdge = true
      console.log(this.edge)
      this.graph.refreshItem(ev.item)
    }
  },
  onMousemove (ev) {
    const point = {
      x: ev.x,
      y: ev.y
    }
    this.graph.updateItem(this.edge, {
      target: point
    })
    // if (this.addingEdge && this.edge) {
    //   // 判断是否为节点
    //   if (ev.item !== null && ev.item.getType() === 'node') {
    //     this.graph.updateItem(this.edge, {
    //       target: ev.item.getModel().id
    //     })
    //     obj.target = ev.item.getModel().id
    //   } else {
    //     this.graph.updateItem(this.edge, {
    //       target: point
    //     })
    //   }
    // }
  },
  onEdgeClick (ev) {
    const currentEdge = ev.item
    if (this.addingEdge && this.edge === currentEdge) { // 点击空白处后移除连线
      this.graph.removeItem(this.edge)
      this.edge = null
      this.addingEdge = false
    }
    this.graph.setMode('default')
  }
}
