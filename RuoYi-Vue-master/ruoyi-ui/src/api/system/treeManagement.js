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

// 新增知识树管理
export function addTreeManagement(data) {
  return request({
    url: '/ClassificationTree',
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
