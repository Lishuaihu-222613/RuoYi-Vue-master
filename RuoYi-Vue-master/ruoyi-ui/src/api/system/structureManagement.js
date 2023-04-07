import request from '@/utils/request'

//根据类型获得产品
export function getAllProductsByType(query) {
  return request({
    url: '/structure/getAllProductsByType',
    method: 'get',
    params: query
  })
}

export function getProductById(structureId) {
  return request({
    url: '/structure/getProductById'+structureId,
    method: 'get'
  })
}
