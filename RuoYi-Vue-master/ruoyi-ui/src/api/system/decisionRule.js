import request from '@/utils/request'

export function getAllDecisionRules(data) {
  return request({
    url: '/decisionRule/getAllRules',
    method: 'post',
    data: data
  })
}

export function getAllDecisionRulesByLabel(data) {
  return request({
    url: '/decisionRule/getAllRulesByLabel',
    method: 'post',
    data: data
  })
}

export function getDecisionRulesByParams(data) {
  return request({
    url: '/decisionRule/getDecisionRulesByParams',
    method: 'post',
    data: data
  })
}

export function deleteDecisionRule(data) {
  return request({
    url: '/decisionRule/deleteDecisionRule',
    method: 'post',
    data: data
  })
}

export function updateDecisionRule(data) {
  return request({
    url: '/decisionRule/updateDecisionRule',
    method: 'post',
    data: data
  })
}

export function createDecisionRule(data) {
  return request({
    url: '/decisionRule/createDecisionRule',
    method: 'post',
    data: data
  })
}
