package com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.TypicalProcess;
import com.ruoyi.system.domain.AssemblyPojo.RawMaterial.RawMaterial;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyProduct;
import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyStructure;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.Set;

//@Node("Prescription")
public class Prescription {

    private Long Id;

    private String prescriptionName;

    private String prescriptionDescription;

    private Set<hasRawMaterialElement> materialElements;

    private Set<AssemblyStructure> product;

    private Set<TypicalProcess> typicalProcesses;



}
