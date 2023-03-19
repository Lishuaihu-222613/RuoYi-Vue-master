package com.ruoyi.system.domain.AssemblyPojo.Knowledge.QualityProblem;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.Objects;

@Node({"Solution","解决方法"})
public class Solution {

    @Id
    @GeneratedValue
    private Long solutionId;

    @Property(name = "label")
    private String solutionName;

    @Property(name = "方法描述")
    private String solutionDescription;

    public Long getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Long solutionId) {
        this.solutionId = solutionId;
    }

    public String getSolutionName() {
        return solutionName;
    }

    public void setSolutionName(String solutionName) {
        this.solutionName = solutionName;
    }

    public String getSolutionDescription() {
        return solutionDescription;
    }

    public void setSolutionDescription(String solutionDescription) {
        this.solutionDescription = solutionDescription;
    }

    public Solution() {
    }

    public Solution(Long solutionId, String solutionName, String solutionDescription) {
        this.solutionId = solutionId;
        this.solutionName = solutionName;
        this.solutionDescription = solutionDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution solution = (Solution) o;
        return Objects.equals(solutionId, solution.solutionId) && Objects.equals(solutionName, solution.solutionName) && Objects.equals(solutionDescription, solution.solutionDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(solutionId, solutionName, solutionDescription);
    }

    @Override
    public String toString() {
        return "Solution{" +
                "solutionId=" + solutionId +
                ", solutionName='" + solutionName + '\'' +
                ", solutionDescription='" + solutionDescription + '\'' +
                '}';
    }
}
