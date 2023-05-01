import request from '@/utils/request'

export function getAllInspectMethods(data) {
  return request({
    url: '/method/getAllInspectMethods',
    method: 'post',
    data: data
  })
}

export function getAllInspectMethodsByLabel(data) {
  return request({
    url: '/method/getAllInspectMethodsByLabel',
    method: 'post',
    data: data
  })
}

export function getInspectMethodsByParams(data) {
  return request({
    url: '/method/getInspectMethodsByParams',
    method: 'post',
    data: data
  })
}

export function getInspectMethodById(methodId) {
  return request({
    url: '/method/getInspectMethodById/' + methodId,
    method: 'get'
  })
}

export function createInspectMethod(data) {
  return request({
    url: '/method/createInspectMethod',
    method: 'post',
    data: data
  })
}

export function updateInspectMethod(data) {
  return request({
    url: '/method/updateInspectMethod',
    method: 'post',
    data: data
  })
}

export function deleteInspectMethod(data) {
  return request({
    url: '/method/deleteInspectMethod',
    method: 'post',
    data:data
  })
}

export function changeRelations(data) {
  return request({
    url: '/method/changeRelations',
    method: 'post',
    data: data
  })
}
