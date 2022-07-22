export default{
  getEvents () {
    return {
      'node:mouseover': 'onMouseover',
      'node:mouseleave': 'onMouseleave',
      'node:mousedown': 'onMousedown'
    }
  },
  onMouseover (e) { // 鼠标移入显示锚点
    e.item.getModel().linkPoints.top = true
    e.item.getModel().linkPoints.left = true
    e.item.getModel().linkPoints.right = true
    e.item.getModel().linkPoints.bottom = true
    this.graph.refreshItem(e.item)
  },
  onMouseleave (e) { // 鼠标移出隐藏锚点
    e.item.getModel().linkPoints.top = false
    e.item.getModel().linkPoints.left = false
    e.item.getModel().linkPoints.right = false
    e.item.getModel().linkPoints.bottom = false
    this.graph.refreshItem(e.item)
  },
  onMousedown (e) {
    const edge = this.graph.findAllByState('edge', 'selected')
    console.log(e.target.cfg.className)
    if (edge.length !== 0) {
      this.graph.setItemState(edge[0], 'selected', false)
    }
    if (e.target.cfg.className === 'link-point-top') { // 点击锚点
      this.graph.setMode('addEdge')
    } else { // 点击节点
      this.graph.setMode('default')
    }
  }
}
