package com.ruoyi.system.service.KgBuilderService.impl;

import com.ruoyi.system.domain.KgBuilderPojo.entity.KgDomain;
import com.ruoyi.system.domain.KgBuilderPojo.entity.KgNodeDetail;
import com.ruoyi.system.domain.KgBuilderPojo.entity.KgNodeDetailFile;
import com.ruoyi.system.mapper.KgBuilderMapper.KnowledgeGraphDao;
import com.ruoyi.system.service.KgBuilderService.KnowledgeGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class KnowledgeGraphServiceImpl implements KnowledgeGraphService {

    @Autowired
    KnowledgeGraphDao knowledgeGraphDao;

    @Override
    public List<KgDomain> getDomains() {
        return knowledgeGraphDao.getDomains();
    }

    @Override
    public List<KgDomain> getRecommendDomainList() {
        return knowledgeGraphDao.getRecommendDomainList();
    }

    @Override
    public List<KgDomain> getDomainList(String domainName,Integer type,Integer commend) {
        return knowledgeGraphDao.getDomainList(domainName,type,commend);
    }

    @Override
    public Integer saveDomain(KgDomain map) {
        knowledgeGraphDao.saveDomain(map);
        return map.getId();
    }

    @Override
    public Integer quickCreateDomain(String domain,Integer type) {
        KgDomain item = new KgDomain();
        item.setName(domain);
        item.setNodecount(0);
        item.setShipcount(0);
        item.setCreateuser("tc");
        item.setCommend(0);
        item.setType(type);
        item.setStatus(1);
       return  saveDomain(item);
    }

    @Override
    public void updateDomain(KgDomain map) {
        knowledgeGraphDao.saveDomain(map);
    }

    @Override
    public void deleteDomain(Integer id) {
        knowledgeGraphDao.deleteDomain(id);
    }

    @Override
    public List<KgDomain> getDomainByName(String domainName) {
        return knowledgeGraphDao.getDomainByName(domainName);
    }

    @Override
    public List<KgDomain> getDomainById(Integer domainId) {
        return knowledgeGraphDao.getDomainById(domainId);
    }

    @Override
    public KgDomain selectById(Integer domainId) {
        return knowledgeGraphDao.selectById(domainId);
    }

    @Override
    public void saveNodeImage(List<Map<String, Object>> mapList) {
        knowledgeGraphDao.saveNodeImage(mapList);
    }

    @Override
    public void saveNodeContent(Map<String, Object> map) {
        knowledgeGraphDao.saveNodeContent(map);
    }

    @Override
    public void updateNodeContent(Map<String, Object> map) {
        knowledgeGraphDao.updateNodeContent(map);
    }

    @Override
    public List<KgNodeDetailFile> getNodeImageList(Integer domainId, Integer nodeId) {
        return knowledgeGraphDao.getNodeImageList(domainId,nodeId);
    }

    @Override
    public List<KgNodeDetail> getNodeContent(Integer domainId, Integer nodeId) {
        return knowledgeGraphDao.getNodeContent(domainId,nodeId);
    }

    @Override
    public void deleteNodeImage(Integer domainId, Integer nodeId) {
        knowledgeGraphDao.deleteNodeImage(domainId,nodeId);
    }
}
