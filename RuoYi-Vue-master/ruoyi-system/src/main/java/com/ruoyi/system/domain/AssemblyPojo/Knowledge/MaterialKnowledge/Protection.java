package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.Objects;

@Node("安全要求")
public class Protection {

    @Id
    @GeneratedValue
    private Long protectionId;

    @Property(name = "label")
    private String protectionName;

    @Property(name = "描述")
    private String protectionDescription;

    public Long getProtectionId() {
        return protectionId;
    }

    public void setProtectionId(Long protectionId) {
        this.protectionId = protectionId;
    }

    public String getProtectionName() {
        return protectionName;
    }

    public void setProtectionName(String protectionName) {
        this.protectionName = protectionName;
    }

    public String getProtectionDescription() {
        return protectionDescription;
    }

    public void setProtectionDescription(String protectionDescription) {
        this.protectionDescription = protectionDescription;
    }

    public Protection() {
    }

    public Protection(Long protectionId, String protectionName, String protectionDescription) {
        this.protectionId = protectionId;
        this.protectionName = protectionName;
        this.protectionDescription = protectionDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Protection that = (Protection) o;
        return Objects.equals(protectionId, that.protectionId) && Objects.equals(protectionName, that.protectionName) && Objects.equals(protectionDescription, that.protectionDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(protectionId, protectionName, protectionDescription);
    }

    @Override
    public String toString() {
        return "Protection{" +
                "protectionId=" + protectionId +
                ", protectionName='" + protectionName + '\'' +
                ", protectionDescription='" + protectionDescription + '\'' +
                '}';
    }
}
