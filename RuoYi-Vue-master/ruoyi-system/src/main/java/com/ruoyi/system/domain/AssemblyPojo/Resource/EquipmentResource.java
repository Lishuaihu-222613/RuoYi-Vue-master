package com.ruoyi.system.domain.AssemblyPojo.Resource;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.Process.TypicalProcedure;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.TypicalKnowledge.Process.TypicalProcess;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Node({"设备资源","EquipmentResource"})
public class EquipmentResource extends AssemblyResource{

    @Property(name = "设备能力")
    private Map<String,String> equipmentCapacity;

    @Property(name = "设备功率")
    private double equipmentPower;

    @Property(name = "设备价格")
    private double equipmentPrice;

    @Property(name = "生产日期")
    private Date productionDate;

    @Property(name = "制造商")
    private String manufacturer;

    @Property(name = "设备型号")
    private String equipmentSize;

    @Property(name = "设备状态")
    private String equipmentState;

    @Property(name = "折旧率")
    private double depreciationRate;

    @Property(name = "负载率")
    private double loadRate;

    @Property(name = "注意事项")
    private List<String>  Attentions;

    @Property(name = "principle")
    private String principle;

    @Relationship(type = "hasInstallTool" , direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyResource> installTools;

    @Relationship(type = "hasSubEquipments" , direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyResource> subEquipments;

    @Relationship(type = "hasSuitableTool" , direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyResource> suitableTools;

    @Relationship(type = "hasUnSuitableTool" , direction = Relationship.Direction.OUTGOING)
    private Set<AssemblyResource> unsuitableTools;

    @Relationship(type = "hasSuitableProcess" , direction = Relationship.Direction.OUTGOING)
    private Set<TypicalProcess> suitableProcesses;

    @Relationship(type = "hasSuitableProcedure" , direction = Relationship.Direction.OUTGOING)
    private Set<TypicalProcedure> suitableProcedures;

    @Relationship(type = "hasUnSuitableProcess" , direction = Relationship.Direction.OUTGOING)
    private Set<TypicalProcess> unsuitableProcesses;

    @Relationship(type = "hasUnSuitableProcedure" , direction = Relationship.Direction.OUTGOING)
    private Set<TypicalProcedure> unsuitableProcedures;

}
