package com.ruoyi.system.domain.AssemblyPojo.Process.SpecialSequence;

import com.ruoyi.system.domain.AssemblyPojo.Process.Step;
import com.ruoyi.system.domain.AssemblyPojo.Resource.EquipmentResource;
import com.ruoyi.system.domain.AssemblyPojo.Resource.SpecialTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.MeasuringTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.MouldTool;
import com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource.SpreaderTool;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Node({"Sequence","装药工序"})
public abstract class Sequence {

    @Id
    @GeneratedValue
    private Long sequenceId;

    @Property(name = "label")
    private String sequenceName;

    @Property(name = "工序号")
    private String sequenceNumber;

    @Property(name = "工序内容")
    private String sequenceDescription;

    @Property(name = "准结工时")
    private double QuasiClosingHours;

    @Property(name = "单件工时")
    private double TaktTime;

    @DynamicLabels
    private Set<String> dynamicLabels;

    @Property(name = "工序备注")
    private List<String> sequenceRemark;

    @Relationship(type = "使用设备" , direction = Relationship.Direction.OUTGOING)
    private Set<EquipmentResource> useEquipment;

    @Relationship(type = "使用模具" , direction = Relationship.Direction.OUTGOING)
    private Set<MouldTool> useMouldTool;

    @Relationship(type = "使用量具" , direction = Relationship.Direction.OUTGOING)
    private Set<MeasuringTool> useMeasuringTool;

    @Relationship(type = "使用专用工装" , direction = Relationship.Direction.OUTGOING)
    private Set<SpecialTool> useSpecialTool;

    @Relationship(type = "使用吊具" , direction = Relationship.Direction.OUTGOING)
    private Set<SpreaderTool> useSpreaderTool;

    @Relationship(type = "包含工步" , direction = Relationship.Direction.OUTGOING)
    private Set<Step> associatedSteps;

    @Relationship(type = "工序与" , direction = Relationship.Direction.OUTGOING)
    private Set<Sequence> andSequence;

    @Relationship(type = "工序或" , direction = Relationship.Direction.OUTGOING)
    private Set<Sequence> orSequence;

    @Relationship(type = "前道工序" , direction = Relationship.Direction.OUTGOING)
    private Set<Sequence> beforeSequence;

    @Relationship(type = "后道工序" , direction = Relationship.Direction.OUTGOING)
    private Set<Sequence> afterSequence;

    public Sequence() {
    }

    public Sequence(Long sequenceId, String sequenceName, String sequenceNumber, String sequenceDescription, double quasiClosingHours, double taktTime, Set<String> dynamicLabels, List<String> sequenceRemark, Set<EquipmentResource> useEquipment, Set<MouldTool> useMouldTool, Set<MeasuringTool> useMeasuringTool, Set<SpecialTool> useSpecialTool, Set<SpreaderTool> useSpreaderTool, Set<Step> associatedSteps, Set<Sequence> andSequence, Set<Sequence> orSequence, Set<Sequence> beforeSequence, Set<Sequence> afterSequence) {
        this.sequenceId = sequenceId;
        this.sequenceName = sequenceName;
        this.sequenceNumber = sequenceNumber;
        this.sequenceDescription = sequenceDescription;
        QuasiClosingHours = quasiClosingHours;
        TaktTime = taktTime;
        this.dynamicLabels = dynamicLabels;
        this.sequenceRemark = sequenceRemark;
        this.useEquipment = useEquipment;
        this.useMouldTool = useMouldTool;
        this.useMeasuringTool = useMeasuringTool;
        this.useSpecialTool = useSpecialTool;
        this.useSpreaderTool = useSpreaderTool;
        this.associatedSteps = associatedSteps;
        this.andSequence = andSequence;
        this.orSequence = orSequence;
        this.beforeSequence = beforeSequence;
        this.afterSequence = afterSequence;
    }

    public Long getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(Long sequenceId) {
        this.sequenceId = sequenceId;
    }

    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getSequenceDescription() {
        return sequenceDescription;
    }

    public void setSequenceDescription(String sequenceDescription) {
        this.sequenceDescription = sequenceDescription;
    }

    public double getQuasiClosingHours() {
        return QuasiClosingHours;
    }

