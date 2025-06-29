package com.kaif.healthcare.Service;

import com.kaif.healthcare.Emuns.Gender;
import com.kaif.healthcare.Exceptions.APIException;
import com.kaif.healthcare.Exceptions.ResourceNotFoundException;
import com.kaif.healthcare.Model.Doctor;
import com.kaif.healthcare.Model.Patient;
import com.kaif.healthcare.Model.Prescription;
import com.kaif.healthcare.Payloads.PatientDTO;
import com.kaif.healthcare.Repositories.DoctorRepo;
import com.kaif.healthcare.Repositories.PatientRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private ModelMapper modelMapper;


    /* Get Patient */
    @Transactional
    public List<PatientDTO> getAllPatients(){

        List<Patient> allPatients= patientRepo.findAll();
        List<PatientDTO> patientDTO= new ArrayList<>();
        for(Patient obj: allPatients){
            patientDTO.add(modelMapper.map(obj, PatientDTO.class));
        }

        return patientDTO;

    }


    /* Get Patient By Id*/
    public PatientDTO getPatientById(Long patientId){
        Patient patientWithId= patientRepo.findById(patientId).
                orElseThrow(() -> new ResourceNotFoundException("Patient Not Found"));

        return modelMapper.map(patientWithId, PatientDTO.class);
    }


    /* Create Patient */
    @Transactional
    public PatientDTO addPatient(PatientDTO patientDTO, Long doctorId){

        Patient patient= modelMapper.map(patientDTO, Patient.class);

        //Check if Patient Already exists
        if(patientRepo.findByEmail(patientDTO.getEmail()).isPresent()){
            throw new APIException("Patient With Email Already Exists");
        }

        //Setting Patients Gender
        if(patientDTO.getGender().equals(Gender.MALE)){
            patient.setGender(Gender.MALE);
        }else{
            patient.setGender(Gender.FEMALE);
        }

        //Setting Patient Address
        patient.setPatientAddress(patientDTO.getPatientAddress());

        //Setting Medical Record
        patient.setMedicalRecord(patientDTO.getMedicalRecord());

        //Setting Doctor
        if(patientDTO.getDoctor()!=null && patientDTO.getDoctor().getId()!=null) {

            Doctor existingDoctor= doctorRepo.findById(patientDTO.getDoctor().getId()).
                    orElseThrow(() -> new ResourceNotFoundException("Doctor Not Found with ID: " + patientDTO.getDoctor().getId()));
            patient.setDoctor(existingDoctor);

        }

        //Setting Prescription
        //Changes needed
        if(patientDTO.getPrescription() != null && !patientDTO.getPrescription().isEmpty()){
            List<Prescription> patientPrescription= patientDTO.getPrescription();
            for(Prescription obj : patientPrescription){
                Prescription prescription= modelMapper.map(obj, Prescription.class);
                patient.getPrescription().add(prescription);
            }
        }

        //Setting Patient with doctor
        Doctor setPatientDoctor= doctorRepo.findById(doctorId).
                orElseThrow(() -> new ResourceNotFoundException("Doctor Not Found with ID: " + doctorId));
        patient.setDoctor(setPatientDoctor);

        //Saving Patient
        patientRepo.save(patient);

        return modelMapper.map(patient, PatientDTO.class);

    }

    /*Delete a Patient*/
    public String deletePatient(Long patientId){
        patientRepo.deleteById(patientId);
        return "Patient Deleted with Id: " + patientId;
    }

    /*Update a Patient*/
    public String updatePatient(Long patientId, PatientDTO patientDTO){
        //Check if patient exist in the DB
        Patient checkId= patientRepo.findById(patientId).
                orElseThrow(() -> new ResourceNotFoundException("Patient Not Found"));

        checkId.setName(patientDTO.getName());
        checkId.setAge(patientDTO.getAge());
        checkId.setEmail(patientDTO.getEmail());

        patientRepo.save(checkId);

        return "Patient Updated with Id: " + patientId;

    }

}
