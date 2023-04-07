import request from '@/utils/request'

export function getAllPrescriptions(data) {
  return request({
    url: '/prescription/getAllPrescriptions',
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
    url: '/prescription/getPrescriptionsByParams',
    method: 'post',
    data: data
  })
}

export function getPrescriptionById(prescriptionId) {
  return request({
    url: '/prescription/getPrescriptionById/' + prescriptionId,
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

export function deletePrescription(prescriptionId) {
  return request({
    url: '/prescription/deletePrescription/' + prescriptionId,
    method: 'get'
  })

}
