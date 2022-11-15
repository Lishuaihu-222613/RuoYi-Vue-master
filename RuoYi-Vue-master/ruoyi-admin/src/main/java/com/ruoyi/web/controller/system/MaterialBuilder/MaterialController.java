package com.ruoyi.web.controller.system.MaterialBuilder;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.Neo4j.R;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Interface.MaterialInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge.Material;
import com.ruoyi.system.service.KnowledgeService.Material.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/material")
public class MaterialController extends BaseController {

    @Autowired
    private MaterialService materialService;

    @ResponseBody
    @PostMapping("/getAllMaterialsByType")
    public R<List<MaterialInterface>> getAllMaterialsByType(Long MKId) {
        try{
            List<MaterialInterface> materials = materialService.getMaterialsByMkId(MKId);
            System.out.println(materials);
            return R.success(materials);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createSingleMaterial")
    public R<MaterialInterface> createSingleMaterial(Material material) {
        try{
            MaterialInterface singleMaterial = (MaterialInterface) materialService.createSingleMaterial(material);
            System.out.println(singleMaterial);
            return R.success(singleMaterial);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createMaterial")
    public R<Material> createMaterial(@RequestBody Material material) {
        try{
            System.out.println(material);
            Material newMaterial = materialService.createMaterial(material);
            System.out.println(newMaterial);
            return R.success(newMaterial);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

}
