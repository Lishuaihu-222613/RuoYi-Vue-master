import request from '@/utils/request'

export function getFilesByParams(data) {
  return request({
    url: '/fileManagement/getFilesByParams',
    method: 'post',
    data: data
  })
}

export function getAllFilesByLabel(data) {
  return request({
    url: '/fileManagement/getAllFilesByLabel',
    method: 'post',
    data: data
  })
}

export function getFilesByRelatedId(relatedId) {
  return request({
    url: '/fileManagement/getFilesByRelatedId/'+relatedId,
    method: 'get'
  })
}


export function getFileOptionsByLabel(label) {
  return request({
    url: '/fileManagement/getFileOptionsByLabel/'+label,
    method: 'get'
  })
}

export function getAllModelFile() {
  return request({
    url: '/fileManagement/getAllModelFile',
    method: 'get'
  })
}

export function getAllFiles(data) {
  return request({
    url: '/fileManagement/getAllFiles',
    method: 'post',
    data: data
  })
}

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
    url: '/fileManagement/deleteFile',
    method: 'post',
    data: data
  })
}

export function getFileInfoById(fileId) {
  return request({
    url: '/fileManagement/getFileInfoById'+fileId,
    method: 'get',
  })
}

export function getModelList() {
  return request({
    url: '/fileManagement/getModelList',
    method: 'get',
  })
}

export function getFileList() {
  return request({
    url: '/fileManagement/getFileList',
    method: 'get',
  })
}

export function getModelFileByStructure(elementId) {
  return request({
    url: '/fileManagement/getModelFileByStructure/'+elementId,
    method: 'get',
  })
}

export function createFile(data) {
  return request({
    url: '/fileManagement/createFile',
    method: 'post',
    data: data
  })
}

export function updateFile(data) {
  return request({
    url: '/fileManagement/updateFile',
    method: 'post',
    data: data
  })
}

