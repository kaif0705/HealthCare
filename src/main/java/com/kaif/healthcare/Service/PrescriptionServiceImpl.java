package com.kaif.healthcare.Service;

import com.kaif.healthcare.Exceptions.ResourceNotFoundException;
import com.kaif.healthcare.ManyToMany.Medicine;
import com.kaif.healthcare.ManyToMany.Prescription;
import com.kaif.healthcare.Model.*;
import com.kaif.healthcare.ManyToMany.MedicineDetailsDTO;
import com.kaif.healthcare.ManyToMany.PrescriptionDetailsDTO;
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
    //Pending
    public PrescriptionDetailsDTO createPrescription(PrescriptionDetailsDTO prescriptionDetailsDTO) {

        Prescription prescription = new Prescription();

        //1. Check if Patient and Doctor ID is null
        Patient patient= patientRepo.findById(prescriptionDetailsDTO.getPatientId()).orElse(null);
        if(patient == null){
            throw new ResourceNotFoundException("Patient not found with id: " + prescriptionDetailsDTO.getPatientId());
        }

        Doctor doctor= doctorRepo.findById(prescriptionDetailsDTO.getDoctorId()).orElse(null);
        if(doctor == null){
            throw new ResourceNotFoundException("Doctor not found with id: " + prescriptionDetailsDTO.getDoctorId());
        }

        //2. Create Composite Keys
        PrescriptionId compositeKey= new PrescriptionId(patient.getId(), doctor.getId());
        prescription.setId(compositeKey);

        //3. Add Prescription
        prescription.setPrescription(prescriptionDetailsDTO.getPrescription());

        //4. Add Medicines
        List<Medicine> medicines = new ArrayList<>();
        for(Long Id : prescriptionDetailsDTO.getMedicinesIds()){
            Medicine medicine= medicineRepo.findById(Id).orElseThrow(
                    () -> new ResourceNotFoundException("Medicine not found with id: " + Id)
            );
            medicines.add(medicine);
        }
        prescription.setMedicines(medicines);

        return modelMapper.map(prescription, PrescriptionDetailsDTO.class);

    }

    /* Get All Prescriptions */
    @Override
    @Transactional
    public List<PrescriptionDetailsDTO> getAllPrescriptions() {

        List<Prescription> prescriptions = prescriptionRepo.findAll();

        List<PrescriptionDetailsDTO> prescriptionDetailsDTO = new ArrayList<>();
        if(!prescriptions.isEmpty()){
            for(Prescription obj : prescriptions){
                PrescriptionDetailsDTO dto = modelMapper.map(obj, PrescriptionDetailsDTO.class);

                List<Long> medicineIds= new ArrayList<>();
                Medicine medicineFromDB= medicineRepo.findById(obj.getPatientId().getId())
                        .orElseThrow(
                        () -> new ResourceNotFoundException("Medicine" + "id" + obj.getPatientId().getId())
                );
                for(int i= 0; i < obj.getMedicines().size(); i++){
                    medicineIds.add(medicineFromDB.getId());
                }
                dto.setMedicinesIds(medicineIds);
                prescriptionDetailsDTO.add(dto);
            }

        }else{
            throw new ResourceNotFoundException("No prescriptions found");
        }

        return prescriptionDetailsDTO;

    }

    @Override
    @Transactional
    public PrescriptionDetailsDTO getPrescriptionById(PrescriptionId prescriptionId) {


        Prescription prescriptionFromDB= prescriptionRepo.findById(prescriptionId).
                orElseThrow(() -> new ResourceNotFoundException("PrescriptionId not found"));

        PrescriptionDetailsDTO dto= modelMapper.map(prescriptionFromDB, PrescriptionDetailsDTO.class);
//
//        //Need to initialize lazy loaded entities
//        if(prescriptionFromDB.getPatientId() != null){
//            Hibernate.initialize(prescriptionFromDB.getPatientId());
//        }
//
//        if(prescriptionFromDB.getMedicines() != null){
//            Hibernate.initialize(prescriptionFromDB.getMedicines());
//        }

        //Set Patient ID
        dto.setPatientId(prescriptionFromDB.getPatientId().getId());

        //Set Doctor ID
        dto.setDoctorId(prescriptionFromDB.getDoctorId().getId());

        //Set Prescription
        dto.setPrescription(prescriptionFromDB.getPrescription());

        //Set Medicines
        List<Long> medicines = new ArrayList<>();
        for(Medicine obj : prescriptionFromDB.getMedicines()){
            medicines.add(obj.getId());
        }
        dto.setMedicinesIds(medicines);

        return dto;
    }

    @Override
    @Transactional
    //Pending
    public String deletePrescriptionById(PrescriptionId prescriptionId) {
        if(prescriptionRepo.findById(prescriptionId).isEmpty()){
            throw new ResourceNotFoundException("Prescription" + "id: " + prescriptionId);
        }else{
            prescriptionRepo.deleteById(prescriptionId);
        }
        return "Prescription deleted with patientId: " + prescriptionId.getPatientId();
    }

    @Override
    @Transactional
    public PrescriptionDetailsDTO updatePrescription(PrescriptionDetailsDTO prescriptionDetailsDTO) {

        PrescriptionDetailsDTO dto= modelMapper.map(prescriptionDetailsDTO, PrescriptionDetailsDTO.class);

        //Check if prescription exits with ID
        PrescriptionId prescriptionId= new PrescriptionId(prescriptionDetailsDTO.getDoctorId(), prescriptionDetailsDTO.getPatientId());
        Prescription prescriptionFromDB= prescriptionRepo.findById(prescriptionId).
                orElseThrow( () -> new ResourceNotFoundException("Prescription" + "id" + prescriptionId));

        prescriptionFromDB.setPrescription(prescriptionDetailsDTO.getPrescription());
        dto.setPrescription(prescriptionFromDB.getPrescription());

        //Setting Medicines
        prescriptionFromDB.getMedicines().clear();
        List<Medicine> medicines= medicineRepo.findAllById(prescriptionDetailsDTO.getMedicinesIds());
        if(medicines.isEmpty()){
            throw new ResourceNotFoundException("No medicines found with id: " + prescriptionDetailsDTO.getMedicinesIds());
        }
        prescriptionFromDB.setMedicines(medicines);

        List<Long> medicineIds= new ArrayList<>();
        for(Medicine obj : medicines){
            medicineIds.add(obj.getId());
        }
        dto.setMedicinesIds(medicineIds);

        prescriptionRepo.save(prescriptionFromDB);

        return dto;
    }

}
