package com.kaif.healthcare.Controller;

import com.kaif.healthcare.Model.PrescriptionId;
import com.kaif.healthcare.Payloads.PrescriptionDTO;
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
    public ResponseEntity<PrescriptionDTO> createPrescription(@Valid @RequestBody PrescriptionDTO prescription) {
        PrescriptionDTO prescriptionDTO = prescriptionService.createPrescription(prescription);
        return ResponseEntity.ok(prescriptionDTO);
    }

    /* Get All Prescriptions */
    @GetMapping("/public/prescriptions")
    public ResponseEntity<List<PrescriptionDTO>> getAllPrescriptions() {
        List<PrescriptionDTO> prescriptions= prescriptionService.getAllPrescriptions();
        return ResponseEntity.ok(prescriptions);
    }

    /* Get Prescription with ID */
    @GetMapping("/public/prescriptions/PrescriptionId")
    public ResponseEntity<PrescriptionDTO> getPrescriptionById(@RequestBody PrescriptionId prescriptionId) {
        PrescriptionDTO prescriptionDTO= prescriptionService.getPrescriptionById(prescriptionId);
        return ResponseEntity.ok(prescriptionDTO);
    }

    /* Delete Prescription */
    @DeleteMapping("/public/prescriptions")
    public ResponseEntity<String> deletePrescriptionById(@RequestBody PrescriptionId prescriptionId) {
        String delete= prescriptionService.deletePrescriptionById(prescriptionId);
        return ResponseEntity.ok(delete);
    }

    /* Update Prescription */
    @PutMapping("/public/prescriptions")
    public ResponseEntity<PrescriptionDTO> updatePrescription(@RequestBody PrescriptionDTO prescriptionDTO) {
        PrescriptionDTO update= prescriptionService.updatePrescription(prescriptionDTO);
        return ResponseEntity.ok(update);
    }

}
