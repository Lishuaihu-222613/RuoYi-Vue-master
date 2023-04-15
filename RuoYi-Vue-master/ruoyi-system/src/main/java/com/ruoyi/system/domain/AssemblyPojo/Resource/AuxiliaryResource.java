package com.ruoyi.system.domain.AssemblyPojo.Resource;

import com.ruoyi.system.domain.AssemblyPojo.Process.SpecialSequence.Sequence;
import com.ruoyi.system.domain.AssemblyPojo.Process.Step;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Node({"辅助资源","AuxiliaryResource"})
public class AuxiliaryResource extends AssemblyResource{

    @Property(name = "工具用途")
    private List<String> resourceUsages;

    @Property(name = "适用工艺")
    private List<String> suitableProcesses;

    public List<String> getResourceUsages() {
        return resourceUsages;
    }

    public void setResourceUsages(List<String> resourceUsages) {
        this.resourceUsages = resourceUsages;
    }

    public List<String> getSuitableProcesses() {
        return suitableProcesses;
    }

    public void setSuitableProcesses(List<String> suitableProcesses) {
        this.suitableProcesses = suitableProcesses;
    }

    public AuxiliaryResource() {
    }

    public AuxiliaryResource(Long resourceId, String resourceName, String resourceDescription, Set<String> resourceTypes, String site, List<String> resourceUsages, List<String> suitableProcesses) {
        super(resourceId, resourceName, resourceDescription, resourceTypes, site);
        this.resourceUsages = resourceUsages;
        this.suitableProcesses = suitableProcesses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AuxiliaryResource that = (AuxiliaryResource) o;
        return Objects.equals(resourceUsages, that.resourceUsages) && Objects.equals(suitableProcesses, that.suitableProcesses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), resourceUsages, suitableProcesses);
    }

    @Override
    public String toString() {
        return "AuxiliaryResource{" +
                "resourceUsages=" + resourceUsages +
                ", suitableProcesses=" + suitableProcesses +
                "} " + super.toString();
    }
}
