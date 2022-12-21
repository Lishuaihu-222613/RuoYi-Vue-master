import request from '@/utils/request'

//获取材料数据
export function getAllMaterialsByType(){
  return request({
    url: '/static/kgData.json',
    method: 'get'
  })
};

export function getMaterialById(materialId){
  return request({
    url: '/material/getMaterialById/'+materialId,
    method: 'get'
  })
};

export function updateMaterial(data){
  return request({
    url: '/material/updateMaterial',
    method: 'post',
    data: data
  })
};

export function getAnalysisSpectrogramByMaterialId(materialId){
  return request({
    url: '/material/getAnalysisSpectrogramByMaterialId/'+materialId,
    method: 'get'
  })
};

export function updateAnalysisSpectrogram(data){
  return request({
    url: '/material/updateAnalysisSpectrogram',
    method: 'post',
    data: data
  })
};

export function getChemicalPropertyByMaterialId(materialId){
  return request({
    url: '/material/getChemicalPropertyByMaterialId/'+materialId,
    method: 'get'
  })
};

export function updateChemicalProperty(data){
  return request({
    url: '/material/updateChemicalProperty',
    method: 'post',
    data: data
  })
};

export function getPhysicalPropertyByMaterialId(materialId){
  return request({
    url: '/material/getPhysicalPropertyByMaterialId/'+materialId,
    method: 'get'
  })
};

export function updatePhysicalProperty(data){
  return request({
    url: '/material/updatePhysicalProperty',
    method: 'post',
    data: data
  })
};
