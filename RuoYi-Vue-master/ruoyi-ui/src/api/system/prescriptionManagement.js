import request from '@/utils/request'

export function getAllPrescriptions(data) {
  return request({
    url: '/prescription/getAllPrescriptions',
    method: 'post',
    data: data
  })
}

export function addMaterialElement(data) {
  return request({
    url: '/prescription/addMaterialElementForProscription',
    method: 'post',
    data: data
  })
}

export function getAllPrescriptionsByLabel(data) {
  return request({
    url: '/prescription/getAllPrescriptionsByLabel',
    method: 'post',
    data: data
  })
}

export function getPrescriptionsByParams(data) {
  return request({
    url: '/prescription/getAllPrescriptionsByParams',
    method: 'post',
    data: data
  })
}

export function getElementsByPrescriptionId(prescriptionId) {
  return request({
    url: '/prescription/getElementsByPrescriptionId/' + prescriptionId,
    method: 'get'
  })
}

export function getProductsByPrescriptionId(prescriptionId) {
  return request({
    url: '/prescription/getProductsByPrescriptionId/' + prescriptionId,
    method: 'get'
  })
}

export function getProcessByPrescriptionId(prescriptionId) {
  return request({
    url: '/prescription/getProcesssByPrescriptionId/' + prescriptionId,
    method: 'get'
  })
}

export function getCombustionPropertyByPId(prescriptionId) {
  return request({
    url: '/prescription/getCombustionPropertyByPrescriptionId/' + prescriptionId,
    method: 'get'
  })
}

export function getEnergyPropertyByPId(prescriptionId) {
  return request({
    url: '/prescription/getEnergyPropertyByPrescriptionId/' + prescriptionId,
    method: 'get'
  })
}

export function getForcePropertyByPId(prescriptionId) {
  return request({
    url: '/prescription/getForcePropertyByPrescriptionId/' + prescriptionId,
    method: 'get'
  })
}

export function getStoragePropertyByPId(prescriptionId) {
  return request({
    url: '/prescription/getStoragePropertyByPrescriptionId/' + prescriptionId,
    method: 'get'
  })
}

export function getOxidantRatioByPId(prescriptionId) {
  return request({
    url: '/prescription/getOxidantRatioByPrescriptionId/' + prescriptionId,
    method: 'get'
  })
}

export function getPrescriptionById(prescriptionId) {
  return request({
    url: '/prescription/getPrescriptionById/' + prescriptionId,
    method: 'get'
  })
}

export function getHeatStabilityByPId(prescriptionId) {
  return request({
    url: '/prescription/getHeatStabilityByPrescriptionId/' + prescriptionId,
    method: 'get'
  })
}

export function getMechanicalStabilityByPId(prescriptionId) {
  return request({
    url: '/prescription/getMechanicalStabilityByPrescriptionId/' + prescriptionId,
    method: 'get'
  })
}

export function getRadioStabilityByPId(prescriptionId) {
  return request({
    url: '/prescription/getRadioStabilityByPrescriptionId/' + prescriptionId,
    method: 'get'
  })
}

export function getExplosionStabilityByPId(prescriptionId) {
  return request({
    url: '/prescription/getExplosionStabilityByPrescriptionId/' + prescriptionId,
    method: 'get'
  })
}

export function createPrescription(data) {
  return request({
    url: '/prescription/createPrescription',
    method: 'post',
    data: data
  })
}

export function updatePrescription(data) {
  return request({
    url: '/prescription/updatePrescription',
    method: 'post',
    data: data
  })
}

export function updateCombustionProperty(data) {
  return request({
    url: '/prescription/updateCombustionProperty',
    method: 'post',
    data: data
  })
}

export function updateEnergyProperty(data) {
  return request({
    url: '/prescription/updateEnergyProperty',
    method: 'post',
    data: data
  })
}

export function updateForceProperty(data) {
  return request({
    url: '/prescription/updateForceProperty',
    method: 'post',
    data: data
  })
}

export function updateOxidantRatio(data) {
  return request({
    url: '/prescription/updateOxidantRatio',
    method: 'post',
    data: data
  })
}

export function updateStorageProperty(data) {
  return request({
    url: '/prescription/updateStorageProperty',
    method: 'post',
    data: data
  })
}

export function updateHeatStability(data) {
  return request({
    url: '/prescription/updateHeatStability',
    method: 'post',
    data: data

  })
}

export function updateMechanicalStability(data) {
  return request({
    url: '/prescription/updateMechanicalStability',
    method: 'post',
    data: data
  })
}

export function updateRadioStability(data) {
  return request({
    url: '/prescription/updateRadioStability',
    method: 'post',
    data: data
  })
}

export function updateExplosionStability(data) {
  return request({
    url: '/prescription/updateExplosionStability',
    method: 'post',
    data: data
  })
}
export function deletePrescription(prescriptionId) {
  return request({
    url: '/prescription/deletePrescription/' + prescriptionId,
    method: 'get'
  })
}

export function deleteMaterialElementForProscription(prescriptionId) {
  return request({
    url: '/prescription/deleteMaterialElementForProscription/' + prescriptionId,
    method: 'get'
  })
}

export function modifyElements(data) {
  return request({
    url: '/prescription/modifyElements',
    method: 'post',
    data: data
  })
}

export function modifyProducts(data) {
  return request({
    url: '/prescription/modifyProducts',
    method: 'post',
    data: data
  })
}

export function modifyProcesses(data) {
  return request({
    url: '/prescription/modifyProcesses',
    method: 'post',
    data: data
  })
}
