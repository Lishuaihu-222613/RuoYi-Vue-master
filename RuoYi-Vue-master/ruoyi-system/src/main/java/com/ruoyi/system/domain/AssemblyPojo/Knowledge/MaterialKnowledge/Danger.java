package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.Objects;

@Node({"危险事项","Danger"})
public class Danger {

    @Id
    @GeneratedValue
    private Long dangerId;

    @Property(name = "label")
    private String dangerName;

    @Property(name = "描述")
    private String dangerDescription;

    public Long getDangerId() {
        return dangerId;
    }

    public void setDangerId(Long dangerId) {
        this.dangerId = dangerId;
    }

    public String getDangerName() {
        return dangerName;
    }

    public void setDangerName(String dangerName) {
        this.dangerName = dangerName;
    }

    public String getDangerDescription() {
        return dangerDescription;
    }

    public void setDangerDescription(String dangerDescription) {
        this.dangerDescription = dangerDescription;
    }

    public Danger() {
    }

    public Danger(Long dangerId, String dangerName, String dangerDescription) {
        this.dangerId = dangerId;
        this.dangerName = dangerName;
        this.dangerDescription = dangerDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Danger danger = (Danger) o;
        return Objects.equals(dangerId, danger.dangerId) && Objects.equals(dangerName, danger.dangerName) && Objects.equals(dangerDescription, danger.dangerDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dangerId, dangerName, dangerDescription);
    }

    @Override
    public String toString() {
        return "Danger{" +
                "dangerId=" + dangerId +
                ", dangerName='" + dangerName + '\'' +
                ", dangerDescription='" + dangerDescription + '\'' +
                '}';
    }
}
