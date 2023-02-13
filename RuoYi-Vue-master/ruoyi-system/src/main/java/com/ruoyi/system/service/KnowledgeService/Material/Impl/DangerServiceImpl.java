package com.ruoyi.system.service.KnowledgeService.Material.Impl;

import com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge.DangerRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Danger;
import com.ruoyi.system.service.KnowledgeService.Material.DangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

@Service
public class DangerServiceImpl implements DangerService {

    @Resource
    private DangerRepository dangerRepository;

    @Override
    public Collection<Danger> getDangersByMaterialId(Long materialId) {
        return dangerRepository.findDangersByMaterialId(materialId);
    }

    @Override
    public Danger getDangerById(Long dangerId) {
        return dangerRepository.findById(dangerId).get();
    }

    @Override
    public Danger createDanger(Danger danger) {
        return dangerRepository.save(danger);
    }

    @Override
    public Danger updateDanger(Danger danger) {
        return dangerRepository.save(danger);
    }

    @Override
    public void deleteDanger(Danger danger) {
        dangerRepository.delete(danger);
    }

    @Override
    public void deleteDangerById(Long dangerId) {
        dangerRepository.deleteById(dangerId);
    }

    @Override
    public void createRelationshipForDanger(Long materialId, Long dangerId) {
        dangerRepository.createRelationshipForDanger(materialId, dangerId);
    }
}
