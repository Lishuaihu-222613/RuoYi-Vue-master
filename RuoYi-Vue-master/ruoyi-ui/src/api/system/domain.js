import request from '@/utils/request'

// 查询知识树管理列表
export function listDomain(query) {
  return request({
    url: '/kg/getGraph',
    method: 'get',
    params: query
  })
}

// 查询知识树管理详细
export function getDomain(id) {
  return request({
    url: '/system/domain/' + id,
    method: 'get'
  })
}

// 新增知识树管理
export function addDomain(data) {
  return request({
    url: '/kg/createDomain',
    method: 'post',
    data: data
  })
}

// 修改知识树管理
export function updateDomain(data) {
  return request({
    url: '/system/domain',
    method: 'put',
    data: data
  })
}

// 删除知识树管理
export function delDomain(id) {
  return request({
    url: '/system/domain/' + id,
    method: 'delete'
  })
}
