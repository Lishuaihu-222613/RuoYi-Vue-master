import request from '@/utils/request'

export function getSingleElementById(elementId){
  return request({
    url: '/process/getSingleElementsById/'+elementId,
    method: 'get'
  })
}

export function getParentElementById(elementId){
  return request({
    url: '/process/getParentElementById/'+elementId,
    method: 'get'
  })
}


export function getElementById(elementId){
  return request({
    url: '/process/getElementById/'+elementId,
    method: 'get'
  })
}

export function getSubElementsById(elementId){
  return request({
    url: '/process/getSubElementsById/'+elementId,
    method: 'get'
  })
}

export function getBeforeElements(elementId){
  return request({
    url: '/process/getBeforeElementsById/'+elementId,
    method: 'get'
  })
}

export function getAfterElements(elementId){
  return request({
    url: '/process/getAfterElementsById/'+elementId,
    method: 'get'
  })
}

export function getAndElements(elementId){
  return request({
    url: '/process/getAndElementsById/'+elementId,
    method: 'get'
  })
}

export function getOrElements(elementId){
  return request({
    url: '/process/getOrElementsById/'+elementId,
    method: 'get'
  })
}

export function createElement(data){
  return request({
    url: '/process/createElement',
    method: 'post',
    data:data
  })
}

export function modifyBeforeElement(data){
  return request({
    url: '/process/modifyBeforeElement',
    method: 'post',
    data:data
  })
}

export function modifyAfterElement(data){
  return request({
    url: '/process/modifyAfterElement',
    method: 'post',
    data:data
  })
}

export function modifyAndElement(data){
  return request({
    url: '/process/modifyAndElement',
    method: 'post',
    data:data
  })
}



export function modifyOrElement(data){
  return request({
    url: '/process/modifyOrElement',
    method: 'post',
    data:data
  })
}

export function createElementForParent(data){
  return request({
    url: '/process/createElementForParent',
    method: 'post',
    data:data
  })
}

export function updateElement(data){
  return request({
    url: '/process/updateElement',
    method: 'post',
    data:data
  })
}

export function updateRelation(data){
  return request({
    url: '/process/updateRelation',
    method: 'post',
    data:data
  })
}


export function getAllProcess(){
  return request({
    url: '/process/getAllProcess',
    method: 'get'
  })
}

export function getAllProcessOptions(){
  return request({
    url: '/process/getAllProcessOptions',
    method: 'get'
  })
}

export function getAllWholeProcess(){
  return request({
    url: '/process/getAllProcess',
    method: 'get'
  })
}

export function getAllProcessByLabel(data){
  return request({
    url: '/process/getProcessListsByLabel',
    method: 'post',
    data:data
  })
}

export function getAllElementsByParams(data){
  return request({
    url: '/process/getElementsByParams',
    method: 'post',
    data:data
  })
}

export function deleteElements(data){
  return request({
    url: '/process/deleteElements',
    method: 'post',
    data:data
  })
}

export function getProcessOptionsByLabel(label){
  return request({
    url: '/process/getProcessOptionsByLabel/'+label,
    method: 'get'
  })
}

export function getProcessById(data){
  return request({
    url: '/process/getProcessById',
    method: 'post',
    data:data
  })
}

export function createProcess(data){
  return request({
    url: '/process/createProcess',
    method: 'post',
    data: data
  })
};

export function updateProcess(data){
  return request({
    url: '/process/updateProcess',
    method: 'post',
    data: data
  })
};

export function deleteProcessById(processId){
  return request({
    url: '/process/deleteProcess/'+processId,
    method: 'get'
  })
};

export function getSequenceByProcess(processId){
  return request({
    url: '/process/getSequenceByProcess/'+processId,
    method: 'get'
  })
};

export function addSequenceForProcess(data){
  return request({
    url: '/process/addSequenceForProcess',
    method: 'post',
    data: data
  })
};

