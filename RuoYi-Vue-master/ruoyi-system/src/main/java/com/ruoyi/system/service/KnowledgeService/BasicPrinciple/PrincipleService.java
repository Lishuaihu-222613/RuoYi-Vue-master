package com.ruoyi.system.service.KnowledgeService.BasicPrinciple;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.BasicPrinciple.Principle;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PrincipleService {

    /**
     *
     * @return
     */
    Page<Principle> getAllPrinciple();

    /**
     *
     * @param label
     * @return
     */
    Page<Principle> getAllPrincipleByLabel(String label);

    /**
     *
     * @param principleId
     * @return
     */
    Principle getPrincipleById(Long principleId);

    /**
     *
     * @param name
     * @return
     */
    List<Principle> getPrincipleByName(String name);

    /**
     *
     * @param condition
     * @return
     */
    List<Principle> getAllPrincipleByCondition(String condition);

    /**
     *
     * @param principle
     * @return
     */
    Principle createPrinciple(Principle principle);

    /**
     *
     * @param principle
     * @return
     */
    Principle updatePrinciple(Principle principle);

    /**
     *
     * @param principleId
     */
    void deletePrinciple(Long principleId);
}
