package com.ruoyi.system.domain.AssemblyPojo.Resource.ToolingResource;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.GeometricElement.GeometricElement;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.Set;

@Node("FixtureTool")
public class FixtureTool extends ToolingResource{

    private String modelNumber;

    private String clampElement;

    private String clampStyle;

    private String positionElement;

    private String positionStyle;

    private Set<GeometricElement> suitableElements;

    private Set<CuttingTool> suitableTools;

    private String suitablePartSize;

    private String suitablePartShape;
}
