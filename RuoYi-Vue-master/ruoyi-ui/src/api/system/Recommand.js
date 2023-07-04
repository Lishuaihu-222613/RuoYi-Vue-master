import request from '@/utils/request'


export function getRecommandProcess(data) {
  return request({
    url: '/recommand/getRecommandProcess',
    method: 'post',
    data: data
  })
}

export function getRecommandSequence(data) {
  return request({
    url: '/recommand/getRecommandSequence',
    method: 'post',
    data: data
  })
}

export function getRecommandStep(data) {
  return request({
    url: '/recommand/getRecommandStep',
    method: 'post',
    data: data
  })
}

export function getRecommandResource(data) {
  return request({
    url: '/recommand/getRecommandResource',
    method: 'post',
    data: data
  })
}
