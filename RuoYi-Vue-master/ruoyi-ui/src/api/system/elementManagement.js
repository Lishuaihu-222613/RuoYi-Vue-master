import request from '@/utils/request'

export function getElementById(elementId) {
  return request({
    url: '/element/getElementById/'+elementId,
    method: 'get'
  })
}

export function getSingleElementById(elementId) {
  return request({
    url: '/element/getSingleElementById/'+elementId,
    method: 'get'
  })
}

export function getAllProducts() {
  return request({
    url: '/element/getAllProducts',
    method: 'get'
  })
}

export function getSubElementsById(elementId) {
  return request({
    url: '/element/getSubElementsById/'+elementId,
    method: 'get'
  })
}

export function getProductOptionsByLabel(label) {
  return request({
    url: '/element/getProductOptionsByLabel/'+label,
    method: 'get'
  })
}

//根据类型获得产品
export function getProductListByLabel(data) {
  return request({
    url: '/element/getProductListsByLabel',
    method: 'post',
    data: data
  })
}

export function getProductById(elementId) {
  return request({
    url: '/element/getProductById/'+elementId,
    method: 'get'
  })
}

export function getAllElementsByParams(data) {
  return request({
    url: '/element/getAllElementsByParams',
    method: 'post',
    data: data
  })
}

export function createElement(data) {
  return request({
    url: '/element/createElement',
    method: 'post',
    data: data
  })
}

export function createElementForParent(data) {
  return request({
    url: '/element/createElementForParent',
    method: 'post',
    data: data
  })
}

export function updateElement(data) {
  return request({
    url: '/element/updateElement',
    method: 'post',
    data: data
  })
}

export function deleteElements(data) {
  return request({
    url: '/element/deleteElement',
    method: 'post',
    data: data
  })
}

export function getParentElement(elementId) {
  return request({
    url: '/element/getParentElementById/'+elementId,
    method: 'get',
  })
}

export function getConstrainsByElementId(elementId) {
  return request({
    url: '/element/getConstraintsByElementId/'+elementId,
    method: 'get',
  })
}

export function createConstraint(data) {
  return request({
    url: '/element/createConstraint',
    method: 'post',
    data: data
  })
}

export function updateConstraint(data) {
  return request({
    url: '/element/updateConstraint',
    method: 'post',
    data: data
  })
}

export function modifyFiles(data) {
  return request({
    url: '/element/modifyFiles',
    method: 'post',
    data: data
  })
}

export function modifyModelFile(data) {
  return request({
    url: '/element/modifyModelFile',
    method: 'post',
    data: data
  })
}

export function deleteConstraint(constraintId) {
  return request({
    url: '/element/deleteConstraint/'+constraintId,
    method: 'get',
  })
}

export function getStructureByRelatedId(relatedId) {
  return request({
    url: '/element/getRelatedStructure/'+relatedId,
    method: 'get',
  })
}
