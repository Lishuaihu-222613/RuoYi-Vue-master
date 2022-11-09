package com.ruoyi.system.service.KnowledgeService.Material;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.ProduceMethodInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.ProduceMethod;

import java.util.Collection;

public interface ProduceMethodService {

    /**
     * 创建生产方法节点
     * @param produceMethod
     * @return
     */
    ProduceMethod createProduceMethod(ProduceMethod produceMethod);

    /**
     * 根据Id查询节点
     * @param methodId
     * @return
     */
    ProduceMethodInterface getProduceMethodInterfaceById(Long methodId);

    /**
     * 查询材料关联生产方法
     * @param materialId
     * @return
     */
    Collection<ProduceMethodInterface> getProduceMethodsByMaterialId(Long materialId);

    /**
     * 根据名称获取生产方法
     * @param methodName
     * @return
     */
    Collection<ProduceMethodInterface> getProduceMethodsByName(String methodName);

    /**
     * 更新
     * @param produceMethod
     * @return
     */
    ProduceMethod updateProduceMethod(ProduceMethod produceMethod);

    /**
     * 删除
     * @param produceMethod
     */
    void deleteProduceMethod(ProduceMethod produceMethod);

    /**
     * 依据Id删除
     * @param methodId
     */
    void deleteProduceMethodById(Long methodId);

}