export function getSequenceById(sequenceId){
  return request({
    url: '/process/getSequenceById/'+sequenceId,
    method: 'get'
  })
};

export function createSequence(data){
  return request({
    url: '/process/createSequence',
    method: 'post',
    data: data
  })
};

export function updateSequence(data){
  return request({
    url: '/process/updateSequence',
    method: 'post',
    data: data
  })
};

export function deleteSequenceById(sequenceId){
  return request({
    url: '/process/deleteSequence/'+sequenceId,
    method: 'get'
  })
};

export function removeSequenceForProcess(params){
  return request({
    url: '/process/deleteSequenceById/',
    method: 'get',
    params:params
  })
};

export function addBeforeSequence(data){
  return request({
    url: '/process/addBeforeSequence',
    method: 'post',
    data: data
  })
};

export function removeBeforeSequence(params){
  return request({
    url: '/process/removeBeforeSequence/',
    method: 'get',
    params:params
  })
};

export function modifyBeforeSequence(data){
  return request({
    url: '/process/modifyBeforeSequence',
    method: 'post',
    data: data
  })
};

export function addAfterSequence(data){
  return request({
    url: '/process/addAfterSequence',
    method: 'post',
    data: data
  })
};

export function removeAfterSequence(params){
  return request({
    url: '/process/removeAfterSequence/',
    method: 'get',
    params:params
  })
};

export function modifyAfterSequence(data){
  return request({
    url: '/process/modifyAfterSequence',
    method: 'post',
    data: data
  })
};

export function addAndSequence(data){
  return request({
    url: '/process/addAndSequence',
    method: 'post',
    data: data
  })
};

export function removeAndSequence(params){
  return request({
    url: '/process/removeAndSequence/',
    method: 'get',
    params:params
  })
};

export function modifyAndSequence(data){
  return request({
    url: '/process/modifyAndSequence',
    method: 'post',
    data: data
  })
};

export function addOrSequence(data){
  return request({
    url: '/process/addOrSequence',
    method: 'post',
    data: data
  })
};

export function removeOrSequence(params){
  return request({
    url: '/process/removeOrSequence/',
    method: 'get',
    params:params
  })
};

export function modifyOrSequence(data){
  return request({
    url: '/process/modifyOrSequence',
    method: 'post',
    data: data
  })
};

export function addStepForSequence(data){
  return request({
    url: '/process/addStepForSequence',
    method: 'post',
    data: data
  })
};

export function removeStepForSequence(sequenceId,stepId){
  return request({
    url: '/process/removeStepForSequence/'+sequenceId+'/'+stepId,
    method: 'get'
  })
};

export function createStep(data){
  return request({
    url: '/process/createStep',
    method: 'post',
    data: data
  })
};

export function updateStep(data){
  return request({
    url: '/process/updateStep',
    method: 'post',
    data: data
  })
};

export function deleteStepById(stepId){
  return request({
    url: '/process/deleteStepById/'+stepId,
    method: 'get'
  })
};

export function getStepById(stepId){
  return request({
    url: '/process/getStepById/'+stepId,
    method: 'get'
  })
};

export function getStepBySequence(sequenceId){
  return request({
    url: '/process/getStepBySequence/'+sequenceId,
    method: 'get'
  })
};

export function modifyBeforeStep(data){
  return request({
    url: '/process/modifyBeforeStep',
    method: 'post',
    data: data
  })
};
export function modifyAfterStep(data){
  return request({
    url: '/process/modifyAfterStep',
    method: 'post',
    data: data
  })
};

export function modifyAndStep(data){
  return request({
    url: '/process/modifyAndStep',
    method: 'post',
    data: data
  })
};

export function modifyOrStep(data){
  return request({
    url: '/process/modifyOrStep',
    method: 'post',
    data: data
  })
};

export function modifySubStep(data){
  return request({
    url: '/process/modifySubStep',
    method: 'post',
    data: data
  })
};
