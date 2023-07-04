package com.ruoyi.system.service.KnowledgeService.Prescription.Impl;

import com.ruoyi.system.Repository.KnowledgeRepository.MaterialKnowledge.MaterialRepository;
import com.ruoyi.system.Repository.KnowledgeRepository.Prescription.*;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Interface.PrescriptionInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Interface.hasMaterialElementInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Prescription;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.PrescriptionElements;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property.*;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.ExplosionStability;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.HeatStability;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.MechanicalStability;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Stability.RadioStability;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.hasMaterialElement;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.vo.MaterialAndValue;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.vo.PrescriptionAndAElement;
import com.ruoyi.system.service.KnowledgeService.Prescription.PrescriptionService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Resource
    private PrescriptionRepository prescriptionRepository;

    @Resource
    private CombustionPropertyRepository combustionPropertyRepository;

    @Resource
    private EnergyPropertyRepository energyPropertyRepository;

    @Resource
    private ExplosionStabilityRepository explosionStabilityRepository;

    @Resource
    private ForcePropertyRepository forcePropertyRepository;

    @Resource
    private HeatStabilityRepository heatStabilityRepository;

    @Resource
    private MechanicalStabilityRepository mechanicalStabilityRepository;

    @Resource
    private OxidantRatioRepository oxidantRatioRepository;

    @Resource
    private RadioStabilityRepository radioStabilityRepository;

    @Resource
    private StoragePropertyRepository storagePropertyRepository;

    @Resource
    private hasMaterialElementRepository materialElementRepository;

    @Resource
    private MaterialRepository materialRepository;
    @Override
    public Prescription createPrescription(Prescription prescription) {
        Prescription singlePrescription = prescriptionRepository.save(prescription);
        EnergyProperty energyProperty = energyPropertyRepository.save(new EnergyProperty());
        ForceProperty forceProperty = forcePropertyRepository.save(new ForceProperty());
        CombustionProperty combustionProperty = combustionPropertyRepository.save(new CombustionProperty());
        StorageProperty storageProperty = storagePropertyRepository.save(new StorageProperty());
        OxidantRatio oxidantRatio = oxidantRatioRepository.save(new OxidantRatio());
        HeatStability heatStability = heatStabilityRepository.save(new HeatStability());
        MechanicalStability mechanicalStability = mechanicalStabilityRepository.save(new MechanicalStability());
        RadioStability radioStability = radioStabilityRepository.save(new RadioStability());
        ExplosionStability explosionStability = explosionStabilityRepository.save(new ExplosionStability());
        singlePrescription.setEnergyProperty(energyProperty);
        singlePrescription.setForceProperty(forceProperty);
        singlePrescription.setCombustionProperty(combustionProperty);
        singlePrescription.setStorageProperty(storageProperty);
        singlePrescription.setOxidantRatio(oxidantRatio);
        singlePrescription.setHeatStability(heatStability);
        singlePrescription.setMechanicalStability(mechanicalStability);
        singlePrescription.setRadioStability(radioStability);
        singlePrescription.setExplosionStability(explosionStability);
        return prescriptionRepository.save(singlePrescription);
    }

    @Override
    public Prescription updatePrescription(Prescription prescription) {
        Optional<Prescription> optionalPrescription = prescriptionRepository.findById(prescription.getPrescriptionId());
        if(optionalPrescription.isPresent()){
            Prescription oldPrescription = optionalPrescription.get();
            oldPrescription.setPrescriptionName(prescription.getPrescriptionName());
            oldPrescription.setPrescriptionDescription(prescription.getPrescriptionDescription());
            oldPrescription.setDensity(prescription.getDensity());
            oldPrescription.setDangerLevel(prescription.getDangerLevel());
            oldPrescription.setCuringTime(prescription.getCuringTime());
            oldPrescription.setPrescriptionLabels(prescription.getPrescriptionLabels());
            oldPrescription.setOtherProperty(prescription.getOtherProperty());
            Prescription newPrescription = prescriptionRepository.save(oldPrescription);
            return newPrescription;
        }
        return null;
    }

    @Override
    public Prescription getPrescriptionById(Long prescriptionId) {
        return prescriptionRepository.findById(prescriptionId).get();
    }

    @Override
    public Page<Prescription> getAllPrescriptions(Pageable pageable) {
        return prescriptionRepository.findAll(pageable);
    }

    @Override
    public Page<Prescription> getAllPrescriptionsByParams(Example example, Pageable pageable) {
        return prescriptionRepository.findAll(example,pageable);
    }

    @Override
    public Page<Prescription> getPrescriptionsByLabel(String dynamicLabel, Pageable pageable) {
        return prescriptionRepository.findPrescriptionByType(dynamicLabel,pageable) ;
    }

    @Override
    public PrescriptionInterface getPrescriptionInterfaceById(Long prescriptionId) {
        return prescriptionRepository.findPrescriptionInterfaceById(prescriptionId).get();
    }

    @Override
    public List<PrescriptionInterface> getPrescriptionInterfaceByName(String prescriptionName) {
        return new ArrayList<>(prescriptionRepository.findPrescriptionInterfaceByName(prescriptionName));
    }

    @Override
    public void deletePrescriptionById(Long prescriptionId) {
        prescriptionRepository.deleteById(prescriptionId);
    }

    @Override
    public CombustionProperty createCombustionProperty(Long prescriptionId, CombustionProperty property) {
        Optional<Prescription> optionalPrescription = prescriptionRepository.findById(prescriptionId);
        if(optionalPrescription.isPresent()) {
            Prescription prescription = optionalPrescription.get();
            CombustionProperty combustionProperty = combustionPropertyRepository.save(property);
            prescription.setCombustionProperty(combustionProperty);
            prescriptionRepository.save(prescription);
            return combustionProperty;
        }
        return null;
    }

    @Override
    public CombustionProperty updateCombustionProperty(CombustionProperty property) {
        return combustionPropertyRepository.save(property);
    }

    @Override
    public CombustionProperty getCombustionPropertyById(Long propertyId) {
        return combustionPropertyRepository.findById(propertyId).get();
    }

    @Override
    public CombustionProperty getCombustionPropertyByPrescriptionId(Long prescriptionId) {
        return combustionPropertyRepository.findCombustionPropertyByPrescription(prescriptionId).get();
    }

    @Override
    public void deleteCombustionPropertyById(Long propertyId) {
        combustionPropertyRepository.deleteById(propertyId);
    }

    @Override
    public Collection<CombustionProperty> getCombustionPropertyByName(String propertyName) {
        return combustionPropertyRepository.findCombustionPropertyByName(propertyName);
    }

    @Override
    public EnergyProperty createEnergyProperty(Long prescriptionId,EnergyProperty property) {
        Optional<Prescription> optionalPrescription = prescriptionRepository.findById(prescriptionId);
        if(optionalPrescription.isPresent()) {
            Prescription prescription = optionalPrescription.get();
            EnergyProperty energyProperty = energyPropertyRepository.save(property);
            prescription.setEnergyProperty(energyProperty);
            prescriptionRepository.save(prescription);
            return energyProperty;
        }
        return null;
    }

    @Override
    public EnergyProperty updateEnergyProperty(EnergyProperty Property) {
        return energyPropertyRepository.save(Property);
    }

    @Override
    public EnergyProperty getEnergyPropertyById(Long propertyId) {
        return energyPropertyRepository.findById(propertyId).get();
    }

    @Override
    public EnergyProperty getEnergyPropertyByPrescriptionId(Long prescriptionId) {
        return energyPropertyRepository.findEnergyPropertyByPrescription(prescriptionId).get();
    }

    @Override
    public void deleteEnergyPropertyById(Long propertyId) {
        energyPropertyRepository.deleteById(propertyId);
    }

    @Override
    public Collection<EnergyProperty> getEnergyPropertyByName(String propertyName) {
        return energyPropertyRepository.findEnergyPropertyByName(propertyName);
    }

    @Override
    public ExplosionStability createExplosionStability(Long prescriptionId,ExplosionStability stability) {
        Optional<Prescription> optionalPrescription = prescriptionRepository.findById(prescriptionId);
        if(optionalPrescription.isPresent()) {
            Prescription prescription = optionalPrescription.get();
            ExplosionStability explosionStability = explosionStabilityRepository.save(stability);
            prescription.setExplosionStability(explosionStability);
            prescriptionRepository.save(prescription);
            return explosionStability;
        }
        return null;
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
    public ExplosionStability getExplosionStabilityByPrescriptionId(Long prescriptionId) {
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
    public ForceProperty createForceProperty(Long prescriptionId,ForceProperty property) {
        Optional<Prescription> optionalPrescription = prescriptionRepository.findById(prescriptionId);
        if(optionalPrescription.isPresent()) {
            Prescription prescription = optionalPrescription.get();
            ForceProperty forceProperty = forcePropertyRepository.save(property);
            prescription.setForceProperty(forceProperty);
            prescriptionRepository.save(prescription);
            return forceProperty;
        }
        return null;
    }

    @Override
    public ForceProperty updateForceProperty(ForceProperty property) {
        return forcePropertyRepository.save(property);
    }

    @Override
    public ForceProperty getForcePropertyById(Long propertyId) {
        return forcePropertyRepository.findById(propertyId).get();
    }

    @Override
    public ForceProperty getForcePropertyByPrescriptionId(Long prescriptionId) {
        return forcePropertyRepository.findForcePropertyByPrescriptionId(prescriptionId).get();
    }

    @Override
    public void deleteForcePropertyById(Long propertyId) {
        forcePropertyRepository.deleteById(propertyId);
    }

    @Override
    public Collection<ForceProperty> getForcePropertyByName(String propertyName) {
        return forcePropertyRepository.findForcePropertyByName(propertyName);
    }

    @Override
    public HeatStability createHeatStability(Long prescriptionId,HeatStability stability) {
        Optional<Prescription> optionalPrescription = prescriptionRepository.findById(prescriptionId);
        if(optionalPrescription.isPresent()) {
            Prescription prescription = optionalPrescription.get();
            HeatStability heatStability = heatStabilityRepository.save(stability);
            prescription.setHeatStability(heatStability);
            prescriptionRepository.save(prescription);
            return heatStability;
        }
        return null;
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
    public MechanicalStability createMechanicalStability(Long prescriptionId,MechanicalStability stability) {
        Optional<Prescription> optionalPrescription = prescriptionRepository.findById(prescriptionId);
        if(optionalPrescription.isPresent()) {
            Prescription prescription = optionalPrescription.get();
            MechanicalStability mechanicalStability = mechanicalStabilityRepository.save(stability);
            prescription.setMechanicalStability(mechanicalStability);
            prescriptionRepository.save(prescription);
            return mechanicalStability;
        }
        return null;
    }

    @Override
    public MechanicalStability updateMechanicalStability(MechanicalStability stability) {
        return mechanicalStabilityRepository.save(stability);
    }

    @Override
    public MechanicalStability getMechanicalStabilityById(Long stabilityId) {
        return mechanicalStabilityRepository.findById(stabilityId).get();
    }

    @Override
    public MechanicalStability getMechanicalStabilityByPrescriptionId(Long prescriptionId) {
        return mechanicalStabilityRepository.findMechanicalStabilityByPrescription(prescriptionId).get();
    }

    @Override
    public Collection<MechanicalStability> getMechanicalStabilityByName(String stabilityName) {
        return mechanicalStabilityRepository.findMechanicalStabilityByName(stabilityName);
    }

    @Override
    public void deleteMechanicalStabilityById(Long stabilityId) {
        mechanicalStabilityRepository.deleteById(stabilityId);
    }

    @Override
    public OxidantRatio createOxidantRatio(Long prescriptionId,OxidantRatio property) {
        Optional<Prescription> optionalPrescription = prescriptionRepository.findById(prescriptionId);
        if(optionalPrescription.isPresent()) {
            Prescription prescription = optionalPrescription.get();
            OxidantRatio oxidantRatio = oxidantRatioRepository.save(property);
            prescription.setOxidantRatio(oxidantRatio);
            prescriptionRepository.save(prescription);
            return oxidantRatio;
        }
        return null;
    }

    @Override
    public OxidantRatio updateOxidantRatio(OxidantRatio oxidantRatio) {
        return oxidantRatioRepository.save(oxidantRatio);
    }

    @Override
    public OxidantRatio getOxidantRatioById(Long propertyId) {
        return oxidantRatioRepository.findById(propertyId).get();
    }

    @Override
    public OxidantRatio getOxidantRatioByPrescriptionId(Long prescriptionId) {
        return oxidantRatioRepository.findOxidantRatioByPrescription(prescriptionId).get();
    }

    @Override
    public void deleteOxidantRatioById(Long propertyId) {
        oxidantRatioRepository.deleteById(propertyId);
    }

    @Override
    public Collection<OxidantRatio> getOxidantRatioByName(String propertyName) {
        return new ArrayList<>(oxidantRatioRepository.findOxidantRatioByName(propertyName)) ;
    }

    @Override
    public RadioStability createRadioStability(Long prescriptionId,RadioStability stability) {
        Optional<Prescription> optionalPrescription = prescriptionRepository.findById(prescriptionId);
        if(optionalPrescription.isPresent()) {
            Prescription prescription = optionalPrescription.get();
            RadioStability radioStability = radioStabilityRepository.save(stability);
            prescription.setRadioStability(radioStability);
            prescriptionRepository.save(prescription);
            return radioStability;
        }
        return null;
    }

    @Override
    public RadioStability updateRadioStability(RadioStability stability) {
        return radioStabilityRepository.save(stability);
    }

    @Override
    public RadioStability getRadioStabilityById(Long stabilityId) {
        return radioStabilityRepository.findById(stabilityId).get();
    }

    @Override
    public RadioStability getRadioStabilityByPrescriptionId(Long prescriptionId) {
        return radioStabilityRepository.findRadioStabilityByPrescription(prescriptionId).get();
    }

    @Override
    public Collection<RadioStability> getRadioStabilityByName(String prescriptionName) {
        return radioStabilityRepository.findRadioStabilityByName(prescriptionName);
    }

    @Override
    public void deleteRadioStabilityById(Long stabilityId) {
        radioStabilityRepository.deleteById(stabilityId);
    }

    @Override
    public StorageProperty createStorageProperty(Long prescriptionId,StorageProperty property) {
        Optional<Prescription> optionalPrescription = prescriptionRepository.findById(prescriptionId);
        if(optionalPrescription.isPresent()) {
            Prescription prescription = optionalPrescription.get();
            StorageProperty storageProperty = storagePropertyRepository.save(property);
            prescription.setStorageProperty(storageProperty);
            prescriptionRepository.save(prescription);
            return storageProperty;
        }
        return null;
    }

    @Override
    public StorageProperty updateStorageProperty(StorageProperty property) {
        return storagePropertyRepository.save(property);
    }

    @Override
    public StorageProperty getStoragePropertyByPrescriptionId(Long prescriptionId) {
        return storagePropertyRepository.findStoragePropertyByPrescription(prescriptionId).get();
    }

    @Override
    public StorageProperty getStoragePropertyById(Long propertyId) {
        return storagePropertyRepository.findById(propertyId).get();
    }

    @Override
    public Collection<StorageProperty> getStoragePropertyByName(String propertyName) {
        return storagePropertyRepository.findStoragePropertyByName(propertyName);
    }

    @Override
    public void deleteStoragePropertyById(Long propertyId) {
        storagePropertyRepository.deleteById(propertyId);
    }

    @Override
    public List<PrescriptionElements> getAllMaterialElementsByProscriptionId(Long proscriptionId) {
        Optional<Prescription> optionalPrescription = prescriptionRepository.findById(proscriptionId);
        if(optionalPrescription.isPresent()) {
            Prescription prescription = prescriptionRepository.getMaterialElementsById(proscriptionId);
            Set<hasMaterialElement> materialElements = prescription.getMaterialElements();
            ArrayList<PrescriptionElements> elements = new ArrayList<>();
            for (hasMaterialElement materialElement : materialElements) {
                PrescriptionElements element = new PrescriptionElements();
                element.setPercentage(materialElement.getPercentage());
                element.setMaterial(materialElement.getMaterial());
                element.setRelationId(materialElement.getId());
                elements.add(element);
            }
            return elements;
        }
        return null;
    }

    @Override
    public List<hasMaterialElement> modifyAllMaterialElementsByProscriptionId(Long proscriptionId,List<MaterialAndValue> materialElements) {
        Optional<Prescription> optionalPrescription = prescriptionRepository.findById(proscriptionId);
        if(optionalPrescription.isPresent()){
            Prescription prescription = optionalPrescription.get();
            prescriptionRepository.deleteRelationForMaterial(proscriptionId);
            for (MaterialAndValue materialElement : materialElements) {
                prescriptionRepository.createRelationForMaterial(proscriptionId,materialElement.getMaterialId(),materialElement.getPercentage());
            }
            return materialElementRepository.findElementByPrescriptionId(proscriptionId);
        }
        return null;
    }

    @Override
    public void addMaterialElementForProscription(PrescriptionAndAElement PE) {
        prescriptionRepository.createRelationForMaterial(PE.getPrescriptionId(), PE.getMaterialId(),PE.getPercentage());
    }

    @Override
    public void deleteMaterialElementForProscription(Long relationId) {
        prescriptionRepository.deleteMaterialElementForProscription(relationId);
    }
}
