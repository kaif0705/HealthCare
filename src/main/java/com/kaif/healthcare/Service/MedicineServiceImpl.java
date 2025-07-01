package com.kaif.healthcare.Service;

import com.kaif.healthcare.Exceptions.APIException;
import com.kaif.healthcare.Model.Medicine;
import com.kaif.healthcare.Model.Prescription;
import com.kaif.healthcare.Payloads.MedicineDTO;
import com.kaif.healthcare.Payloads.PrescriptionDTO;
import com.kaif.healthcare.Repositories.MedicineRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepo medicineRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public MedicineDTO addMedicine(MedicineDTO medicineDTO) {

        Medicine medicine= modelMapper.map(medicineDTO, Medicine.class);

        if(medicineDTO==null){
            throw new APIException("Medicine cannot be null");
        }

        if(medicineRepo.findByName(medicineDTO.getMedicine()) != null){
            throw new APIException("Medicine already exists with Name: " + medicineDTO.getMedicine() );
        }

        medicine.setMedicine(medicineDTO.getMedicine());

        medicineRepo.save(medicine);

        return modelMapper.map(medicine, MedicineDTO.class);

    }

    @Override
    @Transactional
    public List<MedicineDTO> getAllMedicines() {
        List<Medicine> medicines= medicineRepo.findAll();

        if(medicines.isEmpty()){
            throw new APIException("No medicines found");
        }

        List<MedicineDTO> medicineDTOs= new ArrayList<>();
        for(Medicine medicine: medicines){
            medicineDTOs.add(modelMapper.map(medicine, MedicineDTO.class));
        }

        return medicineDTOs;
    }

    @Override
    @Transactional
    public MedicineDTO getMedicine(Long medicineId) {

        //If MedicineId is null
        if(medicineId == null){
            throw new APIException("Medicine id cannot be null");
        }

        //If medicine does not exist
        Medicine medicine= medicineRepo.findById(medicineId).orElse(null);
        if(medicine!=null){
            //Return MedicineDTO
            MedicineDTO medicineDTO= new MedicineDTO();
            medicineDTO.setId(medicineId);
            medicineDTO.setMedicine(medicine.getMedicine());

            return medicineDTO;
        }else{
            throw new APIException("Medicine not found with ID: " + medicineId);
        }
    }

    @Override
    @Transactional
    public String deleteMedicine(Long medicineId){

        //Check if ID is Null
        if(medicineId == null){
            throw new APIException("Medicine id cannot be null");
        }

        //Check if Medicine exits in DB
        Medicine medicine= medicineRepo.findById(medicineId).orElse(null);
        if(medicine!=null){
            medicineRepo.delete(medicine);
        }else{
            throw new APIException("Medicine not found with ID: " + medicineId);
        }

        return "Medicine deleted with ID: " + medicineId;

    }

    @Override
    @Transactional
    public MedicineDTO updateMedicine(MedicineDTO medicineDTO){

        //Check if ID is Null
        if(medicineDTO==null){
            throw new APIException("Medicine cannot be null");
        }

        //Check if Medicine exits in DB
        Medicine medicine= medicineRepo.findById(medicineDTO.getId()).orElse(null);
        if(medicine==null){
            throw new APIException("Medicine not found with ID: " + medicineDTO.getId());
        }

        if(medicineRepo.findByName(medicineDTO.getMedicine()) != null){
            throw new APIException("Medicine already exists with Name: " + medicineDTO.getMedicine() );
        }

        medicine.setMedicine(medicineDTO.getMedicine());
        medicineRepo.save(medicine);

        return modelMapper.map(medicine, MedicineDTO.class);
    }
}
