package com.kaif.healthcare.Service;

import com.kaif.healthcare.Exceptions.APIException;
import com.kaif.healthcare.ManyToMany.Medicine;
import com.kaif.healthcare.ManyToMany.MedicineDetailsDTO;
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
    public MedicineDetailsDTO addMedicine(MedicineDetailsDTO medicineDetailsDTO) {

        Medicine medicine= modelMapper.map(medicineDetailsDTO, Medicine.class);

        if(medicineDetailsDTO ==null){
            throw new APIException("Medicine cannot be null");
        }

        if(medicineRepo.findByName(medicineDetailsDTO.getMedicine()) != null){
            throw new APIException("Medicine already exists with Name: " + medicineDetailsDTO.getMedicine() );
        }

        medicine.setMedicine(medicineDetailsDTO.getMedicine());

        medicineRepo.save(medicine);

        return modelMapper.map(medicine, MedicineDetailsDTO.class);

    }

    @Override
    @Transactional
    public List<MedicineDetailsDTO> getAllMedicines() {
        List<Medicine> medicines= medicineRepo.findAll();

        if(medicines.isEmpty()){
            throw new APIException("No medicines found");
        }

        List<MedicineDetailsDTO> medicineDetailsDTOS = new ArrayList<>();
        for(Medicine medicine: medicines){
            medicineDetailsDTOS.add(modelMapper.map(medicine, MedicineDetailsDTO.class));
        }

        return medicineDetailsDTOS;
    }

    @Override
    @Transactional
    public MedicineDetailsDTO getMedicine(Long medicineId) {

        //If MedicineId is null
        if(medicineId == null){
            throw new APIException("Medicine id cannot be null");
        }

        //If medicine does not exist
        Medicine medicine= medicineRepo.findById(medicineId).orElse(null);
        if(medicine!=null){
            //Return MedicineDTO
            MedicineDetailsDTO medicineDetailsDTO = new MedicineDetailsDTO();
            medicineDetailsDTO.setId(medicineId);
            medicineDetailsDTO.setMedicine(medicine.getMedicine());

            return medicineDetailsDTO;
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
    public MedicineDetailsDTO updateMedicine(MedicineDetailsDTO medicineDetailsDTO){

        //Check if ID is Null
        if(medicineDetailsDTO ==null){
            throw new APIException("Medicine cannot be null");
        }

        //Check if Medicine exits in DB
        Medicine medicine= medicineRepo.findById(medicineDetailsDTO.getId()).orElse(null);
        if(medicine==null){
            throw new APIException("Medicine not found with ID: " + medicineDetailsDTO.getId());
        }

        if(medicineRepo.findByName(medicineDetailsDTO.getMedicine()) != null){
            throw new APIException("Medicine already exists with Name: " + medicineDetailsDTO.getMedicine() );
        }

        medicine.setMedicine(medicineDetailsDTO.getMedicine());
        medicineRepo.save(medicine);

        return modelMapper.map(medicine, MedicineDetailsDTO.class);
    }
}
