package com.ruoyi.system.domain.AssemblyPojo.Structure.vo;

import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyConstraint;
import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyPart;
import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyStructure;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartsWithConstraints {

    private AssemblyConstraint constraint;

    private Set<AssemblyPart> parts;
}
