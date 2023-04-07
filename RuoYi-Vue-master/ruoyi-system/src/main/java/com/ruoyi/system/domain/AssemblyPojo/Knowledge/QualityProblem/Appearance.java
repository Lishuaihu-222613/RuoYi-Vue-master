package com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.Objects;

@Node({"Appearance","外观表现"})
public class Appearance {

    @Id
    @GeneratedValue
    private Long appearanceId;

    @Property(name = "label")
    private String appearanceName;

    @Property(name = "外观描述")
    private String appearanceDescription;

    public Long getAppearanceId() {
        return appearanceId;
    }

    public void setAppearanceId(Long appearanceId) {
        this.appearanceId = appearanceId;
    }

    public String getAppearanceName() {
        return appearanceName;
    }

    public void setAppearanceName(String appearanceName) {
        this.appearanceName = appearanceName;
    }

    public String getAppearanceDescription() {
        return appearanceDescription;
    }

    public void setAppearanceDescription(String appearanceDescription) {
        this.appearanceDescription = appearanceDescription;
    }

    public Appearance() {
    }

    public Appearance(Long appearanceId, String appearanceName, String appearanceDescription) {
        this.appearanceId = appearanceId;
        this.appearanceName = appearanceName;
        this.appearanceDescription = appearanceDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appearance that = (Appearance) o;
        return Objects.equals(appearanceId, that.appearanceId) && Objects.equals(appearanceName, that.appearanceName) && Objects.equals(appearanceDescription, that.appearanceDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appearanceId, appearanceName, appearanceDescription);
    }

    @Override
    public String toString() {
        return "Appearance{" +
                "appearanceId=" + appearanceId +
                ", appearanceName='" + appearanceName + '\'' +
                ", appearanceDescription='" + appearanceDescription + '\'' +
                '}';
    }
}
