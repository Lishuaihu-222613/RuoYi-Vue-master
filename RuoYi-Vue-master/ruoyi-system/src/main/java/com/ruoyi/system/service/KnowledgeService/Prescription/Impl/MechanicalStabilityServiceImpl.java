package com.ruoyi.system.service.KnowledgeService.Prescription.Impl;

import com.ruoyi.system.Repository.KnowledgeRepository.Prescription.MechanicalStabilityRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.MechanicalStability;
import com.ruoyi.system.service.KnowledgeService.Prescription.MechanicalStabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MechanicalStabilityServiceImpl implements MechanicalStabilityService {

    @Autowired
    private MechanicalStabilityRepository mechanicalStabilityRepository;

    @Override
    public MechanicalStability createMechanicalStability(MechanicalStability stability) {
        return null;
    }

    @Override
    public MechanicalStability updateMechanicalStability(MechanicalStability stability) {
        return null;
    }

    @Override
    public MechanicalStability getMechanicalStability(Long stabilityId) {
        return null;
    }

    @Override
    public Collection<MechanicalStability> getMechanicalStabilityByPrescriptionId(Long prescriptionId) {
        return null;
    }

    @Override
    public Collection<MechanicalStability> getMechanicalStabilityByName(String stabilityName) {
        return null;
    }

    @Override
    public void deleteMechanicalStabilityById(Long stabilityId) {

    }
    @Override
    public void deleteMechanicalStability(MechanicalStability stability) {

    }
}
