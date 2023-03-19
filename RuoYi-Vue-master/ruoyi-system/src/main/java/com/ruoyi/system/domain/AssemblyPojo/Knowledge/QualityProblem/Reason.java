package com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.Objects;


@Node({"Reason","原因"})
public class Reason {

    @Id
    @GeneratedValue
    private Long reasonId;

    @Property(name = "label")
    private String reasonName;

    @Property(name = "原因描述")
    private String reasonDescription;

    public Long getReasonId() {
        return reasonId;
    }

    public void setReasonId(Long reasonId) {
        this.reasonId = reasonId;
    }

    public String getReasonName() {
        return reasonName;
    }

    public void setReasonName(String reasonName) {
        this.reasonName = reasonName;
    }

    public String getReasonDescription() {
        return reasonDescription;
    }

    public void setReasonDescription(String reasonDescription) {
        this.reasonDescription = reasonDescription;
    }

    public Reason() {
    }

    public Reason(Long reasonId, String reasonName, String reasonDescription) {
        this.reasonId = reasonId;
        this.reasonName = reasonName;
        this.reasonDescription = reasonDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reason reason = (Reason) o;
        return Objects.equals(reasonId, reason.reasonId) && Objects.equals(reasonName, reason.reasonName) && Objects.equals(reasonDescription, reason.reasonDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reasonId, reasonName, reasonDescription);
    }

    @Override
    public String toString() {
        return "Reason{" +
                "reasonId=" + reasonId +
                ", reasonName='" + reasonName + '\'' +
                ", reasonDescription='" + reasonDescription + '\'' +
                '}';
    }
}
