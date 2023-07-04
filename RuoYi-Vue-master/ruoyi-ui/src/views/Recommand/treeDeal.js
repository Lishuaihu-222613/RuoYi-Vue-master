class Tree {
  constructor(config = {}) {
    this.defaultConfig = {
      id: "element",
      children: "subElements",
      pId: "pId"
    };
    this.config = Object.assign(this.defaultConfig, config);
  }

  // 获取某一项的数据
  getData (tree, id) {
    let stack = [];
    let result = {};
    if (Array.isArray(tree) && tree.length > 0) {
      // tree.
      for (let i = 0; i < tree.length; i++) {
        stack.push(tree[i]);
      }
    } else if (typeof tree === "object") {
      stack = [tree];
    }
    while (stack.length) {
      let node = stack.shift();
      if (node[this.config.id] === id) {
        result = node;
        return result;
      }
      if (node[this.config.children] && node[this.config.children].length > 0) {
        stack = stack.concat(node[this.config.children]);
      }
    }
    return result;
  }

  // 根据id找到parentNode
  findIdParentNode (tree, id) {
    let currentNode = this.getData(tree, id);
    if (Object.keys(currentNode).length > 0) {
      let parentNode = currentNode[this.config.pId] ? this.getData(tree, currentNode[this.config.pId]) : tree;
      return parentNode[this.config.children] ? parentNode[this.config.children] : parentNode;
    }
    return [];
  }

  // list=>tree 列表转换成为树
  listToTree (list) {
    let info = list.reduce((map, node) => {
      if (!map[node[this.config.id]]) {
        map[node[this.config.id]] = node;
        node.children = [];
      }
      return map;
    }, {});
    return list.filter(v => {
      if (info[v[this.config.pId]]) {
        info[v[this.config.pId]].children.push(v);
      }
      return !v[[this.config.pId]];
    });
  }

  // tree-list 树转变成为列表
  treeToList (tree) {
    const { children } = this.config; const result = [...tree];
    for (let i = 0; i < result.length; i++) {
      if (!result[i][children]) {
        continue;
      }
      result.splice(i + 1, 0, ...result[i][children]);
    }
    return result;
  }

  // 删除某个节点
  removeNode (tree, id) {
    let pNode = this.findIdParentNode(tree, id);
    let currentIndex = pNode.findIndex(v => v[this.config.id] === id);
    currentIndex > -1 && pNode.splice(currentIndex, 1);
    // let currentNode = this.getData(tree, id);
    // if (Object.keys(currentNode).length > 0) {
    //   let parentNode = currentNode[this.config.pId] ? this.getData(tree, currentNode[this.config.pId]) : tree;
    //   let parent = parentNode[this.config.children] ? parentNode[this.config.children] : parentNode;
    //   let currentIndex = parent.findIndex(v => v[this.config.id] === id);
    //   currentIndex > -1 && parent.splice(currentIndex, 1);
    // }
  }

  // 插入某个子节点
  insertChildrenNode (tree, pId, node) {
    if (pId) { // 说明的时候某个节点
      let currentNode = this.getData(tree, pId);
      if (Object.keys(currentNode).length > 0) {
        if (!currentNode[[this.config.children]]) {
          currentNode[this.config.children] = [];
        }
        currentNode[this.config.children].push(node);
      }
    } else if (pId === "" || pId === 0 || pId === "0") { // 说明操作的根节点
      tree.push(node);
    }
  }

  // 插入某个节点之后
  insertAfter (tree, sourceId, targetNode) {
    let pNode = this.findIdParentNode(tree, sourceId); // 找出它的父节点
    let sIndex = pNode.findIndex(v => v[this.config.id] === sourceId);
    console.log(pNode);
    if (sIndex > -1) {
      pNode.splice(sIndex + 1, 0, targetNode);
    }
  }
}

export default Tree;

