package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Material;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Objects;

@RelationshipProperties
public class hasMaterialElement {

    @Id
    @GeneratedValue
    private Long Id;

    @TargetNode
    private Material material;

    @Property(name = "质量分数")
    private double percentage;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public hasMaterialElement() {
    }

    public hasMaterialElement(Long id, Material material, double percentage) {
        Id = id;
        this.material = material;
        this.percentage = percentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        hasMaterialElement that = (hasMaterialElement) o;
        return Double.compare(that.percentage, percentage) == 0 && Objects.equals(Id, that.Id) && Objects.equals(material, that.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, material, percentage);
    }

    @Override
    public String toString() {
        return "hasRawMaterialElement{" +
                "Id=" + Id +
                ", material=" + material +
                ", percentage=" + percentage +
                '}';
    }
}
