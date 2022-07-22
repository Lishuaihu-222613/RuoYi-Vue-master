import request from '@/utils/request'

//获取决策工艺知识树
export function getDKTreeData(){
  return request({
    url: '/dK/getDKTree',
    method: 'get'
  })
}

//获取某类型的决策工艺知识对象
export function getDKByCluster(query){
  return request({
    url: '/dK/getDKByCluster',
    method: 'get',
    params: 'query'
  })
}

//获得具体决策工艺知识的图谱对象
export function getDKGraphById(query){
  return request({
    url: '/dK/getDKGraphById',
    method: 'get',
    params: 'query'
  })
}

//添加决策工艺知识
export function addDK(data){
  return request({
    url: '/dK/createDK',
    method: 'post',
    data: data
  })
}

//修改决策工艺知识
export function updateDK(data){
  return request({
    url: '/dK/updateDK',
    method: 'post',
    data: data
  })
}

//删除决策工艺知识
export function deleteDK(query){
  return request({
    url: '/dK/deleteDKById/'+query,
    method: 'get',
  })
}

//查看工艺知识来源
export function getDKSource(data){
  return request({
    url: '/dK/getDKSource',
    method: 'post',
    data: data
  })
}

//获取接口测试
export function getAllDecisionKnowledge(){
  return request({
    url: '/dK/getAllDecisionKnowledge',
    method: 'post'
  })
}
