import request from '@/utils/request'

// 查询知识树管理列表
export function listDomainManagement(query) {
  return request({
    url: '/knowledgeTree/domainManagement/list',
    method: 'get',
    params: query
  })
}

// 查询知识树管理详细
export function getDomainManagement(id) {
  return request({
    url: '/knowledgeTree/domainManagement/' + id,
    method: 'get'
  })
}

// 新增知识树管理
export function addDomainManagement(data) {
  return request({
    url: '/knowledgeTree/domainManagement',
    method: 'post',
    data: data
  })
}

// 修改知识树管理
export function updateDomainManagement(data) {
  return request({
    url: '/knowledgeTree/domainManagement',
    method: 'put',
    data: data
  })
}

// 删除知识树管理
export function delDomainManagement(id) {
  return request({
    url: '/knowledgeTree/domainManagement/' + id,
    method: 'delete'
  })
}
