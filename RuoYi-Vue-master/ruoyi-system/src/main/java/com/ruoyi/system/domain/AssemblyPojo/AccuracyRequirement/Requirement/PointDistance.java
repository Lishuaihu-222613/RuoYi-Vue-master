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
@Node("PointDistance")
public class PointDistance implements Requirement{

    @Id
    @GeneratedValue
    private Long RequirementId;

    @Property(name = "Point-Point")
    private float PointPointDistance;

    @Property(name = "Point-Line")
    private float PointLineDistance;

    @Property(name = "Point-Plane")
    private float PointPlaneDistance;

    @Property(name = "Point-Nominal")
    private float PointNominalDistance;
}
