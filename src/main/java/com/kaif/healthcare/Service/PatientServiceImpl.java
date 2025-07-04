package com.kaif.healthcare.Service;

import com.kaif.healthcare.Emuns.Gender;
import com.kaif.healthcare.Exceptions.APIException;
import com.kaif.healthcare.Exceptions.ResourceNotFoundException;
import com.kaif.healthcare.ManyToMany.Medicine;
import com.kaif.healthcare.ManyToMany.Prescription;
import com.kaif.healthcare.ManyToMany.PrescriptionDetailsDTO;
import com.kaif.healthcare.Model.Doctor;
import com.kaif.healthcare.Model.Patient;
import com.kaif.healthcare.Payloads.PatientDTOs.CreatePatientDTO;
import com.kaif.healthcare.Payloads.PatientDTOs.PatientDTO;
import com.kaif.healthcare.Payloads.PatientDTOs.UpdatePatientDTO;
import com.kaif.healthcare.Repositories.DoctorRepo;
import com.kaif.healthcare.Repositories.MedicalRecordRepo;
import com.kaif.healthcare.Repositories.PatientRepo;
import com.kaif.healthcare.Repositories.PrescriptionRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private PrescriptionRepo prescriptionRepo;

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

        //Check if Patient ID is null
        if(patientId == null){
            throw new APIException("Patient id is null");
        }

        //Check if patient exists in DB
        Patient patientWithId= patientRepo.findById(patientId).
                orElseThrow(() -> new ResourceNotFoundException("Patient Not Found"));

        List<Prescription> patientPrescription= prescriptionRepo.findPrescriptionByPatientId(patientId);
        patientWithId.setPrescription(patientPrescription);

        PatientDTO dtos= modelMapper.map(patientWithId, PatientDTO.class);

        for(Prescription prescription: patientPrescription){
            List<PrescriptionDetailsDTO> dto= new ArrayList<>(Collections.singletonList(modelMapper.map(prescription, PrescriptionDetailsDTO.class)));
            List<Medicine> medicines= prescription.getMedicines();

            for(Medicine medicine: medicines){
                for(PrescriptionDetailsDTO prescriptionDetailsDTO: dto){
                    prescriptionDetailsDTO.addMedicineId(medicine.getId());
                }
            }
            dtos.setPrescriptionDetailsDTO(dto);
        }

        return dtos;
    }


    /* Create Patient */
    @Override
    @Transactional
    public CreatePatientDTO addPatient(CreatePatientDTO patientDTO, Long doctorId){

        Patient patient= modelMapper.map(patientDTO, Patient.class);

        //Check if Patient Already exists
        if(patientRepo.findPatientByEmail(patientDTO.getEmail()).isPresent()){
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


        //Setting Patient with doctor

        assert doctorId != null;
        Doctor setPatientDoctor= doctorRepo.findById(doctorId).
                orElseThrow(() -> new ResourceNotFoundException("Doctor Not Found with ID: " + doctorId));
        patient.setDoctor(setPatientDoctor);

        //Saving Patient
        patientRepo.save(patient);

        return modelMapper.map(patient, CreatePatientDTO    .class);

    }

    /*Delete a Patient*/
    @Override
    @Transactional
    public String deletePatient(Long patientId){
        Patient patient= patientRepo.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient Not Found"));
        patientRepo.deletePatientByEmail(patient.getEmail());
        return "Patient Deleted with Id: " + patientId;
    }

    /*Update a Patient*/
    public String updatePatient(Long patientId, UpdatePatientDTO dtos){
        //Check if patient exist in the DB
        Patient checkId= patientRepo.findById(patientId).
                orElseThrow(() -> new ResourceNotFoundException("Patient Not Found"));

        checkId.setName(dtos.getName());
        checkId.setAge(dtos.getAge());
        checkId.setEmail(dtos.getEmail());
        checkId.setPatientAddress(dtos.getPatientAddress());

        patientRepo.save(checkId);

        return "Patient Updated with Id: " + patientId;

    }

}
