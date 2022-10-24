import request from '@/utils/request'

// 文件上传
export function fileUpload(data) {
  return request({
    url: '/fileManagement/upload',
    method: 'post',
    data: data
  })
}

// 文件下载
export function fileDownload(query) {
  return request({
    url: '/fileManagement/download',
    method: 'get',
    params:query
  })
}

// 文件删除
export function fileDelete(data) {
  return request({
    url: '/fileManagement/delete',
    method: 'post',
    data: data
  })
}
