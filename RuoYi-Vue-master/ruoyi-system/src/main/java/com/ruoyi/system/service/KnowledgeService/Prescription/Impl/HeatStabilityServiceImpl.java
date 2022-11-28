package com.ruoyi.system.service.KnowledgeService.Prescription.Impl;

import com.ruoyi.system.Repository.KnowledgeRepository.Prescription.HeatStabilityRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.HeatStability;
import com.ruoyi.system.service.KnowledgeService.Prescription.HeatStabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class HeatStabilityServiceImpl implements HeatStabilityService {

    @Autowired
    private HeatStabilityRepository heatStabilityRepository;

    @Override
    public HeatStability createHeatStability(HeatStability stability) {
        return heatStabilityRepository.save(stability);
    }

    @Override
    public HeatStability updateHeatStability(HeatStability stability) {
        return heatStabilityRepository.save(stability);
    }

    @Override
    public HeatStability getHeatStabilityById(Long stabilityId) {
        return heatStabilityRepository.findById(stabilityId).get();
    }

    @Override
    public HeatStability getHeatStabilityByPrescriptionId(Long prescriptionId) {
        return heatStabilityRepository.findHeatStabilityByPrescription(prescriptionId).get();
    }

    @Override
    public Collection<HeatStability> getHeatStabilityByName(String stabilityName) {
        return heatStabilityRepository.findHeatStabilityByName(stabilityName);
    }

    @Override
    public void deleteHeatStabilityById(Long stabilityId) {
        heatStabilityRepository.deleteById(stabilityId);
    }

    @Override
    public void deleteHeatStability(HeatStability stability) {
        heatStabilityRepository.delete(stability);
    }
}
