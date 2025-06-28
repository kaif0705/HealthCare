package com.kaif.healthcare.Service;

import com.kaif.healthcare.Exceptions.APIException;
import com.kaif.healthcare.Exceptions.ResourceNotFoundException;
import com.kaif.healthcare.Model.Doctor;
import com.kaif.healthcare.Model.Patient;
import com.kaif.healthcare.Model.Prescription;
import com.kaif.healthcare.Payloads.PatientDTO;
import com.kaif.healthcare.Repositories.DoctorRepo;
import com.kaif.healthcare.Repositories.PatientRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Service
//public class PatientServiceImpl implements PatientService {
//
////    @Autowired
////    private PatientRepo patientRepo;
////
////    @Autowired
////    private DoctorRepo doctorRepo;
////
////    @Autowired
////    private ModelMapper modelMapper;
//
//
//    /* Get Patient */
//    public List<PatientDTO> getAllPatients(){
//
//        List<Patient> allPatients= patientRepo.findAll();
//        List<PatientDTO> patientDTO= new ArrayList<>();
//        for(Patient obj: allPatients){
//            patientDTO.add(modelMapper.map(obj, PatientDTO.class));
//        }
//
//        return patientDTO;
//
//    }
//
//
//    /* Get Patient By Id*/
//    public PatientDTO getPatientById(Long patientId){
//        Patient patientWithId= patientRepo.findById(patientId).
//                orElseThrow(() -> new ResourceNotFoundException("Patient Not Found"));
//
//        return modelMapper.map(patientWithId, PatientDTO.class);
//    }
//
//
//    /* Create Patient */
//    public PatientDTO addPatient(PatientDTO patientDTO){
//
//        Patient patient= modelMapper.map(patientDTO, Patient.class);
//
//        //Setting Patient Address
//        patient.setPatientAddress(patientDTO.getAddress());
//
//        //Setting Medical Record
//        patient.setMedicalRecord(patientDTO.getMedicalRecord());
//
//        //Setting Doctor
//        if(patientDTO.getDoctor()!=null && patientDTO.getDoctor().getId()!=null) {
//
//            Doctor existingDoctor= doctorRepo.findById(patientDTO.getDoctor().getId()).
//                    orElseThrow(() -> new ResourceNotFoundException("Doctor Not Found with ID: " + patientDTO.getDoctor().getId()));
//            patient.setDoctor(existingDoctor);
//
//        }
//
//        //Setting Prescription
//        //Changes needed
//        if(patientDTO.getPrescription() != null && !patientDTO.getPrescription().isEmpty()){
//            List<Prescription> patientPrescription= patientDTO.getPrescription();
//            for(Prescription obj : patientPrescription){
//                Prescription prescription= modelMapper.map(obj, Prescription.class);
//                patient.getPrescription().add(prescription);
//            }
//        }
//
//        //Saving Patient
//        patientRepo.save(patient);
//
//        return modelMapper.map(patient, PatientDTO.class);
//
//    }


    /*Update a Patient*/


//}
