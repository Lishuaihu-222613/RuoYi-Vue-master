import request from '@/utils/request'

export function getAllQualityProblems(data) {
  return request({
    url: '/qualityProblem/getAllProblems',
    method: 'post',
    data: data
  })
}

export function getAllQualityProblemsByLabel(data) {
  return request({
    url: '/qualityProblem/getAllProblemsByLabel',
    method: 'post',
    data: data
  })
}

export function getProblemsByRelatedId(relatedId) {
  return request({
    url: '/qualityProblem/getProblemsByRelatedId/'+relatedId,
    method: 'get',
  })
}

export function getQualityProblemOptionsByLabel(label) {
  return request({
    url: '/qualityProblem/getQualityProblemOptionsByLabel/'+label,
    method: 'get',
  })
}

export function getQualityProblemsByParams(data) {
  return request({
    url: '/qualityProblem/getQualityProblemsByParams',
    method: 'post',
    data: data
  })
}

export function getQualityProblemById(problemId) {
  return request({
    url: '/qualityProblem/getProblemById/' + problemId,
    method: 'get'
  })
}

export function createQualityProblem(data) {
  return request({
    url: '/qualityProblem/createProblem',
    method: 'post',
    data: data
  })
}

export function updateQualityProblem(data) {
  return request({
    url: '/qualityProblem/updateProblem',
    method: 'post',
    data: data
  })
}


export function deleteQualityProblem(problemId) {
  return request({
    url: '/qualityProblem/deleteQualityProblem/' + problemId,
    method: 'get'
  })
}
