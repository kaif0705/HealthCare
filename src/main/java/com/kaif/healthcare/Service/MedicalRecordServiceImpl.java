package com.kaif.healthcare.Service;

import com.kaif.healthcare.Exceptions.APIException;
import com.kaif.healthcare.Exceptions.ResourceNotFoundException;
import com.kaif.healthcare.Model.MedicalRecord;
import com.kaif.healthcare.Model.Patient;
import com.kaif.healthcare.Payloads.MedicalRecordDTOs.MedicalRecordDTO;
import com.kaif.healthcare.Payloads.PatientDTOs.PatientDTO;
import com.kaif.healthcare.Repositories.MedicalRecordRepo;
import com.kaif.healthcare.Repositories.PatientRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    private MedicalRecordRepo medicalRecordRepo;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public MedicalRecordDTO createMedicalRecord(MedicalRecordDTO medicalRecordDTO) {

        MedicalRecord medicalRecord = modelMapper.map(medicalRecordDTO, MedicalRecord.class);

        if(medicalRecordDTO.getPatientId() == null){
            throw new APIException("Patient ID Is Required!!!");
        }

        //Set Diagnose
        if(medicalRecordDTO.getDiagnose() != null){
            throw new APIException("Please enter diagnose!!!");
        }

        medicalRecordRepo.save(medicalRecord);

        medicalRecord.setPatient(patientRepo.getOne(medicalRecordDTO.getPatientId()));

        MedicalRecordDTO dto= modelMapper.map(medicalRecord, MedicalRecordDTO.class);

        //Set Patient
//        dto.setPatientDTO(modelMapper.map(patient, PatientDTO.class));

        return dto;
    }

    @Override
    @Transactional
    public MedicalRecordDTO getMedicalRecord(Long medicalRecordId) {

        //Check if Id is null
        if(medicalRecordId == null){
            throw new APIException("Medical Record ID Is Required!!!");
        }

        //Fetch medical record
        MedicalRecord medicalRecord= medicalRecordRepo.findById(medicalRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("MedicalRecord", "id", medicalRecordId));

        //Fetch Patient
        Patient patient= patientRepo.findPatientByMedicalRecordId(medicalRecord.getId());
        medicalRecord.setPatient(patient);

        //Set PatientDTO in MedicalRecordDTO
        MedicalRecordDTO dto= modelMapper.map(medicalRecord, MedicalRecordDTO.class);
        dto.setPatientDTO(modelMapper.map(patient, PatientDTO.class));

        return dto;
    }

    @Override
    @Transactional
    public List<MedicalRecordDTO> getMedicalRecords(){
        List<MedicalRecord> medicalRecords= medicalRecordRepo.findAll();
        if(medicalRecords.isEmpty()){
            throw new ResourceNotFoundException("No Medical Records Found");
        }

        List<MedicalRecordDTO> medicalRecordDTOs= new ArrayList<>();
        for(MedicalRecord medicalRecord: medicalRecords){
            medicalRecordDTOs.add(modelMapper.map(medicalRecord, MedicalRecordDTO.class));
        }

        return medicalRecordDTOs;
    }

    @Override
    @Transactional
    public String deleteMedicalRecord(Long medicalRecordId) {

        if(medicalRecordId == null){
            throw new APIException("Medical Record ID Is Required!!!");
        }

        MedicalRecord medicalRecord= medicalRecordRepo.findById(medicalRecordId).orElse(null);

        if(medicalRecord!=null){
            medicalRecordRepo.delete(medicalRecord);
            medicalRecordRepo.flush();
        }else{
            throw new ResourceNotFoundException("Medical Record", "id", medicalRecordId);
        }


        return "Medical Record Deleted with ID: " + medicalRecordId;

    }

    @Override
    @Transactional
    public MedicalRecordDTO updateMedicalRecord(MedicalRecordDTO medicalRecordDTO) {

        if(medicalRecordDTO.getId() == null){
            throw new APIException("Medical Record ID Is Required!!!");
        }

        MedicalRecord medicalRecord= medicalRecordRepo.findById(medicalRecordDTO.getId()).
                orElseThrow(() -> new ResourceNotFoundException("Medical Record", "id", medicalRecordDTO.getId()));

        medicalRecord.setDiagnose(medicalRecordDTO.getDiagnose());

        medicalRecordRepo.save(medicalRecord);
        return modelMapper.map(medicalRecord, MedicalRecordDTO.class);
    }
}
