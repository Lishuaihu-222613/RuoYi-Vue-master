package com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("MeasuringTool")
public class MeasuringTool extends ToolingResource{

     @Property(name  = "MeasureAccuracy")
     private String measureAccuracy;

     @Property(name  = "MeasureHeight")
     private String measureHeight;

    @Property(name  = "ModelNumber")
     private String modelNumber;

}
