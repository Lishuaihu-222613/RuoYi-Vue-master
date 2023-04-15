import request from '@/utils/request'

// 查询知识树管理列表
export function listTreeManagement(query) {
  return request({
    url: '/ClassificationTree/list',
    method: 'get',
    params: query
  })
}

// 查询知识树管理详细
export function getTreeManagement(id) {
  return request({
    url: '/ClassificationTree/' + id,
    method: 'get'
  })
}

export function getAllRootNode() {
  return request({
    url: '/ClassificationTree/getAllRoot',
    method: 'get'
  })
}

export function getleafsByParams(data) {
  return request({
    url: '/ClassificationTree/getleafsByParams',
    method: 'post',
    data:data
  })
}

export function getParentLeaf(id) {
  return request({
    url: '/ClassificationTree/getParentLeaf/'+id,
    method: 'get',
  })
}

// 新增知识树管理
export function addTreeManagement(data) {
  return request({
    url: '/ClassificationTree',
    method: 'post',
    data: data
  })
}

export function getSubLeafsByParentId(data) {
  return request({
    url: '/ClassificationTree/getSubLeafs',
    method: 'post',
    data: data
  })
}


export function addSubLeaf(data) {
  return request({
    url: '/ClassificationTree/addSubLeaf',
    method: 'post',
    data: data
  })
}

// 修改知识树管理
export function updateTreeManagement(data) {
  return request({
    url: '/ClassificationTree',
    method: 'put',
    data: data
  })
}

// 删除知识树管理
export function delTreeManagement(id) {
  return request({
    url: '/ClassificationTree/' + id,
    method: 'delete'
  })
}
