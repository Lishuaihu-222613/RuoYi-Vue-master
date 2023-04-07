package com.ruoyi.system.service.KnowledgeService.BasicPrinciple;

import com.ruoyi.system.domain.AssemblyPojo.Knowledge.BasicPrinciple.Principle;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PrincipleService {

    /**
     *
     * @return
     */
    Page<Principle> getAllPrinciple(Pageable pageable);

    /**
     *
     * @param label
     * @return
     */
    Page<Principle> getAllPrincipleByLabel(String label,Pageable pageable);

    /**
     *
     * @param
     * @return
     */
    Page<Principle> getAllPrincipleByParams(Example example, Pageable pageable);

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
