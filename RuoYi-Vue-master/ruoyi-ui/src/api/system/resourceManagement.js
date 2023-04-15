import request from '@/utils/request'

export function getAllResources(data) {
  return request({
    url: '/resource/getAllResources',
    method: 'post',
    data: data
  })
}

export function getAllResourcesByLabel(data) {
  return request({
    url: '/resource/getAllResourcesByLabel',
    method: 'post',
    data: data
  })
}

export function getResourcesByParams(data) {
  return request({
    url: '/resource/getResourcesByParams',
    method: 'post',
    data: data
  })
}



export function getAllAuxiliaryResources(data) {
  return request({
    url: '/resource/getAllAuxiliaryResources',
    method: 'post',
    data: data
  })
}

export function getAllAuxiliaryResourcesByLabel(data) {
  return request({
    url: '/resource/getAllAuxiliaryResourcesByLabel',
    method: 'post',
    data: data
  })
}

export function getAuxiliaryResourcesByParams(data) {
  return request({
    url: '/resource/getAuxiliaryResourcesByParams',
    method: 'post',
    data: data
  })
}

export function getResourceById(resourceId) {
  return request({
    url: '/resource/getResourceById/' + resourceId,
    method: 'get'
  })
}

export function createResource(data) {
  return request({
    url: '/resource/createResource',
    method: 'post',
    data: data
  })
}

export function updateResource(data) {
  return request({
    url: '/resource/updateResource',
    method: 'post',
    data: data
  })
}

export function deleteResource(resourceId) {
  return request({
    url: '/resource/deleteResource/' + resourceId,
    method: 'get'
  })
}
