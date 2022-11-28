package com.ruoyi.web.controller.system.PrescriptionBuilder;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.Neo4j.R;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Interface.PrescriptionInterface;
import com.ruoyi.system.domain.AssemblyPojo.Knowledge.Prescription.Prescription;
import com.ruoyi.system.service.KnowledgeService.Prescription.Impl.PrescriptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController extends BaseController {

    @Autowired
    private PrescriptionServiceImpl prescriptionService;

    @ResponseBody
    @PostMapping("/getAllPrescriptionsByType")
    public R<List<PrescriptionInterface>> getAllPrescriptionsByType(String dynamicLabel) {
        try{
            List<PrescriptionInterface> prescriptions = prescriptionService.getPrescriptionsByLabel(dynamicLabel);
            System.out.println(prescriptions);
            return R.success(prescriptions);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };

    @ResponseBody
    @PostMapping("/createPrescription")
    public R<PrescriptionInterface> createPrescription(@RequestBody Prescription prescription) {
        try{
            PrescriptionInterface prescriptionInterface = prescriptionService.createPrescription(prescription);
            System.out.println(prescriptionInterface);
            return R.success(prescriptionInterface);
        }catch(Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }
    };
}
