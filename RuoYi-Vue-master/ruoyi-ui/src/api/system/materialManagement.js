import request from '@/utils/request'

//获取材料数据
export function getAllMaterialsByType(data){
  return request({
    url: '/material/getAllMaterialsByType',
    method: 'post',
    data: data
  })
}

export function getMaterialsByParams(data){
  return request({
    url: '/material/getMaterialsByParams',
    method: 'post',
    data: data
  })
}

export function getAllMaterials(data){
  return request({
    url: '/material/getAllMaterials',
    method: 'post',
    data: data
  })
}

export function deleteMaterial(data){
  return request({
    url: '/material/deleteMaterial',
    method: 'post',
    data: data
  })
}

export function getAllMaterialOptions(){
  return request({
    url: '/material/getAllMaterialOptions',
    method: 'get'
  })
}

export function getMaterialOptionsByLabel(label){
  return request({
    url: '/material/getAllMaterialOptions/'+label,
    method: 'get'
  })
}


export function getMaterialById(materialId){
  return request({
    url: '/material/getMaterialById/'+materialId,
    method: 'get'
  })
};

export function createMaterial(data){
  return request({
    url: '/material/createMaterial',
    method: 'post',
    data: data
  })
};

export function updateMaterial(data){
  return request({
    url: '/material/updateMaterial',
    method: 'post',
    data: data
  })
};

export function updateSingleMaterial(data){
  return request({
    url: '/material/updateSingleMaterial',
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

export function getDangersByMaterialId(materialId){
  return request({
    url: '/material/getDangersByMaterialId/'+materialId,
    method: 'get'
  })
};

export function createDanger(materialId){
  return request({
    url: '/material/createDanger/'+materialId,
    method: 'get',
  })
};

export function updateDanger(data){
  return request({
    url: '/material/updateDanger',
    method: 'post',
    data: data
  })
};

export function deleteDangerById(dangerId){
  return request({
    url: '/material/deleteDanger/'+dangerId,
    method: 'get',
  })
};

export function getProtectionsByMaterialId(materialId){
  return request({
    url: '/material/getProtectionsByMaterialId/'+materialId,
    method: 'get'
  })
};

export function createProtection(materialId){
  return request({
    url: '/material/createProtection/'+materialId,
    method: 'get',
  })
};

export function updateProtection(data){
  return request({
    url: '/material/updateProtection',
    method: 'post',
    data: data
  })
};

export function deleteProtectionById(protectionId){
  return request({
    url: '/material/deleteProtection/'+protectionId,
    method: 'get'
  })
};

export function createProduceMethod(materialId){
  return request({
    url: '/material/createProduceMethod/'+materialId,
    method: 'get',
  })
};
export function getProduceMethodsByMaterialId(materialId){
  return request({
    url: '/material/getProduceMethodsByMaterialId/'+materialId,
    method: 'get'
  })
};

export function updateProduceMethod(data){
  return request({
    url: '/material/updateProduceMethod',
    method: 'post',
    data: data
  })
};

export function deleteProduceMethodById(methodId){
  return request({
    url: '/material/deleteProduceMethodById/'+methodId,
    method: 'get'
  })
};

export function getInspectProjectsByMaterialId(materialId){
  return request({
    url: '/material/getInspectProjectsByMaterialId/'+materialId,
    method: 'get'
  })
};

export function createInspectProject(materialId){
  return request({
    url: '/material/createInspectProject/'+materialId,
    method: 'get',
  })
};

export function updateInspectProjectById(data){
  return request({
    url: '/material/updateInspectProjectById',
    method: 'post',
    data: data
  })
};

export function deleteInspectProjectById(projectId){
  return request({
    url: '/material/deleteProtection/'+projectId,
    method: 'get'
  })
};





