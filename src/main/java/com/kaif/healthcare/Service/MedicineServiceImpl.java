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

        if(medicineRepo.findByName(medicineDTO.getMedicine()) != null){
            throw new APIException("Medicine already exists with Name: " + medicineDTO.getMedicine() );
        }

        medicine.setMedicine(medicineDTO.getMedicine());

        medicineRepo.save(medicine);

        return modelMapper.map(medicine, MedicineDTO.class);

    }
}
