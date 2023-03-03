package com.ruoyi.system.domain.AssemblyPojo.Knowledge.ClassificationTree;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Set;

@Node({"分类树","treeNode"})
public class ClassificationTree {

    @Id
    @GeneratedValue
    private Long leafId;

    @Property(name = "label")
    private String leafName;

    @Property(name = "节点描述")
    private String leafDescription;

    @DynamicLabels
    private Set<String> dynamicLabels;

    @Relationship(type = "存在子节点" , direction = Relationship.Direction.OUTGOING)
    private Set<ClassificationTree> subLeafs;

}
