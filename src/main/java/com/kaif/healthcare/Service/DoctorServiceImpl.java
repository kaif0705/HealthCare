package com.kaif.healthcare.Service;

import com.kaif.healthcare.Emuns.Gender;
import com.kaif.healthcare.Exceptions.APIException;
import com.kaif.healthcare.Exceptions.ResourceNotFoundException;
import com.kaif.healthcare.Model.Address;
import com.kaif.healthcare.Model.Doctor;
import com.kaif.healthcare.Payloads.DoctorDTO;
import com.kaif.healthcare.Payloads.PatientDTO;
import com.kaif.healthcare.Repositories.DoctorRepo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private ModelMapper modelMapper;

    /* Create Doctor */
    @Override
    @Transactional
    public DoctorDTO createDoctor(DoctorDTO doctorDTO){

        Doctor doctor = modelMapper.map(doctorDTO, Doctor.class);

        //Check if doctor already exists
        if(doctorRepo.findByEmail(doctorDTO.getEmail()).isPresent()){
            throw new APIException("Doctor already exists");
        }

        doctor.setName(doctorDTO.getName());
        doctor.setAge(doctorDTO.getAge());
        doctor.setGender(doctorDTO.getGender());
        doctor.setEmail(doctorDTO.getEmail());
        doctor.setDoctorAddress(doctorDTO.getDoctorAddress());
        doctor.setSpeciality(doctorDTO.getSpeciality());

        doctorRepo.save(doctor);

        return modelMapper.map(doctor, DoctorDTO.class);

    }

    /* Get All Doctors */
    @Override
    @Transactional
    public List<DoctorDTO> getDoctors() {
        List<Doctor> doctors = doctorRepo.findAll();
        if(doctors.isEmpty()){
            throw new APIException("No doctors found");
        }

        List<DoctorDTO> dtos = new ArrayList<>();
        for(Doctor doctor : doctors){
            DoctorDTO doctorDto= modelMapper.map(doctor, DoctorDTO.class);
            dtos.add(doctorDto);
        }
        return dtos;
    }

    @Override
    @Transactional
    public DoctorDTO getDoctorById(Long doctorId) {
       Doctor doctor= doctorRepo.findById(doctorId).
               orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", doctorId));

       return modelMapper.map(doctor, DoctorDTO.class);
    }

    @Override
    @Transactional
    public String deleteDoctor(Long doctorId) {
        if(doctorRepo.findById(doctorId).isPresent()){
            doctorRepo.deleteById(doctorId);
            return "Doctor deleted with id: " + doctorId;
        }else{
            throw new ResourceNotFoundException("Doctor", "id", doctorId);
        }
    }

    @Override
    @Transactional
    public DoctorDTO updateDoctor(DoctorDTO doctorDTO) {
        //Check if doctor exists
        Doctor doctor= doctorRepo.findById(doctorDTO.getId()).
                orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", doctorDTO.getId()));

        doctor.setName(doctorDTO.getName());
        doctor.setAge(doctorDTO.getAge());
        doctor.setEmail(doctorDTO.getEmail());
        doctor.setDoctorAddress(doctorDTO.getDoctorAddress());
        doctor.setSpeciality(doctorDTO.getSpeciality());

        return modelMapper.map(doctor, DoctorDTO.class);
    }

}
