package com.ruoyi.system.domain.AssemblyPojo.GeometricPose.GeometryEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Node("GeometryEntity")
public class GeometryEntity {

    @Id
    @GeneratedValue
    private Long entityId;

    private String entityName;

    private String entityType;
}
