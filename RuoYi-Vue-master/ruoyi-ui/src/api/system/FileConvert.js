import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listUserruoyi(query) {
  return request({
    url: '/system/userruoyi/list',
    method: 'get',
    params: query
  })
}
