package com.ruoyi.system.domain.AssemblyPojo.Resource;

import com.ruoyi.system.domain.AssemblyPojo.Process.SpecialSequence.Sequence;
import com.ruoyi.system.domain.AssemblyPojo.Process.Step;
import org.springframework.data.neo4j.core.schema.CompositeProperty;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.*;

@Node({"设备资源","EquipmentResource"})
public class EquipmentResource extends AssemblyResource{

    @CompositeProperty(prefix = "设备能力")
    private Map<String,String> equipmentCapacity;

    @Property(name = "设备功率")
    private String equipmentPower;

    @Property(name = "设备价格")
    private String equipmentPrice;

    @Property(name = "生产日期")
    private Date productionDate;

    @Property(name = "制造商")
    private String manufacturer;

    @Property(name = "设备型号")
    private String equipmentSize;

    @Property(name = "设备状态")
    private String equipmentState;

    @Property(name = "折旧率")
    private double depreciationRate;

    @Property(name = "负载率")
    private double loadRate;

    @Property(name = "注意事项")
    private List<String>  attentions;

    @Property(name = "使用原则")
    private String principle;

    public Map<String, String> getEquipmentCapacity() {
        return equipmentCapacity;
    }

    public void setEquipmentCapacity(Map<String, String> equipmentCapacity) {
        this.equipmentCapacity = equipmentCapacity;
    }

    public String getEquipmentPower() {
        return equipmentPower;
    }

    public void setEquipmentPower(String equipmentPower) {
        this.equipmentPower = equipmentPower;
    }

    public String getEquipmentPrice() {
        return equipmentPrice;
    }

    public void setEquipmentPrice(String equipmentPrice) {
        this.equipmentPrice = equipmentPrice;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getEquipmentSize() {
        return equipmentSize;
    }

    public void setEquipmentSize(String equipmentSize) {
        this.equipmentSize = equipmentSize;
    }

    public String getEquipmentState() {
        return equipmentState;
    }

    public void setEquipmentState(String equipmentState) {
        this.equipmentState = equipmentState;
    }

    public double getDepreciationRate() {
        return depreciationRate;
    }

    public void setDepreciationRate(double depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    public double getLoadRate() {
        return loadRate;
    }

    public void setLoadRate(double loadRate) {
        this.loadRate = loadRate;
    }

    public List<String> getAttentions() {
        return attentions;
    }

    public void setAttentions(List<String> attentions) {
        this.attentions = attentions;
    }

    public String getPrinciple() {
        return principle;
    }

    public void setPrinciple(String principle) {
        this.principle = principle;
    }

    public EquipmentResource() {
    }

    public EquipmentResource(Long resourceId, String resourceName, String resourceDescription, Set<String> resourceTypes, String site, Map<String, String> equipmentCapacity, String equipmentPower, String equipmentPrice, Date productionDate, String manufacturer, String equipmentSize, String equipmentState, double depreciationRate, double loadRate, List<String> attentions, String principle) {
        super(resourceId, resourceName, resourceDescription, resourceTypes, site);
        this.equipmentCapacity = equipmentCapacity;
        this.equipmentPower = equipmentPower;
        this.equipmentPrice = equipmentPrice;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.equipmentSize = equipmentSize;
        this.equipmentState = equipmentState;
        this.depreciationRate = depreciationRate;
        this.loadRate = loadRate;
        this.attentions = attentions;
        this.principle = principle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EquipmentResource that = (EquipmentResource) o;
        return Double.compare(that.depreciationRate, depreciationRate) == 0 && Double.compare(that.loadRate, loadRate) == 0 && Objects.equals(equipmentCapacity, that.equipmentCapacity) && Objects.equals(equipmentPower, that.equipmentPower) && Objects.equals(equipmentPrice, that.equipmentPrice) && Objects.equals(productionDate, that.productionDate) && Objects.equals(manufacturer, that.manufacturer) && Objects.equals(equipmentSize, that.equipmentSize) && Objects.equals(equipmentState, that.equipmentState) && Objects.equals(attentions, that.attentions) && Objects.equals(principle, that.principle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), equipmentCapacity, equipmentPower, equipmentPrice, productionDate, manufacturer, equipmentSize, equipmentState, depreciationRate, loadRate, attentions, principle);
    }

    @Override
    public String toString() {
        return "EquipmentResource{" +
                "equipmentCapacity=" + equipmentCapacity +
                ", equipmentPower='" + equipmentPower + '\'' +
                ", equipmentPrice='" + equipmentPrice + '\'' +
                ", productionDate=" + productionDate +
                ", manufacturer='" + manufacturer + '\'' +
                ", equipmentSize='" + equipmentSize + '\'' +
                ", equipmentState='" + equipmentState + '\'' +
                ", depreciationRate=" + depreciationRate +
                ", loadRate=" + loadRate +
                ", attentions=" + attentions +
                ", principle='" + principle + '\'' +
                "} " + super.toString();
    }
}
