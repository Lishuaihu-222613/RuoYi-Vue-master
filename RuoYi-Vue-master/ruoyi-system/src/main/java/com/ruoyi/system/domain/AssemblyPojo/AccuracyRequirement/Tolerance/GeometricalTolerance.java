package com.ruoyi.system.domain.AssemblyPojo.AccuracyRequirement.Tolerance;

import com.ruoyi.system.domain.AssemblyPojo.GeometricPose.GeometryEntity.GeometryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Node("GeometricalTolerance")
public class GeometricalTolerance {

    @Id
    @GeneratedValue
    private Long toleranceId;

    @Property(name = "Label")
    private String toleranceName;

    @Property(name = "Type")
    private String toleranceType;

    @Property(name = "Value")
    private float toleranceValue;

    @Property(name = "Description")
    private String toleranceDescription;

    @Relationship(type = "hasAdditionalInformation", direction = Relationship.Direction.OUTGOING)
    private Set<AdditionalInformation> information;

    @Relationship(type = "use", direction = Relationship.Direction.OUTGOING)
    private GeometryEntity geometryEntity;

}
