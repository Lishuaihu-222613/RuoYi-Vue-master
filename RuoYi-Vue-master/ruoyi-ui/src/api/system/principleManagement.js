import request from '@/utils/request'

export function getAllPrinciples(data) {
  return request({
    url: '/Principle/getAllPrinciples',
    method: 'post',
    data: data
  })
}

export function getAllPrinciplesByLabel(data) {
  return request({
    url: '/Principle/getAllPrinciplesByLabel',
    method: 'post',
    data: data
  })
}

export function getPrinciplesByParams(data) {
  return request({
    url: '/Principle/getPrinciplesByParams',
    method: 'post',
    data: data
  })
}

export function getPrincipleById(principleId) {
  return request({
    url: '/Principle/getPrincipleById/' + principleId,
    method: 'get'
  })
}

export function createPrinciple(data) {
  return request({
    url: '/Principle/createPrinciple',
    method: 'post',
    data: data
  })
}

export function updatePrinciple(data) {
  return request({
    url: '/Principle/updatePrinciple',
    method: 'post',
    data: data
  })
}

export function deletePrinciple(principleId) {
  return request({
    url: '/Principle/deletePrinciple/' + principleId,
    method: 'get'
  })
}
