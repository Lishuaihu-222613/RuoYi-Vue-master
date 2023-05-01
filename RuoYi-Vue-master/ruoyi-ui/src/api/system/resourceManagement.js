import request from '@/utils/request'

export function updateMouldTool(data) {
  return request({
    url: '/Resource/updateMouldTool',
    method: 'post',
    data: data
  })
}

export function createMouldTool(data) {
  return request({
    url: '/Resource/createMouldTool',
    method: 'post',
    data: data
  })
}

export function getMouldToolsByParams(data) {
  return request({
    url: '/Resource/getMouldToolsByParams',
    method: 'post',
    data: data
  })
}

export function getAllMouldToolsByLabel(data) {
  return request({
    url: '/Resource/getMouldToolsByLabel',
    method: 'post',
    data: data
  })
}

export function getAllMouldTools(data) {
  return request({
    url: '/Resource/getAllMouldTools',
    method: 'post',
    data: data
  })
}

export function updateSpreaderTool(data) {
  return request({
    url: '/Resource/updateSpreaderTool',
    method: 'post',
    data: data
  })
}

export function createSpreaderTool(data) {
  return request({
    url: '/Resource/createSpreaderTool',
    method: 'post',
    data: data
  })
}

export function getSpreaderToolsByParams(data) {
  return request({
    url: '/Resource/getSpreaderToolsByParams',
    method: 'post',
    data: data
  })
}

export function getAllSpreaderToolsByLabel(data) {
  return request({
    url: '/Resource/getSpreaderToolsByLabel',
    method: 'post',
    data: data
  })
}

export function getAllSpreaderTools(data) {
  return request({
    url: '/Resource/getAllSpreaderTools',
    method: 'post',
    data: data
  })
}

export function updateMeasuringTool(data) {
  return request({
    url: '/Resource/updateMeasuringTool',
    method: 'post',
    data: data
  })
}

export function createMeasuringTool(data) {
  return request({
    url: '/Resource/createMeasuringTool',
    method: 'post',
    data: data
  })
}

export function getMeasuringToolsByParams(data) {
  return request({
    url: '/Resource/getMeasuringToolsByParams',
    method: 'post',
    data: data
  })
}

export function getAllMeasuringToolsByLabel(data) {
  return request({
    url: '/Resource/getMeasuringToolsByLabel',
    method: 'post',
    data: data
  })
}

export function getAllMeasuringTools(data) {
  return request({
    url: '/Resource/getAllMeasuringTools',
    method: 'post',
    data: data
  })
}
export function updateFixtureTool(data) {
  return request({
    url: '/Resource/updateFixtureTool',
    method: 'post',
    data: data
  })
}

export function createFixtureTool(data) {
  return request({
    url: '/Resource/createFixtureTool',
    method: 'post',
    data: data
  })
}

export function getFixtureToolsByParams(data) {
  return request({
    url: '/Resource/getFixtureToolsByParams',
    method: 'post',
    data: data
  })
}

export function getAllFixtureToolsByLabel(data) {
  return request({
    url: '/Resource/getFixtureToolsByLabel',
    method: 'post',
    data: data
  })
}

export function getAllFixtureTools(data) {
  return request({
    url: '/Resource/getAllFixtureTools',
    method: 'post',
    data: data
  })
}

export function updateSpecificResource(data) {
  return request({
    url: '/Resource/updateSpecificResource',
    method: 'post',
    data: data
  })
}

export function createSpecificResource(data) {
  return request({
    url: '/Resource/createSpecificResource',
    method: 'post',
    data: data
  })
}

export function getSpecificResourcesByParams(data) {
  return request({
    url: '/Resource/getSpecificResourcesByParams',
    method: 'post',
    data: data
  })
}

export function getAllSpecificResourcesByLabel(data) {
  return request({
    url: '/Resource/getSpecificResourcesByLabel',
    method: 'post',
    data: data
  })
}

export function getAllSpecificResources(data) {
  return request({
    url: '/Resource/getAllSpecificResources',
    method: 'post',
    data: data
  })
}

export function updateEquipmentResource(data) {
  return request({
    url: '/Resource/updateEquipmentResource',
    method: 'post',
    data: data
  })
}

export function createEquipmentResource(data) {
  return request({
    url: '/Resource/createEquipmentResource',
    method: 'post',
    data: data
  })
}

export function getEquipmentResourcesByParams(data) {
  return request({
    url: '/Resource/getEquipmentResourcesByParams',
    method: 'post',
    data: data
  })
}

export function getAllEquipmentResourcesByLabel(data) {
  return request({
    url: '/Resource/getEquipmentResourcesByLabel',
    method: 'post',
    data: data
  })
}

export function getAllEquipmentResources(data) {
  return request({
    url: '/Resource/getAllEquipmentResources',
    method: 'post',
    data: data
  })
}

export function getAllResources(data) {
  return request({
    url: '/Resource/getAllResources',
    method: 'post',
    data: data
  })
}

export function getAllResourcesByLabel(data) {
  return request({
    url: '/Resource/getAllResourcesByLabel',
    method: 'post',
    data: data
  })
}

export function getResourcesByParams(data) {
  return request({
    url: '/Resource/getResourcesByParams',
    method: 'post',
    data: data
  })
}

export function updateAuxiliaryResource(data) {
  return request({
    url: '/Resource/updateAuxiliaryResource',
    method: 'post',
    data: data
  })
}

export function createAuxiliaryResource(data) {
  return request({
    url: '/Resource/createAuxiliaryResource',
    method: 'post',
    data: data
  })
}

export function getAllAuxiliaryResources(data) {
  return request({
    url: '/Resource/getAllAuxiliaryResources',
    method: 'post',
    data: data
  })
}

export function getAllAuxiliaryResourcesByLabel(data) {
  return request({
    url: '/Resource/getAllAuxiliaryResourcesByLabel',
    method: 'post',
    data: data
  })
}

export function getAuxiliaryResourcesByParams(data) {
  return request({
    url: '/Resource/getAuxiliaryResourcesByParams',
    method: 'post',
    data: data
  })
}

export function getResourceById(resourceId) {
  return request({
    url: '/Resource/getResourceById/' + resourceId,
    method: 'get'
  })
}

export function createResource(data) {
  return request({
    url: '/Resource/createResource',
    method: 'post',
    data: data
  })
}

export function updateResource(data) {
  return request({
    url: '/Resource/updateResource',
    method: 'post',
    data: data
  })
}

export function deleteResource(resourceId) {
  return request({
    url: '/Resource/deleteResource/' + resourceId,
    method: 'get'
  })
}
