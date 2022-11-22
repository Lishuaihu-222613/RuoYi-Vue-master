package com.ruoyi.system.service.KnowledgeService.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Property.StorageProperty;

import java.util.Collection;

public interface StoragePropertyService {

    /**
     *
     * @param property
     * @return
     */
    StorageProperty createStorageProperty(StorageProperty property);

    /**
     *
     * @param property
     * @return
     */
    StorageProperty updateStorageProperty(StorageProperty property);

    /**
     *
     * @param prescriptionId
     * @return
     */
    Collection<StorageProperty> getStoragePropertyByPrescription(Long prescriptionId);

    /**
     *
     * @param propertyId
     * @return
     */
    StorageProperty getStoragePropertyById(Long propertyId);

    /**
     *
     * @param propertyName
     * @return
     */
    Collection<StorageProperty> getStoragePropertyByName(String propertyName);

    /**
     *
     * @param property
     */
    void deleteStorageProperty(StorageProperty property);

    /**
     *
     * @param propertyId
     */
    void deleteStoragePropertyById(Long propertyId);
}