    public void setQuasiClosingHours(double quasiClosingHours) {
        QuasiClosingHours = quasiClosingHours;
    }

    public double getTaktTime() {
        return TaktTime;
    }

    public void setTaktTime(double taktTime) {
        TaktTime = taktTime;
    }

    public List<String> getSequenceRemark() {
        return sequenceRemark;
    }

    public void setSequenceRemark(List<String> sequenceRemark) {
        this.sequenceRemark = sequenceRemark;
    }

    public Set<String> getDynamicLabels() {
        return dynamicLabels;
    }

    public void setDynamicLabels(Set<String> dynamicLabels) {
        this.dynamicLabels = dynamicLabels;
    }

    public Set<EquipmentResource> getUseEquipment() {
        return useEquipment;
    }

    public void setUseEquipment(Set<EquipmentResource> useEquipment) {
        this.useEquipment = useEquipment;
    }

    public Set<MouldTool> getUseMouldTool() {
        return useMouldTool;
    }

    public void setUseMouldTool(Set<MouldTool> useMouldTool) {
        this.useMouldTool = useMouldTool;
    }

    public Set<MeasuringTool> getUseMeasuringTool() {
        return useMeasuringTool;
    }

    public void setUseMeasuringTool(Set<MeasuringTool> useMeasuringTool) {
        this.useMeasuringTool = useMeasuringTool;
    }

    public Set<SpecialTool> getUseSpecialTool() {
        return useSpecialTool;
    }

    public void setUseSpecialTool(Set<SpecialTool> useSpecialTool) {
        this.useSpecialTool = useSpecialTool;
    }

    public Set<SpreaderTool> getUseSpreaderTool() {
        return useSpreaderTool;
    }

    public void setUseSpreaderTool(Set<SpreaderTool> useSpreaderTool) {
        this.useSpreaderTool = useSpreaderTool;
    }

    public Set<Step> getAssociatedSteps() {
        return associatedSteps;
    }

    public void setAssociatedSteps(Set<Step> associatedSteps) {
        this.associatedSteps = associatedSteps;
    }

    public Set<Sequence> getAndSequence() {
        return andSequence;
    }

    public void setAndSequence(Set<Sequence> andSequence) {
        this.andSequence = andSequence;
    }

    public Set<Sequence> getOrSequence() {
        return orSequence;
    }

    public void setOrSequence(Set<Sequence> orSequence) {
        this.orSequence = orSequence;
    }

    public Set<Sequence> getBeforeSequence() {
        return beforeSequence;
    }

    public void setBeforeSequence(Set<Sequence> beforeSequence) {
        this.beforeSequence = beforeSequence;
    }

    public Set<Sequence> getAfterSequence() {
        return afterSequence;
    }

    public void setAfterSequence(Set<Sequence> afterSequence) {
        this.afterSequence = afterSequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sequence sequence = (Sequence) o;
        return Double.compare(sequence.QuasiClosingHours, QuasiClosingHours) == 0 && Double.compare(sequence.TaktTime, TaktTime) == 0 && Objects.equals(sequenceId, sequence.sequenceId) && Objects.equals(sequenceName, sequence.sequenceName) && Objects.equals(sequenceNumber, sequence.sequenceNumber) && Objects.equals(sequenceDescription, sequence.sequenceDescription) && Objects.equals(dynamicLabels, sequence.dynamicLabels) && Objects.equals(sequenceRemark, sequence.sequenceRemark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sequenceId, sequenceName, sequenceNumber, sequenceDescription, QuasiClosingHours, TaktTime, dynamicLabels, sequenceRemark);
    }

    @Override
    public String toString() {
        return "Sequence{" +
                "sequenceId=" + sequenceId +
                ", sequenceName='" + sequenceName + '\'' +
                ", sequenceNumber='" + sequenceNumber + '\'' +
                ", sequenceDescription='" + sequenceDescription + '\'' +
                ", QuasiClosingHours=" + QuasiClosingHours +
                ", TaktTime=" + TaktTime +
                ", dynamicLabels=" + dynamicLabels +
                ", sequenceRemark=" + sequenceRemark +
                '}';
    }
}
