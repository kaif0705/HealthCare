package com.kaif.healthcare.Controller;

import com.kaif.healthcare.Model.Patient;
import com.kaif.healthcare.Payloads.PatientDTO;
//import com.kaif.healthcare.Service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("api/")
//public class PatientController {

//    @Autowired
//    private PatientService patientService;

    //Get All Patients
//    @GetMapping("/public/patients")
//    public ResponseEntity<List<PatientDTO>> getAllPatients(){
//
//        List<PatientDTO> patientDTOs= patientService.getAllPatients();
//        if(patientDTOs.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }else{
//            return new ResponseEntity<>(patientDTOs, HttpStatus.OK);
//        }
//    }
//
//    //Get Patient By Id
//    @GetMapping("/public/patient/{patientId}")
//    public ResponseEntity<PatientDTO> patientById(@PathVariable Long patientId){
//
//        PatientDTO patient= patientService.getPatientById(patientId);
//        return new ResponseEntity<>(patient, HttpStatus.OK);
//    }
//
//    //Create Patient
//    @PostMapping("/public/patient/create_patient")
//    public ResponseEntity<PatientDTO> createPatient(@Valid @RequestBody PatientDTO patientDTO){
//
//        PatientDTO patient= patientService.addPatient(patientDTO);
//        return new ResponseEntity<>(patient, HttpStatus.OK);
//
//    }
//
//    @DeleteMapping("/public/patient/{patientId}")
//    public ResponseEntity<PatientDTO> deletePatient(@PathVariable Long patientId){
//        return null;
//    }

//}
