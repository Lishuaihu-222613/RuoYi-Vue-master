package com.ruoyi.system.domain.AssemblyPojo.Resource;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Objects;
import java.util.Set;

@Node({"地点","Site"})
public class Site {

    @Id
    @GeneratedValue
    private Long siteId;

    @Property(name = "label")
    private String siteName;

    @Property(name = "地点描述")
    private String siteDescription;

    @DynamicLabels
    private Set<String> dynamicLabels;

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteDescription() {
        return siteDescription;
    }

    public void setSiteDescription(String siteDescription) {
        this.siteDescription = siteDescription;
    }

    public Set<String> getDynamicLabels() {
        return dynamicLabels;
    }

    public void setDynamicLabels(Set<String> dynamicLabels) {
        this.dynamicLabels = dynamicLabels;
    }

    public Site() {
    }

    public Site(Long siteId, String siteName, String siteDescription, Set<String> dynamicLabels) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.siteDescription = siteDescription;
        this.dynamicLabels = dynamicLabels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Site site = (Site) o;
        return Objects.equals(siteId, site.siteId) && Objects.equals(siteName, site.siteName) && Objects.equals(siteDescription, site.siteDescription) && Objects.equals(dynamicLabels, site.dynamicLabels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(siteId, siteName, siteDescription, dynamicLabels);
    }

    @Override
    public String toString() {
        return "Site{" +
                "siteId=" + siteId +
                ", siteName='" + siteName + '\'' +
                ", siteDescription='" + siteDescription + '\'' +
                ", dynamicLabels=" + dynamicLabels +
                '}';
    }
}
