package com.ruoyi.system.domain.AssemblyPojo.Process.SpecialSequence;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Prescription;
import com.ruoyi.system.domain.AssemblyPojo.Process.Step;
import com.ruoyi.system.domain.AssemblyPojo.Resource.EquipmentResource;
import com.ruoyi.system.domain.AssemblyPojo.Resource.SpecialTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.MeasuringTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.MouldTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.SpreaderTool;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;
import java.util.Set;

@Node({"混合工序","Truing"})
public class Truing extends Sequence{

    @Relationship(type = "使用配方")
    private Prescription associatedPrescription;

    @Property(name = "混合锅数")
    private Integer panNumber;

    @Property(name = "每锅料质量")
    private double panQuality;

    @Property(name = "混合时间")
    private double mixTime;

    @Property(name = "工房温度")
    private String workshopTemperature;

    @Property(name = "工房绝对湿含量")
    private String absoluteHumidity;

    public Truing() {
    }

    public Truing(Long sequenceId, String sequenceName, String sequenceNumber, String sequenceDescription, double quasiClosingHours, double taktTime, Set<String> dynamicLabels, List<String> sequenceRemark, Set<EquipmentResource> useEquipment, Set<MouldTool> useMouldTool, Set<MeasuringTool> useMeasuringTool, Set<SpecialTool> useSpecialTool, Set<SpreaderTool> useSpreaderTool, Set<Step> associatedSteps, Set<Sequence> andSequence, Set<Sequence> orSequence, Set<Sequence> beforeSequence, Set<Sequence> afterSequence, Prescription associatedPrescription, Integer panNumber, double panQuality, double mixTime, String workshopTemperature, String absoluteHumidity) {
        super(sequenceId, sequenceName, sequenceNumber, sequenceDescription, quasiClosingHours, taktTime, dynamicLabels, sequenceRemark, useEquipment, useMouldTool, useMeasuringTool, useSpecialTool, useSpreaderTool, associatedSteps, andSequence, orSequence, beforeSequence, afterSequence);
        this.associatedPrescription = associatedPrescription;
        this.panNumber = panNumber;
        this.panQuality = panQuality;
        this.mixTime = mixTime;
        this.workshopTemperature = workshopTemperature;
        this.absoluteHumidity = absoluteHumidity;
    }

    @Override
    public String toString() {
        return "Truing{" +
                "associatedPrescription=" + associatedPrescription +
                ", panNumber=" + panNumber +
                ", panQuality=" + panQuality +
                ", mixTime=" + mixTime +
                ", workshopTemperature='" + workshopTemperature + '\'' +
                ", absoluteHumidity='" + absoluteHumidity + '\'' +
                "} " + super.toString();
    }
}
