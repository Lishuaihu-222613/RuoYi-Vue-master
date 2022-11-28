package com.ruoyi.system.service.KnowledgeService.Prescription.Impl;

import com.ruoyi.system.Repository.KnowledgeRepository.Prescription.ExplosionStabilityRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.ExplosionStability;
import com.ruoyi.system.service.KnowledgeService.Prescription.ExplosionStabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ExplosionStabilityServiceImpl implements ExplosionStabilityService {

    @Autowired
    private ExplosionStabilityRepository explosionStabilityRepository;

    @Override
    public ExplosionStability createExplosionStability(ExplosionStability stability) {
        return explosionStabilityRepository.save(stability);
    }

    @Override
    public ExplosionStability updateExplosionStability(ExplosionStability stability) {
        return explosionStabilityRepository.save(stability);
    }

    @Override
    public ExplosionStability getExplosionStabilityById(Long stabilityId) {
        return explosionStabilityRepository.findById(stabilityId).get();
    }

    @Override
    public ExplosionStability getExplosionStabilityByPrescription(Long prescriptionId) {
        return explosionStabilityRepository.findExplosionStabilityByPrescription(prescriptionId).get();
    }

    @Override
    public Collection<ExplosionStability> getExplosionStabilityByName(String stabilityName) {
        return explosionStabilityRepository.findExplosionStabilityByName(stabilityName);
    }

    @Override
    public void deleteExplosionStabilityById(Long stabilityId) {
        explosionStabilityRepository.deleteById(stabilityId);
    }

    @Override
    public void deleteExplosionStability(ExplosionStability stability) {
        explosionStabilityRepository.delete(stability);
    }
}
