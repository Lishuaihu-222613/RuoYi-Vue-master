import * as kgBuilderApi from '@/api/system/KgBuilder'
import G6 from '@antv/g6'

export  {
  contextMenu
}

const contextMenu = new G6.Menu({
  getContent(evt) {
    if (evt.target && evt.target.isCanvas && evt.target.isCanvas()) {
      return `
        <ul>
            <li title='addNode'>创建节点</li>
            <li title='addEdge'>创建关系</li>
            <li>li 3</li>
        </ul>`;
    } else if (evt.item && evt.item.getType() === "node") {
      return `
        <ul>
            <li title='addNode'>添加关系</li>
            <li title='deleteNode'>删除节点</li>
            <li title='updateNode'>修改节点</li>
            <li>li 4</li>
        </ul>`;
    } else if (evt.item && evt.item.getType() === "edge") {
      return `
        <ul>
            <li title='updateEdge'>修改关系</li>
            <li title='deleteEdge'>删除关系</li>
            <li>li 3</li>
        </ul>`;
    }
  },
  handleMenuClick: (target, item) => {
    if(target.title ==="addNode") {
      let obj = {
        label: '',
        x: target.x,
        y: target.y
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
    console.log(target, item);
  },
  // offsetX and offsetY include the padding of the parent container
  // 需要加上父级容器的 padding-left 16 与自身偏移量 10
  offsetX: 16 + 10,
  // 需要加上父级容器的 padding-top 24 、画布兄弟元素高度、与自身偏移量 10
  offsetY: 0,
  // the types of items that allow the menu show up
  // 在哪些类型的元素上响应
  itemTypes: ['node', 'edge', 'canvas'],
});


