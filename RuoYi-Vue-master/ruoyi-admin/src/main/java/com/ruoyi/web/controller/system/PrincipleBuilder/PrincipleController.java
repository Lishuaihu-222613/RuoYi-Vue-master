package com.ruoyi.web.controller.system.PrincipleBuilder;

import com.ruoyi.system.service.KnowledgeService.BasicPrinciple.PrincipleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/Principle")
public class PrincipleController {

    @Resource
    private PrincipleService principleService;



}
