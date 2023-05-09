import request from '@/utils/request'

export function getAllInspectMethods(data) {
  return request({
    url: '/method/getAllInspectionMethods',
    method: 'post',
    data: data
  })
}

export function getAllInspectMethodsByLabel(data) {
  return request({
    url: '/method/getInspectionMethodsByType',
    method: 'post',
    data: data
  })
}

export function getInspectMethodsByParams(data) {
  return request({
    url: '/method/getAllInspectionMethodsByParams',
    method: 'post',
    data: data
  })
}

export function getInspectMethodById(methodId) {
  return request({
    url: '/method/getInspectionMethodById/' + methodId,
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
    url: '/method/modifyRelations',
    method: 'post',
    data: data
  })
}