// this.processData = [{
//   elementId:25369,
//   elementNumber:'',
//   elementName:'壳体准备工艺',
//   elementDescription:'',
//   elementRemark:["壳体内表面绝热层喷砂、打磨、清理"],
//   elementRequirements:{},
//   elementOtherProperties:{},
//   usedResources:[],
//   subElements:[
//     {
//       elementId:25412,
//       elementNumber:'10',
//       elementName:'壳体预处理工序',
//       elementDescription:'壳体内表面绝热层喷砂、打磨、清理',
//       elementRemark:[],
//       elementRequirements:{},
//       elementOtherProperties:{},
//       usedResources:[],
//       subElements:[
//         {
//           elementId:25501,
//           elementNumber:'1',
//           elementName:'壳体接收',
//           elementDescription:'',
//           elementRemark:[],
//           elementRequirements:{
//             技术要求:'见子工步',
//             检验要求:'三检'
//           },
//           elementOtherProperties:{},
//           usedResources:[],
//           subElements:[
//             {
//               elementId:25520,
//               elementNumber:'1.1',
//               elementName:'壳体接收1',
//               elementDescription:'壳体卸车时，将壳体平稳放置弧形架或打磨转动车上。检查壳体外表面有无划痕、磕碰伤，电缆支座有无磕碰伤，前后封头纤维丝有无断裂，前后裙及前后接头（金属件）有无损伤，螺纹孔有无缺陷、多余物，人工脱粘层分离是否正常，有无局部粘连，其夹层内有无多余物等，并做详细记录。',
//               elementRemark:[],
//               elementRequirements:{},
//               elementOtherProperties:{},
//               usedResources:[],
//             },
//             {
//               elementId:25521,
//               elementNumber:'1.2',
//               elementName:'壳体接收2',
//               elementDescription:'填写产品标识，连同质量标识一同粘贴于壳体前裙端面I象限金属裙框附近处。',
//               elementRemark:[],
//               elementRequirements:{
//                 技术要求:'粘贴产品标识前，应确认质量传递卡与壳体质量标识齐全后方可粘贴',
//                 检验要求:'三检'
//               },
//               elementOtherProperties:{},
//               usedResources:[],
//             },
//             {
//               elementId:25522,
//               elementNumber:'1.3',
//               elementName:'壳体接收3',
//               elementDescription:'壳体接收后，每天记录壳体存放区域的环境情况和产品防护情况',
//               elementRemark:[],
//               elementRequirements:{
//                 技术要求:'环境情况包括：天气情况、温度、相对湿含量、绝对湿含量',
//                 检验要求:'三检'
//               },
//               elementOtherProperties:{},
//               usedResources:[],
//             }
//           ]
//         },{
//           elementId:25524,
//           elementNumber:'2',
//           elementName:'内表面处理',
//           elementDescription:'内表面喷砂处理与内表面打磨',
//           elementRemark:[],
//           elementRequirements:{},
//           elementOtherProperties:{},
//           usedResources:[],
//           subElements:[
//             {
//               elementId:25525,
//               elementNumber:'2.1',
//               elementName:'内表面喷砂处理',
//               elementDescription:'内表面喷砂处理',
//               elementRemark:[],
//               elementRequirements:{},
//               elementOtherProperties:{},
//               usedResources:[],
//               subElements:[
//                 {
//                   elementId:25527,
//                   elementNumber:'2.1.1',
//                   elementName:'内表面喷砂处理1',
//                   elementDescription:'使用胶带对金属裙上的螺孔进行粘贴保护',
//                   elementRemark:[],
//                   elementRequirements:{
//                     技术要求:'内有螺纹的孔均需保护，光孔不需要保护。'
//                   },
//                   elementOtherProperties:{},
//                   usedResources:[],
//                 },
//                 {
//                   elementId:25528,
//                   elementNumber:'2.1.2',
//                   elementName:'内表面喷砂处理2',
//                   elementDescription:'使用胶布对人工脱夹层进行简易封口。',
//                   elementRemark:[],
//                   elementRequirements:{
//                     技术要求:'防止喷砂过程中多余物进入人脱夹层中。'
//                   },
//                   elementOtherProperties:{},
//                   usedResources:[],
//                 },
//                 {
//                   elementId:25529,
//                   elementNumber:'2.1.3',
//                   elementName:'内表面喷砂处理3',
//                   elementDescription:'内表面处理采用立式喷砂工艺，喷砂前准备、安装翻转工装及壳体防护工装、喷枪连接、模拟运行、壳体喷砂、壳体起吊转运、卸除工装等步骤操作按《非金属壳体立式喷砂通用工艺规程》执行。',
//                   elementRemark:[],
//                   elementRequirements:{},
//                   elementOtherProperties:{},
//                   usedResources:[],
//                 }
//               ]
//             },
//             {
//               elementId:25526,
//               elementNumber:'2.2',
//               elementName:'内表面打磨',
//               elementDescription:'打磨前准备，壳体防护、绝热层打磨、人工脱黏层打磨、壳体清理可按Q/Gz626《非金属壳体内表面处理通用工艺规程执行》',
//               elementRemark:[],
//               elementRequirements:{
//                 技术要求:'如需使用打磨工艺，需提前反馈工艺，经批准后方可使用打磨工艺进行内表面处理'
//               },
//               elementOtherProperties:{},
//               usedResources:[],
//             }
//           ]
//         }
//       ]
//     },
//     {
//       elementId:25411,
//       elementNumber:'20',
//       elementName:'衬层成型工序',
//       elementDescription:'待编辑',
//       elementRemark:[],
//       elementRequirements:{},
//       elementOtherProperties:{},
//       usedResources:[],
//     },
//     {
//       elementId:25432,
//       elementNumber:'30',
//       elementName:'壳体预处理工序',
//       elementDescription:'待编辑',
//       elementRemark:[],
//       elementRequirements:{},
//       elementOtherProperties:{},
//       usedResources:[],
//     }
//
//   ]
// }]

