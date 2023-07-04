package com.ruoyi.web.controller.system.RecommandBuilder;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.Neo4j.R;
import com.ruoyi.system.domain.AssemblyPojo.Process.ProcessElement;
import com.ruoyi.system.domain.AssemblyPojo.Resource.AssemblyResource;
import com.ruoyi.system.domain.AssemblyPojo.Structure.AssemblyElement;
import com.ruoyi.system.service.ElementService.ElementService;
import com.ruoyi.system.service.KnowledgeService.Prescription.PrescriptionService;
import com.ruoyi.system.service.ProcessService.ProcessService;
import com.ruoyi.system.service.ResourceService.ResourceService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/recommand")
public class RecommandController extends BaseController {

    @Resource
    private ProcessService processService;

    @Resource
    private ElementService elementService;

    @Resource
    private ResourceService resourceService;

    @Resource
    private PrescriptionService prescriptionService;


    @ResponseBody
    @PostMapping("/getRecommandProcess")
    public R<Set<ProcessElement>> getRecommandProcess(@RequestBody RecommandProcessVo vo) {
        try {
            LinkedHashSet<ProcessElement> recommandProcess = new LinkedHashSet<>();
            AssemblyElement element = elementService.getSingleElementById(vo.getElementId());
            if(element != null){
                List<ProcessElement> elementsByStructure = processService.getElementsByStructureAndLabel(vo.getProcessLabel(), vo.getElementId());
                if(!elementsByStructure.isEmpty()){
                    recommandProcess.addAll(elementsByStructure);
                }
            }
            if (element != null) {
                element.setElementName(element.getElementName().substring(0,5));
                if(element.getElementDescription().length()>7){
                    element.setElementDescription(element.getElementDescription().substring(0,5));
                }
            }
            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withMatcher("elementName", ExampleMatcher.GenericPropertyMatcher::startsWith)
                    .withMatcher("elementDescription", ExampleMatcher.GenericPropertyMatcher::startsWith)
                    .withIgnorePaths("elementId","elementQuantity","elementSource","elementDensity","elementWetArea","elementVolume","elementMass","dynamicLabels","elementBoundingBox","subElements","modelFile","associatedFiles");
            Example<AssemblyElement> example = Example.of(element, matcher);
            List<AssemblyElement> similarElements = elementService.getSimilarElements(example);
            if(!similarElements.isEmpty()){
                for (AssemblyElement similarElement : similarElements) {
                    List<ProcessElement> similarProcessElements = processService.getElementsByStructureAndLabel(vo.getProcessLabel(),similarElement.getElementId());
                    if(!similarProcessElements.isEmpty()){
                        recommandProcess.addAll(similarProcessElements);
                    }
                }
            }
            List<ProcessElement> typicalProcessByLabel = processService.getTypicalProcessByLabel(vo.getProcessLabel());
            if(!typicalProcessByLabel.isEmpty()){
                recommandProcess.addAll(typicalProcessByLabel);
            }
            System.out.println(recommandProcess);
            return R.success(recommandProcess);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    ;

    @ResponseBody
    @PostMapping("/getRecommandSequence")
    public R<Set<ProcessElement>> getRecommandSequence(@RequestBody RecommandSequenceVo vo) {
        try {
            LinkedHashSet<ProcessElement> recommandSequence = new LinkedHashSet<>();
            if(vo.getSequenceId() != 0){
                ProcessElement element = processService.getSingleElementById(vo.getSequenceId());
                List<ProcessElement> elements = processService.getAfterElementsById(element.getElementId());
                if(!elements.isEmpty()){
                    recommandSequence.addAll(elements);
                }
            }
            if(vo.getOriginSequence() != null){
                ProcessElement element = vo.getOriginSequence();
                ExampleMatcher equalMatcher = ExampleMatcher.matching()
                        .withMatcher("elementName", ExampleMatcher.GenericPropertyMatcher::contains)
                        .withMatcher("elementDescription", ExampleMatcher.GenericPropertyMatcher::contains)
                        .withIgnorePaths("elementId","elementNumber","elementRemark","elementRequirements","elementOtherProperties","dynamicLabels","hasSubElements"
                                ,"subElements","associatedStructure","usedResources","associatedFiles");
                Example<ProcessElement> example = Example.of(element, equalMatcher);
                for (ProcessElement processElement : processService.getSimilarElement(example)) {
                    List<ProcessElement> elements = processService.getAfterElementsById(processElement.getElementId());
                    if(!elements.isEmpty()){
                        recommandSequence.addAll(elements);
                    }
                }

                ProcessElement sequence = vo.getOriginSequence();
                sequence.setElementName(sequence.getElementName().substring(0,5));
                if(sequence.getElementDescription().length()>7){
                    sequence.setElementDescription(sequence.getElementDescription().substring(0,5));
                }


                ExampleMatcher matcher = ExampleMatcher.matching()
                        .withMatcher("elementName", ExampleMatcher.GenericPropertyMatcher::startsWith)
                        .withMatcher("elementDescription", ExampleMatcher.GenericPropertyMatcher::startsWith)
                        .withIgnorePaths("elementId","elementNumber","elementRemark","elementRequirements","elementOtherProperties","dynamicLabels","hasSubElements"
                                ,"subElements","associatedStructure","usedResources","associatedFiles");
                Example<ProcessElement> similarExample = Example.of(sequence, matcher);
                List<ProcessElement> similarSequenceByLabel = processService.getSimilarSequenceByLabel(vo.getLabel(), similarExample);
                if(!similarSequenceByLabel.isEmpty()){
                    for (ProcessElement similarElement : similarSequenceByLabel) {
                        List<ProcessElement> elements = processService.getAfterElementsById(similarElement.getElementId());
                        if(!elements.isEmpty()){
                            recommandSequence.addAll(elements);
                        }
                    }
                }
            }
            List<ProcessElement> typicalSequenceByLabel = processService.getTypicalSequenceByLabel(vo.getLabel());
            if(!typicalSequenceByLabel.isEmpty()){
                recommandSequence.addAll(typicalSequenceByLabel);
            }
            System.out.println(recommandSequence);
            return R.success(recommandSequence);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    ;

    @ResponseBody
    @PostMapping("/getRecommandStep")
    public R<Set<ProcessElement>> getRecommandStep(@RequestBody RecommandStepVo vo) {
        try {
            LinkedHashSet<ProcessElement> recommandStep = new LinkedHashSet<>();
            if(vo.getStepId() != 0){
                ProcessElement element = processService.getSingleElementById(vo.getStepId());
                List<ProcessElement> elements = processService.getAfterElementsById(element.getElementId());
                if(!elements.isEmpty()){
                    recommandStep.addAll(elements);
                }
            }
            if(vo.getOriginStep() != null){
                ProcessElement element = vo.getOriginStep();
                ExampleMatcher equalMatcher = ExampleMatcher.matching()
                        .withMatcher("elementName", ExampleMatcher.GenericPropertyMatcher::contains)
                        .withMatcher("elementDescription", ExampleMatcher.GenericPropertyMatcher::contains)
                        .withIgnorePaths("elementId","elementNumber","elementRemark","elementRequirements","elementOtherProperties","dynamicLabels","hasSubElements"
                                ,"subElements","associatedStructure","usedResources","associatedFiles");
                Example<ProcessElement> example = Example.of(element, equalMatcher);
                for (ProcessElement processElement : processService.getSimilarElement(example)) {
                    List<ProcessElement> elements = processService.getAfterElementsById(processElement.getElementId());
                    if(!elements.isEmpty()){
                        recommandStep.addAll(elements);
                    }
                }

                ProcessElement sequence = vo.getOriginStep();
                if(sequence.getElementName().length()>7){
                    sequence.setElementName(sequence.getElementName().substring(0,3));
                }
                if(sequence.getElementDescription().length() > 7){
                    sequence.setElementDescription(sequence.getElementDescription().substring(0,3));
                }


                ExampleMatcher matcher = ExampleMatcher.matching()
                        .withMatcher("elementName", ExampleMatcher.GenericPropertyMatcher::startsWith)
                        .withMatcher("elementDescription", ExampleMatcher.GenericPropertyMatcher::startsWith)
                        .withIgnorePaths("elementId","elementNumber","elementRemark","elementRequirements","elementOtherProperties","dynamicLabels","hasSubElements"
                                ,"subElements","associatedStructure","usedResources","associatedFiles");
                Example<ProcessElement> similarExample = Example.of(sequence, matcher);
                List<ProcessElement> similarStepByLabel = processService.getSimilarStepByLabel(vo.getLabel(), similarExample);
                if(!similarStepByLabel.isEmpty()){
                    for (ProcessElement similarElement : similarStepByLabel) {
                        List<ProcessElement> elements = processService.getAfterElementsById(similarElement.getElementId());
                        if(!elements.isEmpty()){
                            recommandStep.addAll(elements);
                        }
                    }
                }
            }
            List<ProcessElement> typicalStepByLabel = processService.getTypicalStepByLabel(vo.getLabel());
            if(!typicalStepByLabel.isEmpty()){
                recommandStep.addAll(typicalStepByLabel);
            }
            System.out.println(recommandStep);
            LinkedHashSet<ProcessElement> objects = new LinkedHashSet<>();
            return R.success(recommandStep);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    ;

    @ResponseBody
    @PostMapping("/getRecommandResource")
    public R<Set<ProcessElement>> getRecommandResource(@RequestBody RecommandResourceVo vo) {
        try {
            LinkedHashSet<AssemblyResource> recommandResource = new LinkedHashSet<>();
            if(vo.getElementId() != 0){
                ProcessElement element = processService.getSingleElementById(vo.getElementId());
                List<AssemblyResource> resources = resourceService.getResourcesByRelatedId(element.getElementId());
                recommandResource.addAll(resources);
            }
            if(vo.getOriginElement() != null){
                ProcessElement originElement = vo.getOriginElement();
                ExampleMatcher equalMatcher = ExampleMatcher.matching()
                        .withMatcher("elementName", ExampleMatcher.GenericPropertyMatcher::contains)
                        .withMatcher("elementDescription", ExampleMatcher.GenericPropertyMatcher::contains)
                        .withIgnorePaths("elementId","elementNumber","elementRemark","elementRequirements","elementOtherProperties","dynamicLabels","hasSubElements"
                                ,"subElements","associatedStructure","usedResources","associatedFiles");
                Example<ProcessElement> example = Example.of(originElement, equalMatcher);
                for (ProcessElement element : processService.getSimilarElement(example)) {
                    List<AssemblyResource> resources = resourceService.getResourcesByRelatedId(element.getElementId());
                    if(!resources.isEmpty()){
                        recommandResource.addAll(resources);
                    }
                }
                ProcessElement element = vo.getOriginElement();
                if(element.getElementName().length()>6){
                    element.setElementName(element.getElementName().substring(0,5));
                    element.setElementDescription(element.getElementDescription().substring(0,5));
                }
                ExampleMatcher matcher = ExampleMatcher.matching()
                        .withMatcher("elementName", ExampleMatcher.GenericPropertyMatcher::startsWith)
                        .withMatcher("elementDescription", ExampleMatcher.GenericPropertyMatcher::startsWith)
                        .withIgnorePaths("elementId","elementNumber","elementRemark","elementRequirements","elementOtherProperties","dynamicLabels","hasSubElements"
                                ,"subElements","associatedStructure","usedResources","associatedFiles");
                Example<ProcessElement> similarExample = Example.of(element, matcher);
                for (ProcessElement processElement : processService.getSimilarElement(similarExample)) {
                    List<AssemblyResource> resources = resourceService.getResourcesByRelatedId(processElement.getElementId());
                    if(!resources.isEmpty()){
                        recommandResource.addAll(resources);
                    }
                }
            }
            List<ProcessElement> typicalElement = processService.getTypicalElementByLabel(vo.getLabel());
            if(!typicalElement.isEmpty()){
                for (ProcessElement element : typicalElement) {
                    List<AssemblyResource> resources = resourceService.getResourcesByRelatedId(element.getElementId());
                    if(!resources.isEmpty()){
                        recommandResource.addAll(resources);
                    }
                }
            }
            System.out.println(recommandResource);
            return R.success(recommandResource);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    }

    ;

}
