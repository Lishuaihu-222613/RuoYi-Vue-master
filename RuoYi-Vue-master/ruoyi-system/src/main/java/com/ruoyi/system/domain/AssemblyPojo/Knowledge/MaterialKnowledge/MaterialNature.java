package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.List;

@Node()
public class MaterialNature {

    @Id
    @GeneratedValue
    private Long Id;

    @Property(name = "label")
    private String MaterialName;

    @Property(name = "Description")
    private String MaterialDescription;

    @Property(name = "Appearance")
    private String MaterialAppearance;

    @Property(name = "physicalProperty")
    private String physicalProperty;

    @Property(name = "chemicalProperty")
    private String chemicalProperty;

    @Property(name = "thermalDecompositionProperty")
    private String thermalDecompositionProperty;

    @Property(name = "explosiveProperty")
    private String explosiveProperty;

    @Property(name = "corrosiveProperty")
    private String corrosiveProperty;

    @Property(name = "waterAbsorptionProperty")
    private String waterAbsorptionProperty;

    private List<String> testItems;

    private List<String> storageRequirements;

    private List<String> productMethods;

    private List<String> dangerProperties;
}
