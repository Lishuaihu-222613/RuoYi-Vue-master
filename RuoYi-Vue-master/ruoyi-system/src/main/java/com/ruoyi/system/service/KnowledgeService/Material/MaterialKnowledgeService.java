package com.ruoyi.system.service.KnowledgeService.Material;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.MaterialKnowledgeInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.MaterialKnowledge;

import java.util.List;
import java.util.Optional;

public interface MaterialKnowledgeService {

    /**
     * 获得全部材料知识
     * @return
     */
    List<MaterialKnowledge> getAllMaterialKnowledge();

    /**
     * 根据id获得材料知识
     *
     * @param mKid
     * @return
     */
    Optional<MaterialKnowledgeInterface> getMaterialKnowledgeById(Long mKid);

    /**
     * 创建材料知识
     * @param materialKnowledge
     * @return
     */
    MaterialKnowledge createMaterialKnowledge(MaterialKnowledge materialKnowledge);

    /**
     * 更新材料知识
     * @param materialKnowledge
     * @return
     */
    MaterialKnowledge updateMaterialKnowledge(MaterialKnowledge materialKnowledge);

    /**
     * 删除材料知识
     * @param materialKnowledge
     */
    void deleteMaterialKnowledge(MaterialKnowledge materialKnowledge);

    /**
     * 根据id删除材料知识
     * @param mKid
     */
    void deleteMaterialKnowledgeById(Long mKid);

}
