package com.kaif.healthcare.Controller;

import com.kaif.healthcare.Payloads.MedicalRecordDTO;
import com.kaif.healthcare.Service.MedicalRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    /* Create Medical Record */
    @PostMapping("/public/medicalRecords")
    public ResponseEntity<MedicalRecordDTO> addMedicalRecord(@Valid @RequestBody MedicalRecordDTO MedicalRecordDTO) {
        MedicalRecordDTO medicalRecordDTO = medicalRecordService.createMedicalRecord(MedicalRecordDTO);
        return new ResponseEntity<>(medicalRecordDTO, HttpStatus.CREATED);
    }

    /* Get Medical Record with Id*/
    @GetMapping("/public/medicalRecords/{medicalRecordId}")
    public ResponseEntity<MedicalRecordDTO> getMedicalRecord(@PathVariable Long medicalRecordId) {
        MedicalRecordDTO medicalRecord= medicalRecordService.getMedicalRecord(medicalRecordId);
        return new ResponseEntity<>(medicalRecord, HttpStatus.OK);
    }

    /* Get Medical Record*/
    @GetMapping("/public/medicalRecords")
    public ResponseEntity<List<MedicalRecordDTO>> getMedicalRecords() {
        List<MedicalRecordDTO> medicalRecords= medicalRecordService.getMedicalRecords();
        return new ResponseEntity<>(medicalRecords, HttpStatus.OK);
    }

    /* Delete Medical Record*/
    @DeleteMapping("/public/medicalRecords/{medicalRecordId}")
    public ResponseEntity<String> deleteMedicalRecord(@PathVariable Long medicalRecordId) {
        String medicalRecords= medicalRecordService.deleteMedicalRecord(medicalRecordId);
        return new ResponseEntity<>(medicalRecords, HttpStatus.OK);
    }

    /* Update Medical Record*/
    @PutMapping("/public/medicalRecords")
    public ResponseEntity<MedicalRecordDTO> updateMedicalRecord(@Valid @RequestBody MedicalRecordDTO medicalRecordDTO) {
        MedicalRecordDTO medicalRecords= medicalRecordService.updateMedicalRecord(medicalRecordDTO);
        return new ResponseEntity<>(medicalRecords, HttpStatus.OK);
    }


}
