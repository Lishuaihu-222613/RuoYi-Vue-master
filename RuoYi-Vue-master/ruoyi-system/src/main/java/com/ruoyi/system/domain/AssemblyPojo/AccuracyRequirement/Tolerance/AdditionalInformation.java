package com.ruoyi.system.domain.AssemblyPojo.AccuracyRequirement.Tolerance;

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
@Node("AdditionalInformation")
public class AdditionalInformation {

    @Id
    @GeneratedValue
    private Long informationId;

    @Property(name = "Label")
    private String informationName;

    @Property(name = "Description")
    private String informationDescription;

}
