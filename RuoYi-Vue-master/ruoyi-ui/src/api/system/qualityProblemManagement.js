import request from '@/utils/request'

export function getAllQualityProblems(data) {
  return request({
    url: '/problem/getAllQualityProblems',
    method: 'post',
    data: data
  })
}

export function getAllQualityProblemsByLabel(data) {
  return request({
    url: '/problem/getAllQualityProblemsByLabel',
    method: 'post',
    data: data
  })
}

export function getQualityProblemsByParams(data) {
  return request({
    url: '/problem/getQualityProblemsByParams',
    method: 'post',
    data: data
  })
}

export function getQualityProblemById(problemId) {
  return request({
    url: '/problem/getQualityProblemById/' + problemId,
    method: 'get'
  })
}

export function createQualityProblem(data) {
  return request({
    url: '/problem/createQualityProblem',
    method: 'post',
    data: data
  })
}

export function updateQualityProblem(data) {
  return request({
    url: '/problem/updateQualityProblem',
    method: 'post',
    data: data
  })
}

export function changeRelations(data) {
  return request({
    url: '/problem/changeRelations',
    method: 'post',
    data: data
  })
}

export function deleteQualityProblem(problemId) {
  return request({
    url: '/problem/deleteQualityProblem/' + problemId,
    method: 'get'
  })
}
