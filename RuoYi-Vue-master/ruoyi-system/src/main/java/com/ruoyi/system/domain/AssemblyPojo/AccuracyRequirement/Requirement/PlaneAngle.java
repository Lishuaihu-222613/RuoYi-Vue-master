package com.ruoyi.system.domain.AssemblyPojo.AccuracyRequirement.Requirement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Node("PlaneAngle")
public class PlaneAngle implements Requirement{

    @Id
    @GeneratedValue
    private Long RequirementId;

    @Property(name = "Plane-Plane")
    private float PlanePlaneAngle;

    @Property(name = "Plane-Nominal")
    private float PlaneNominalAngle;
}
