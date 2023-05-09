package com.ruoyi.system.service.ElementService;

import com.ruoyi.system.domain.AssemblyPojo.Structure.*;
import com.ruoyi.system.domain.AssemblyPojo.Structure.vo.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface ElementService {

    /**
     * 获得所有装配产品
     * @return
     */
    List<AssemblyElement> getAllAssemblyProducts();

    List<AssemblyElement> getSubElementsById(Long elementId );

    List<AssemblyElement> getAllAssemblyProductsByLabel(String dynamicLabel);

    Page<AssemblyElement> getAllAssemblyProductsByLabel(String dynamicLabel, Pageable pageable);

    Page<AssemblyElement> getElementsByParams(Example<AssemblyElement> example,Pageable pageable);

    void deleteElements(Long[] elements);

    AssemblyElement getElementById(Long elementId);

    List<AssemblyElement> getRelatedStructure(Long relatedId);

    void modifyRelatedStructure(RelatedStructureVo vo);

    AssemblyElement getSingleElementById(Long elementId);

    AssemblyElement getParentElementById(Long elementId);

    AssemblyElement createElementForParent(ElementForParent EP);

    AssemblyElement createElement(AssemblyElement assemblyElement);

    AssemblyElement updateElementForParent(ElementForParent EP);

    AssemblyElement updateElement(AssemblyElement assemblyElement);

    List<AssemblyConstraint> getConstraintsByElementId(Long elementId);

    AssemblyConstraint createConstraintForElement(ConstraintVo vo);

    AssemblyConstraint updateConstraint(ConstraintForElement CE);

    void deleteConstraint(Long constraintId);

    void modifyRelations(RelationsVoForElement relationsVoForElement);




    /**
     * 获得某装配结构关联的全部装配约束
     * @param ASItemId
     * @return
     */
    List<AssemblyConstraint> getAllConstraintByStructureId(Long ASItemId);



    /**
     * 创建完整的装配零件，包含零件所有属性
     * @param part
     * @return
     */
    AssemblyPart createPart(AssemblyPart part);

    /**
     * 创建装配约束，包含关联的零件或组件
     * @param
     * @return
     */
    AssemblyConstraint createConstraint(ConstraintVo vo);


    /**
     * 创建待推理零件集并初始化
     * @param constraints
     * @return
     */
    Set<AssemblyPart> createUnknownParts(List<PartsWithConstraints> constraints);

}
