package com.kaif.healthcare.Service;

import com.kaif.healthcare.Exceptions.ResourceNotFoundException;
import com.kaif.healthcare.Model.*;
import com.kaif.healthcare.Payloads.MedicineDTO;
import com.kaif.healthcare.Payloads.PrescriptionDTO;
import com.kaif.healthcare.Repositories.DoctorRepo;
import com.kaif.healthcare.Repositories.MedicineRepo;
import com.kaif.healthcare.Repositories.PatientRepo;
import com.kaif.healthcare.Repositories.PrescriptionRepo;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepo prescriptionRepo;

    @Autowired
    private MedicineRepo medicineRepo;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private ModelMapper modelMapper;


    /* Create Prescription */
    @Override
    @Transactional
    public PrescriptionDTO createPrescription(PrescriptionDTO prescriptionDTO) {

        Prescription prescription= modelMapper.map(prescriptionDTO, Prescription.class);

        // 1. Check if Patient And Doctor exists in the DB
        Doctor doctor= doctorRepo.findById(prescriptionDTO.getDoctorId()).
                orElseThrow( () -> new ResourceNotFoundException("Doctor", "doctorId", prescriptionDTO.getDoctorId()));

        Patient patient= patientRepo.findById(prescriptionDTO.getPatientId()).
                orElseThrow( () -> new ResourceNotFoundException("Patient", "patientId", prescriptionDTO.getPatientId()));

        // 2. Create the composite ID and set it
        PrescriptionId prescriptionId = new PrescriptionId(patient.getId(), doctor.getId());
        prescription.setId(prescriptionId);

        //3. Set Doctor and patient
        prescription.setDoctorId(doctor);
        prescription.setPatientId(patient);

        // 4. Set Prescription information
        prescription.setPrescription(prescriptionDTO.getPrescription());

        //Fetch Medicines from prescriptionDTO and set them
        List<Medicine> medicines = new ArrayList<>();
        if(prescriptionDTO.getMedicinesDTO() != null){
            for(MedicineDTO medicineDTO : prescriptionDTO.getMedicinesDTO()){
                Medicine medicine= modelMapper.map(medicineDTO, Medicine.class);
                medicine.getPrescription().add(prescription);
                medicines.add(medicine);
            }
        }
        prescription.setMedicines(medicines);
        prescriptionRepo.save(prescription);

        return modelMapper.map(prescription, PrescriptionDTO.class);

    }

    /* Get All Prescriptions */
    @Override
    @Transactional
    public List<PrescriptionDTO> getAllPrescriptions() {

        List<Prescription> prescriptions = prescriptionRepo.findAll();
        List<PrescriptionDTO> prescriptionDTO = new ArrayList<>();
        if(!prescriptions.isEmpty()){
            for(Prescription obj : prescriptions){
                prescriptionDTO.add(modelMapper.map(obj, PrescriptionDTO.class));
            }
        }else{
            throw new ResourceNotFoundException("No prescriptions found");
        }

        return prescriptionDTO;

    }

    @Override
    @Transactional
    public PrescriptionDTO getPrescriptionById(PrescriptionId prescriptionId) {

        PrescriptionId idToFind = new PrescriptionId(prescriptionId.getDoctorId(), prescriptionId.getPatientId());

        Prescription prescription= prescriptionRepo.findById(idToFind).
                orElseThrow(() -> new ResourceNotFoundException("PrescriptionId not found"));

        //Need to initialize lazy loaded entities
        if(prescription.getPatientId() != null){
            Hibernate.initialize(prescription.getPatientId());
        }

        if(prescription.getMedicines() != null){
            Hibernate.initialize(prescription.getMedicines());
        }

        prescription.setPatientId(prescription.getPatientId());
        prescription.setMedicines(prescription.getMedicines());

        return modelMapper.map(prescription, PrescriptionDTO.class);
    }

    @Override
    @Transactional
    public String deletePrescriptionById(PrescriptionId prescriptionId) {
        if(prescriptionRepo.findById(prescriptionId).isEmpty()){
            throw new ResourceNotFoundException("PrescriptionId not found");
        }else{
            prescriptionRepo.deleteById(prescriptionId);
        }
        return "Prescription deleted";
    }

}
