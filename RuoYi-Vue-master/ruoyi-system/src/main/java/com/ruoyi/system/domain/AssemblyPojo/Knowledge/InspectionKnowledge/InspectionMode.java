package com.ruoyi.system.domain.AssemblyPojo.Knowledge.InspectionKnowledge;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.Objects;

@Node({"检测模式","InspectionMode"})
public class InspectionMode {

    @Id
    @GeneratedValue
    private Long modeId;

    @Property(name = "label")
    private String modeName;

    @Property(name = "模式描述")
    private String modeDescription;

    public Long getModeId() {
        return modeId;
    }

    public void setModeId(Long modeId) {
        this.modeId = modeId;
    }

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public String getModeDescription() {
        return modeDescription;
    }

    public void setModeDescription(String modeDescription) {
        this.modeDescription = modeDescription;
    }

    public InspectionMode() {
    }

    public InspectionMode(Long modeId, String modeName, String modeDescription) {
        this.modeId = modeId;
        this.modeName = modeName;
        this.modeDescription = modeDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InspectionMode that = (InspectionMode) o;
        return Objects.equals(modeId, that.modeId) && Objects.equals(modeName, that.modeName) && Objects.equals(modeDescription, that.modeDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modeId, modeName, modeDescription);
    }

    @Override
    public String toString() {
        return "InspectionMode{" +
                "modeId=" + modeId +
                ", modeName='" + modeName + '\'' +
                ", modeDescription='" + modeDescription + '\'' +
                '}';
    }
}
