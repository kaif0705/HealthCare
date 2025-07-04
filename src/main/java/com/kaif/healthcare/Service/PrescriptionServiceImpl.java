package com.kaif.healthcare.Service;

import com.kaif.healthcare.Exceptions.ResourceNotFoundException;
import com.kaif.healthcare.ManyToMany.Medicine;
import com.kaif.healthcare.ManyToMany.Prescription;
import com.kaif.healthcare.Model.*;
import com.kaif.healthcare.ManyToMany.PrescriptionDetailsDTO;
import com.kaif.healthcare.Repositories.DoctorRepo;
import com.kaif.healthcare.Repositories.MedicineRepo;
import com.kaif.healthcare.Repositories.PatientRepo;
import com.kaif.healthcare.Repositories.PrescriptionRepo;
import jakarta.transaction.Transactional;
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

        Prescription prescription = modelMapper.map(prescriptionDetailsDTO, Prescription.class);

        //1. Check if Patient and Doctor ID is null
        Patient patient= patientRepo.findById(prescriptionDetailsDTO.getPatientId()).orElse(null);
        Doctor doctor= doctorRepo.findById(prescriptionDetailsDTO.getDoctorId()).orElse(null);
        if(patient == null && doctor == null){
            throw new ResourceNotFoundException("Please enter a valid patient id or a valid doctor id!!!");
        }

        //2. Create Composite Keys
        PrescriptionId compositeKey= new PrescriptionId(prescriptionDetailsDTO.getPatientId(), prescriptionDetailsDTO.getDoctorId());
        prescription.setId(compositeKey);

        //3. Add Prescription
        prescription.setPrescription(prescriptionDetailsDTO.getPrescription());

        //4. Add Medicines
        List<Medicine> medicines = new ArrayList<>();
        List<Long> medicinesId= new ArrayList<>();
        for(Long Id : prescriptionDetailsDTO.getMedicinesIds()){
            Medicine medicine= medicineRepo.findById(Id).orElseThrow(
                    () -> new ResourceNotFoundException("Medicine not found with id: " + Id)
            );
            medicinesId.add(medicine.getId());
            medicines.add(medicine);
        }
        prescription.setMedicines(medicines);


        //Save Prescription
        prescriptionRepo.save(prescription);

        PrescriptionDetailsDTO dto= modelMapper.map(prescription, PrescriptionDetailsDTO.class);
        dto.setMedicinesIds(medicinesId);
        //Set patientId and doctorId
        dto.setPatientId(patient.getId());
        dto.setDoctorId(doctor.getId());

        return dto;

    }

    /* Get All Prescriptions */
    @Override
    @Transactional
    public List<PrescriptionDetailsDTO> getAllPrescriptions() {

        List<Prescription> prescriptions = prescriptionRepo.findAll();

        List<PrescriptionDetailsDTO> prescriptionDetailsDTO = new ArrayList<>();
        if(!prescriptions.isEmpty()){
            for(Prescription prescription : prescriptions){
                PrescriptionDetailsDTO dto = modelMapper.map(prescription, PrescriptionDetailsDTO.class);

                for(Medicine medicine : prescription.getMedicines()){
                    dto.getMedicinesIds().add(medicine.getId());
                }
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

        if(prescriptionFromDB == null){
            throw new ResourceNotFoundException("Prescription" + "id" + prescriptionId);
        }

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
