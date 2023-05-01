package com.ruoyi.system.service.ElementService;

import com.ruoyi.system.domain.AssemblyPojo.Structure.*;
import com.ruoyi.system.domain.AssemblyPojo.Structure.vo.Constraint;
import com.ruoyi.system.domain.AssemblyPojo.Structure.vo.ConstraintForElement;
import com.ruoyi.system.domain.AssemblyPojo.Structure.vo.ElementForParent;
import com.ruoyi.system.domain.AssemblyPojo.Structure.vo.PartsWithConstraints;
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

    List<AssemblyElement> getAllAssemblyProductsByLabel(String dynamicLabel);

    Page<AssemblyElement> getAllAssemblyProductsByLabel(String dynamicLabel, Pageable pageable);

    Page<AssemblyElement> getElementsByParams(Example<AssemblyElement> example,Pageable pageable);

    void deleteElements(Long[] elements);

    AssemblyElement getElementById(Long elementId);

    AssemblyElement createElementForParent(ElementForParent EP);

    AssemblyElement createElement(AssemblyElement assemblyElement);

    AssemblyElement updateElementForParent(ElementForParent EP);

    AssemblyElement updateElement(AssemblyElement assemblyElement);

    List<AssemblyConstraint> getConstraintsByElementId(Long elementId);

    AssemblyConstraint createConstraintForElement(Long elementId);

    AssemblyConstraint updateConstraint(ConstraintForElement CE);

    void deleteConstraint(Long constraintId);

    /**
     * 获得某装配产品的组件与零件
     * @param ProductId
     * @return
     */
    List<AssemblyStructure> getAllStructureByProductId(Long ProductId);

    /**
     * 获得某装配组件的子组件与零件
     * @param ComponentId
     * @return
     */
    List<AssemblyStructure> getAllStructureByComponentId(Long ComponentId);

    /**
     * 获得某装配结构关联的全部装配约束
     * @param ASItemId
     * @return
     */
    List<AssemblyConstraint> getAllConstraintByStructureId(Long ASItemId);

    /**
     * 获得单个装配结构
     * @param ASItemId
     * @return
     */
    AssemblyStructure getAssemblyStructureId(Long ASItemId);

    /**
     * 获得单个产品
     * @param ProductId
     * @return
     */
    AssemblyProduct getProductById(Long ProductId);

    /**
     * 获得单个组件
     * @param ComponentId
     * @return
     */
    AssemblyComponent getComponentById(Long ComponentId);

    /**
     * 获得单个零件
     * @param PartId
     * @return
     */
    AssemblyPart getPartById(Long PartId);

    /**
     * 获得装配约束
     * @param ConstraintId
     * @return
     */
    AssemblyConstraint getConstraintById(Long ConstraintId);

    /**
     * 创建完整的装配产品结构，包含组件，零件，以及相关约束
     * @param product
     * @return
     */
    AssemblyProduct createProduct(AssemblyProduct product);

    /**
     * 创建完整的装配组件结构，包含子组件，零件，以及相关约束
     * @param component
     * @return
     */
    AssemblyComponent createComponent(AssemblyComponent component);

    /**
     * 创建完整的装配零件，包含零件所有属性
     * @param part
     * @return
     */
    AssemblyPart createPart(AssemblyPart part);

    /**
     * 创建装配约束，包含关联的零件或组件
     * @param constraint
     * @return
     */
    AssemblyConstraint createConstraint(AssemblyConstraint constraint);

    /**
     * 更新装配元素
     * @param structure
     * @return
     */
    AssemblyStructure updateStructure(AssemblyStructure structure);

    /**
     * 创建待推理零件集并初始化
     * @param constraints
     * @return
     */
    Set<AssemblyPart> createUnknownParts(List<PartsWithConstraints> constraints);

}
