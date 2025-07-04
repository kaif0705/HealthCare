package com.kaif.healthcare.Service;

import com.kaif.healthcare.Exceptions.APIException;
import com.kaif.healthcare.ManyToMany.Medicine;
import com.kaif.healthcare.ManyToMany.MedicineListDTO;
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
    public MedicineListDTO addMedicine(MedicineListDTO medicineListDTO) {

        Medicine medicine= modelMapper.map(medicineListDTO, Medicine.class);

        if(medicineListDTO ==null){
            throw new APIException("Medicine cannot be null");
        }

        if(medicineRepo.findByName(medicineListDTO.getMedicine()) != null){
            throw new APIException("Medicine already exists with Name: " + medicineListDTO.getMedicine() );
        }

        medicineRepo.save(medicine);

        return modelMapper.map(medicine, MedicineListDTO.class);

    }

    @Override
    @Transactional
    public List<MedicineListDTO> getAllMedicines() {
        List<Medicine> medicines= medicineRepo.findAll();

        if(medicines.isEmpty()){
            throw new APIException("No medicines found");
        }

        List<MedicineListDTO> medicineDetailsDTOS = new ArrayList<>();
        for(Medicine medicine: medicines){
            medicineDetailsDTOS.add(modelMapper.map(medicine, MedicineListDTO.class));
        }

        return medicineDetailsDTOS;
    }

    @Override
    @Transactional
    public MedicineListDTO getMedicine(Long medicineId) {

        //If MedicineId is null
        if(medicineId == null){
            throw new APIException("Medicine id cannot be null");
        }

        //If medicine does not exist
        Medicine medicine= medicineRepo.findById(medicineId).orElse(null);

        if(medicine!=null){
            //Return MedicineDTO
            return modelMapper.map(medicine, MedicineListDTO.class);
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
    public MedicineListDTO updateMedicine(MedicineListDTO medicineListDTO){

        //Check if ID is Null
        if(medicineListDTO.getId() == null && medicineListDTO.getMedicine() == null){
            throw new APIException("Medicine ID or Medicine Name cannot be Null!!!");
        }

        //Check if Medicine exits in DB
        Medicine medicine= medicineRepo.findById(medicineListDTO.getId()).orElse(null);
        if(medicine==null){
            throw new APIException("Medicine not found with ID: " + medicineListDTO.getId());
        }

        if(medicineRepo.findByName(medicineListDTO.getMedicine()) != null){
            throw new APIException("Medicine already exists with Name: " + medicineListDTO.getMedicine() );
        }

        medicine.setMedicine(medicineListDTO.getMedicine());
        medicineRepo.save(medicine);

        return modelMapper.map(medicine, MedicineListDTO.class);
    }
}