// this.processData.push( {
//   elementId: 25369,
//   elementNumber: '',
//   elementName: '壳体准备工艺',
//   elementDescription: '',
//   elementRemark: ['壳体内表面绝热层喷砂、打磨、清理'],
//   elementRequirements: {},
//   elementOtherProperties: {},
//   usedResources: [],
//   subElements: [
//     {
//       elementId: 25412,
//       elementNumber: '10',
//       elementName: '壳体预处理工序',
//       elementDescription: '壳体内表面绝热层喷砂、打磨、清理',
//       elementRemark: [],
//       elementRequirements: {},
//       elementOtherProperties: {},
//       usedResources: [],
//       subElements: [
//         {
//           elementId: 25501,
//           elementNumber: '1',
//           elementName: '壳体接收',
//           elementDescription: '',
//           elementRemark: [],
//           elementRequirements: {
//             技术要求: '见子工步',
//             检验要求: '三检'
//           },
//           elementOtherProperties: {},
//           usedResources: [],
//           subElements: [
//             {
//               elementId: 25520,
//               elementNumber: '1.1',
//               elementName: '壳体接收1',
//               elementDescription: '壳体卸车时，将壳体平稳放置弧形架或打磨转动车上。检查壳体外表面有无划痕、磕碰伤，电缆支座有无磕碰伤，前后封头纤维丝有无断裂，前后裙及前后接头（金属件）有无损伤，螺纹孔有无缺陷、多余物，人工脱粘层分离是否正常，有无局部粘连，其夹层内有无多余物等，并做详细记录。',
//               elementRemark: [],
//               elementRequirements: {},
//               elementOtherProperties: {},
//               usedResources: []
//             },
//             {
//               elementId: 25521,
//               elementNumber: '1.2',
//               elementName: '壳体接收2',
//               elementDescription: '填写产品标识，连同质量标识一同粘贴于壳体前裙端面I象限金属裙框附近处。',
//               elementRemark: [],
//               elementRequirements: {
//                 技术要求: '粘贴产品标识前，应确认质量传递卡与壳体质量标识齐全后方可粘贴',
//                 检验要求: '三检'
//               },
//               elementOtherProperties: {},
//               usedResources: []
//             },
//             {
//               elementId: 25522,
//               elementNumber: '1.3',
//               elementName: '壳体接收3',
//               elementDescription: '壳体接收后，每天记录壳体存放区域的环境情况和产品防护情况',
//               elementRemark: [],
//               elementRequirements: {
//                 技术要求: '环境情况包括：天气情况、温度、相对湿含量、绝对湿含量',
//                 检验要求: '三检'
//               },
//               elementOtherProperties: {},
//               usedResources: []
//             }
//           ]
//         }, {
//           elementId: 25524,
//           elementNumber: '2',
//           elementName: '内表面处理',
//           elementDescription: '内表面喷砂处理与内表面打磨',
//           elementRemark: [],
//           elementRequirements: {},
//           elementOtherProperties: {},
//           usedResources: [],
//           subElements: [
//             {
//               elementId: 25525,
//               elementNumber: '2.1',
//               elementName: '内表面喷砂处理',
//               elementDescription: '内表面喷砂处理',
//               elementRemark: [],
//               elementRequirements: {},
//               elementOtherProperties: {},
//               usedResources: [],
//               subElements: [
//                 {
//                   elementId: 25527,
//                   elementNumber: '2.1.1',
//                   elementName: '内表面喷砂处理1',
//                   elementDescription: '使用胶带对金属裙上的螺孔进行粘贴保护',
//                   elementRemark: [],
//                   elementRequirements: {
//                     技术要求: '内有螺纹的孔均需保护，光孔不需要保护。'
//                   },
//                   elementOtherProperties: {},
//                   usedResources: []
//                 },
//                 {
//                   elementId: 25528,
//                   elementNumber: '2.1.2',
//                   elementName: '内表面喷砂处理2',
//                   elementDescription: '使用胶布对人工脱夹层进行简易封口。',
//                   elementRemark: [],
//                   elementRequirements: {
//                     技术要求: '防止喷砂过程中多余物进入人脱夹层中。'
//                   },
//                   elementOtherProperties: {},
//                   usedResources: []
//                 },
//                 {
//                   elementId: 25529,
//                   elementNumber: '2.1.3',
//                   elementName: '内表面喷砂处理3',
//                   elementDescription: '内表面处理采用立式喷砂工艺，喷砂前准备、安装翻转工装及壳体防护工装、喷枪连接、模拟运行、壳体喷砂、壳体起吊转运、卸除工装等步骤操作按《非金属壳体立式喷砂通用工艺规程》执行。',
//                   elementRemark: [],
//                   elementRequirements: {},
//                   elementOtherProperties: {},
//                   usedResources: []
//                 }
//               ]
//             },
//             {
//               elementId: 25526,
//               elementNumber: '2.2',
//               elementName: '内表面打磨',
//               elementDescription: '打磨前准备，壳体防护、绝热层打磨、人工脱黏层打磨、壳体清理可按Q/Gz626《非金属壳体内表面处理通用工艺规程执行》',
//               elementRemark: [],
//               elementRequirements: {
//                 技术要求: '如需使用打磨工艺，需提前反馈工艺，经批准后方可使用打磨工艺进行内表面处理'
//               },
//               elementOtherProperties: {},
//               usedResources: []
//             }
//           ]
//         }
//       ]
//     },
//     {
//       elementId: 25411,
//       elementNumber: '20',
//       elementName: '衬层成型工序',
//       elementDescription: '待编辑',
//       elementRemark: [],
//       elementRequirements: {},
//       elementOtherProperties: {},
//       usedResources: []
//     },
//     {
//       elementId: 25432,
//       elementNumber: '30',
//       elementName: '壳体预处理工序',
//       elementDescription: '待编辑',
//       elementRemark: [],
//       elementRequirements: {},
//       elementOtherProperties: {},
//       usedResources: []
//     }
//
//   ]
// })

