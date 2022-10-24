package com.ruoyi.system.domain.AssemblyPojo.Operation;

import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyProduct;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.Set;

@Node("AssemblyProcess")
public class AssemblyProcess {

    @Id
    @GeneratedValue
    private Long processId;

    @Property(name = "label")
    private String processName;

    @Property(name = "description")
    private String processDescription;

    @Property(name = "hasSubOperation")
    private Set<AssemblyOperation> operations;

    @Property(name = "hasAssociatedProduct")
    private AssemblyProduct associatedProduct;


}
