package com.ruoyi.system.service.KnowledgeService.Prescription.Impl;

import com.ruoyi.system.Repository.KnowledgeRepository.Prescription.StoragePropertyRepository;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property.StorageProperty;
import com.ruoyi.system.service.KnowledgeService.Prescription.StoragePropertyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

@Service
public class StoragePropertyServiceImpl implements StoragePropertyService {
    @Resource
    private StoragePropertyRepository storagePropertyRepository;

    @Override
    public StorageProperty createStorageProperty(StorageProperty property) {
        return null;
    }

    @Override
    public StorageProperty updateStorageProperty(StorageProperty property) {
        return null;
    }

    @Override
    public Collection<StorageProperty> getStoragePropertyByPrescription(Long prescriptionId) {
        return null;
    }

    @Override
    public StorageProperty getStoragePropertyById(Long propertyId) {
        return null;
    }

    @Override
    public Collection<StorageProperty> getStoragePropertyByName(String propertyName) {
        return null;
    }

    @Override
    public void deleteStorageProperty(StorageProperty property) {

    }

    @Override
    public void deleteStoragePropertyById(Long propertyId) {

    }
}
