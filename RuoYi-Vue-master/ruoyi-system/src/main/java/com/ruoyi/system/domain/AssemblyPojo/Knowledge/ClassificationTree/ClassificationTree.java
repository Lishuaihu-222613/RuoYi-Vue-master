package com.ruoyi.system.domain.AssemblyPojo.Knowledge.ClassificationTree;

import org.springframework.data.neo4j.core.schema.*;

import java.util.Set;

@Node("treeNode")
public class ClassificationTree {

    @Id
    @GeneratedValue
    private Long leafId;

    @Property(name = "label")
    private String leafName;

    @Property(name = "Description")
    private String leafDescription;

    @Relationship(type = "hasSubLeaf" , direction = Relationship.Direction.OUTGOING)
    private Set<ClassificationTree> subLeafs;

}
