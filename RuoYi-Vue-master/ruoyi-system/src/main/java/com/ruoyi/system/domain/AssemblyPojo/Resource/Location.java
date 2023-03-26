package com.ruoyi.system.domain.AssemblyPojo.Resource;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Objects;
import java.util.Set;

@Node({"地点","Location"})
public class Location {

    @Id
    @GeneratedValue
    private Long locationId;

    @Property(name = "label")
    private String locationName;

    @Property(name = "地点描述")
    private String locationDescription;

    @DynamicLabels
    private Set<String> dynamicLabels;

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public Set<String> getDynamicLabels() {
        return dynamicLabels;
    }

    public void setDynamicLabels(Set<String> dynamicLabels) {
        this.dynamicLabels = dynamicLabels;
    }

    public Location() {
    }

    public Location(Long locationId, String locationName, String locationDescription, Set<String> dynamicLabels) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.locationDescription = locationDescription;
        this.dynamicLabels = dynamicLabels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(locationId, location.locationId) && Objects.equals(locationName, location.locationName) && Objects.equals(locationDescription, location.locationDescription) && Objects.equals(dynamicLabels, location.dynamicLabels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, locationName, locationDescription, dynamicLabels);
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", locationName='" + locationName + '\'' +
                ", locationDescription='" + locationDescription + '\'' +
                ", dynamicLabels=" + dynamicLabels +
                '}';
    }
}
