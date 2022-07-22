import request from '@/utils/request'

//获取图谱数据
export function getKgData(){
  return request({
    url: '/static/kgData.json',
    method: 'get'
  })
}

//获取工艺知识树结构
export function getKnowledgeTree(query) {
  return request({
    url: '/system/knowledgegraphdomain/list',
    method: 'get',
    params: query
  })
}

export function feedBack(data){
  return request({
    url: '/feedBack',
    method: 'post',
    data: JSON.parse(JSON.stringify(data))
  })
}

export function saveData(data){
  return request({
    url: '/er/saveData',
    method: 'post',
    data: data
  })
}

export function getDomainNode(domainId){
  return request({
    url: '/er/getDomainNode',
    method: 'get',
    params: domainId
  })
}

export function execute(domainId){
  return request({
    url: '/er/execute',
    method: 'get',
    params: domainId
  })
}

export function getDomains(data){
  return request({
    url: '/kg/getGraph',
    method: 'post',
    data: data
  })
}

export function createDomain(data){
  return request({
    url: '/kg/createDomain',
    method: 'get',
    params: data
  })
}

export function getCypherResult(data){
  return request({
    url: '/kg/getCypherResult',
    method: 'get',
    params: data
  })
}

export function getNodeContent(data){
  return request({
    url: '/kg/getNodeContent',
    method: 'post',
    data: JSON.parse(JSON.stringify(data))
  })
}

export function getNodeImage(data){
  return request({
    url: '/kg/getNodeImage',
    method: 'post',
    data: JSON.parse(JSON.stringify(data))
  })
}

export function getNodeDetail(data){
  return request({
    url: '/kg/getNodeDetail',
    method: 'post',
    data: JSON.parse(JSON.stringify(data))
  })
}

export function saveNodeImage(data){
  return request({
    url: '/kg/saveNodeImage',
    method: 'post',
    data: data
  })
}

export function saveNodeContent(data){
  return request({
    url: '/kg/saveNodeContent',
    method: 'post',
    data: data
  })
}

export function getDomainGraph(data){
  return request({
    url: '/kg/getDomainGraph',
    method: 'post',
    data: data
  })
}


export function getRelationNodeCount(data){
  return request({
    url: '/kg/getRelationNodeCount',
    method: 'post',
    data: JSON.parse(JSON.stringify(data))
  })
}

export function deleteDomain(data){
  return request({
    url: '/kg/deleteDomain',
    method: 'post',
    data: JSON.parse(JSON.stringify(data))
  })
}

export function getRecommendGraph(data){
  return request({
    url: '/kg/getRecommendGraph',
    method: 'post',
    data: data
  })
}

export function deleteNode(data){
  return request({
    url: '/kg/deleteNode',
    method: 'post',
    params: JSON.parse(JSON.stringify(data))
  })
}

export function deleteEdge(data){
  return request({
    url: '/kg/deleteEdge',
    method: 'post',
    data: data
  })
}

export function createEdge(data){
  return request({
    url: '/kg/createEdge',
    method: 'post',
    data: data
  })
}

export function updateEdge(data){
  return request({
    url: '/kg/updateEdge',
    method: 'post',
    data: data
  })
}

export function updateNodeName(data){
  return request({
    url: '/kg/updateNodeName',
    method: 'post',
    data: data
  })
}

export function batchCreateNode(data){
  return request({
    url: '/kg/batchCreateNode',
    method: 'post',
    data: data
  })
}

export function batchCreateChildNode(data){
  return request({
    url: '/kg/batchCreateChildNode',
    method: 'post',
    data: data
  })
}

export function batchCreateSameNode(data){
  return request({
    url: '/kg/batchCreateSameNode',
    method: 'post',
    data: data
  })
}

export function exportGraph(data){
  return request({
    url: '/kg/exportGraph',
    method: 'post',
    data: JSON.parse(JSON.stringify(data))
  })
}

export function download(data){
  return request({
    url: '/kg/download/'+data,
    method: 'get',
    params:  ""
  })
}

export function updateCoordinateOfNode(data){
  return request({
    url: '/kg/updateCoordinateOfNode',
    method: 'post',
    data: data
  })
}

export function createNode(data){
  console.log(data)
  return request({
    url: '/kg/createNode',
    method: 'post',
    data: data
  })
}