// this.processData = [{
//   elementId:25369,
//   elementNumber:'',
//   elementName:'壳体准备工艺',
//   elementDescription:'',
//   elementRemark:["壳体内表面绝热层喷砂、打磨、清理"],
//   elementRequirements:{},
//   elementOtherProperties:{},
//   subElements:[
//     {
//       elementId:25412,
//       elementNumber:'10',
//       elementName:'壳体预处理工序',
//       elementDescription:'壳体内表面绝热层喷砂、打磨、清理',
//       elementRemark:[],
//       elementRequirements:{},
//       elementOtherProperties:{},
//       subElements:[
//         {
//           elementId:25501,
//           elementNumber:'1',
//           elementName:'壳体接收',
//           elementDescription:'',
//           elementRemark:[],
//           elementRequirements:{
//             技术要求:'见子工步',
//             检验要求:'三检'
//           },
//           elementOtherProperties:{},
//           subElements:[
//             {
//               elementId:25520,
//               elementNumber:'1.1',
//               elementName:'壳体接收1',
//               elementDescription:'壳体卸车时，将壳体平稳放置弧形架或打磨转动车上。检查壳体外表面有无划痕、磕碰伤，电缆支座有无磕碰伤，前后封头纤维丝有无断裂，前后裙及前后接头（金属件）有无损伤，螺纹孔有无缺陷、多余物，人工脱粘层分离是否正常，有无局部粘连，其夹层内有无多余物等，并做详细记录。',
//               elementRemark:[],
//               elementRequirements:{},
//               elementOtherProperties:{},
//             },
//             {
//               elementId:25521,
//               elementNumber:'1.2',
//               elementName:'壳体接收2',
//               elementDescription:'填写产品标识，连同质量标识一同粘贴于壳体前裙端面I象限金属裙框附近处。',
//               elementRemark:[],
//               elementRequirements:{
//                 技术要求:'粘贴产品标识前，应确认质量传递卡与壳体质量标识齐全后方可粘贴',
//                 检验要求:'三检'
//               },
//               elementOtherProperties:{},
//             },
//             {
//               elementId:25522,
//               elementNumber:'1.3',
//               elementName:'壳体接收3',
//               elementDescription:'壳体接收后，每天记录壳体存放区域的环境情况和产品防护情况',
//               elementRemark:[],
//               elementRequirements:{
//                 技术要求:'环境情况包括：天气情况、温度、相对湿含量、绝对湿含量',
//                 检验要求:'三检'
//               },
//               elementOtherProperties:{},
//             }
//           ]
//         },{
//           elementId:25524,
//           elementNumber:'2',
//           elementName:'内表面处理',
//           elementDescription:'内表面喷砂处理与内表面打磨',
//           elementRemark:[],
//           elementRequirements:{},
//           elementOtherProperties:{},
//           subElements:[
//             {
//               elementId:25525,
//               elementNumber:'2.1',
//               elementName:'内表面喷砂处理',
//               elementDescription:'内表面喷砂处理',
//               elementRemark:[],
//               elementRequirements:{},
//               elementOtherProperties:{},
//               subElements:[
//                 {
//                   elementId:25527,
//                   elementNumber:'2.1.1',
//                   elementName:'内表面喷砂处理1',
//                   elementDescription:'使用胶带对金属裙上的螺孔进行粘贴保护',
//                   elementRemark:[],
//                   elementRequirements:{
//                     技术要求:'内有螺纹的孔均需保护，光孔不需要保护。'
//                   },
//                   elementOtherProperties:{},
//                 },
//                 {
//                   elementId:25528,
//                   elementNumber:'2.1.2',
//                   elementName:'内表面喷砂处理2',
//                   elementDescription:'使用胶布对人工脱夹层进行简易封口。',
//                   elementRemark:[],
//                   elementRequirements:{
//                     技术要求:'防止喷砂过程中多余物进入人脱夹层中。'
//                   },
//                   elementOtherProperties:{},
//                 },
//                 {
//                   elementId:25529,
//                   elementNumber:'2.1.3',
//                   elementName:'内表面喷砂处理3',
//                   elementDescription:'内表面处理采用立式喷砂工艺，喷砂前准备、安装翻转工装及壳体防护工装、喷枪连接、模拟运行、壳体喷砂、壳体起吊转运、卸除工装等步骤操作按《非金属壳体立式喷砂通用工艺规程》执行。',
//                   elementRemark:[],
//                   elementRequirements:{},
//                   elementOtherProperties:{},
//                 }
//               ]
//             },
//             {
//               elementId:25526,
//               elementNumber:'2.2',
//               elementName:'内表面打磨',
//               elementDescription:'打磨前准备，壳体防护、绝热层打磨、人工脱黏层打磨、壳体清理可按Q/Gz626《非金属壳体内表面处理通用工艺规程执行》',
//               elementRemark:[],
//               elementRequirements:{
//                 技术要求:'如需使用打磨工艺，需提前反馈工艺，经批准后方可使用打磨工艺进行内表面处理'
//               },
//               elementOtherProperties:{},
//             }
//           ]
//         }
//       ]
//     },
//     {
//       elementId:25411,
//       elementNumber:'20',
//       elementName:'衬层成型工序',
//       elementDescription:'待编辑',
//       elementRemark:[],
//       elementRequirements:{},
//       elementOtherProperties:{},
//     },
//     {
//       elementId:25432,
//       elementNumber:'30',
//       elementName:'壳体预处理工序',
//       elementDescription:'待编辑',
//       elementRemark:[],
//       elementRequirements:{},
//       elementOtherProperties:{},
//     },
//     {
//       elementId:25631,
//       elementNumber:'40',
//       elementName:'壳体清理',
//       elementDescription:'壳内无沙粒等多余物残留',
//       elementRemark:[],
//       elementRequirements:{},
//       elementOtherProperties:{}
//     }
//   ]
// }]

// this.recommandProcess = [
//   {
//     elementId:25631,
//     elementNumber:'40',
//     elementName:'壳体清理',
//     elementDescription:'壳内无沙粒等多余物残留',
//     elementRemark:[],
//     elementRequirements:{},
//     elementOtherProperties:{}
//   },{
//     elementId:26589,
//     elementNumber:'20',
//     elementName:'壳体除油',
//     elementDescription:'使用使用化学溶剂进行清洗',
//     elementRemark:[],
//     elementRequirements:{},
//     elementOtherProperties:{}
//   },{
//     elementId:30785,
//     elementNumber:'20',
//     elementName:'金属壳体除油',
//     elementDescription:'使用高温炭化除油',
//     elementRemark:[],
//     elementRequirements:{},
//     elementOtherProperties:{}
//   },{
//     elementId:30926,
//     elementNumber:'30',
//     elementName:'壳体物理除油',
//     elementDescription:'超声波气泡除油',
//     elementRemark:[],
//     elementRequirements:{},
//     elementOtherProperties:{}
//   }
// ]
