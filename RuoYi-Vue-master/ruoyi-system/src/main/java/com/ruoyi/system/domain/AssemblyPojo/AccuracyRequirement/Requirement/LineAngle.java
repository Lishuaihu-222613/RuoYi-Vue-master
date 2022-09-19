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
@Node("LineAngle")
public class LineAngle implements Requirement{

    @Id
    @GeneratedValue
    private Long RequirementId;

    @Property(name = "Line-Plane")
    private float LinePlaneAngle;

    @Property(name = "Line-Line")
    private float LineLineAngle;

    @Property(name = "Line-Nominal")
    private float LineNominalAngle;

}
