package com.kaif.healthcare.Controller;

import com.kaif.healthcare.Model.PrescriptionId;
import com.kaif.healthcare.ManyToMany.PrescriptionDetailsDTO;
import com.kaif.healthcare.Service.PrescriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    /* Create Prescription */
    @PostMapping("/public/prescriptions")
    public ResponseEntity<PrescriptionDetailsDTO> createPrescription(@Valid @RequestBody PrescriptionDetailsDTO prescription) {
        PrescriptionDetailsDTO prescriptionDetailsDTO = prescriptionService.createPrescription(prescription);
        return ResponseEntity.ok(prescriptionDetailsDTO);
    }

    /* Get All Prescriptions */
    @GetMapping("/public/prescriptions")
    public ResponseEntity<List<PrescriptionDetailsDTO>> getAllPrescriptions() {
        List<PrescriptionDetailsDTO> prescriptions= prescriptionService.getAllPrescriptions();
        return ResponseEntity.ok(prescriptions);
    }

    /* Get Prescription with ID */
    @GetMapping("/public/prescriptions/PrescriptionId")
    public ResponseEntity<PrescriptionDetailsDTO> getPrescriptionById(@RequestBody PrescriptionId prescriptionId) {
        PrescriptionDetailsDTO prescriptionDetailsDTO = prescriptionService.getPrescriptionById(prescriptionId);
        return ResponseEntity.ok(prescriptionDetailsDTO);
    }

    /* Delete Prescription */
    @DeleteMapping("/public/prescriptions")
    public ResponseEntity<String> deletePrescriptionById(@RequestBody PrescriptionId prescriptionId) {
        String delete= prescriptionService.deletePrescriptionById(prescriptionId);
        return ResponseEntity.ok(delete);
    }

    /* Update Prescription */
    @PutMapping("/public/prescriptions")
    public ResponseEntity<PrescriptionDetailsDTO> updatePrescription(@RequestBody PrescriptionDetailsDTO prescriptionDetailsDTO) {
        PrescriptionDetailsDTO update= prescriptionService.updatePrescription(prescriptionDetailsDTO);
        return ResponseEntity.ok(update);
    }

}
