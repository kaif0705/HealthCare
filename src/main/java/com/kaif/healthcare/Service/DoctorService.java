package com.kaif.healthcare.Service;

import com.kaif.healthcare.Payloads.DoctorDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface DoctorService {

    DoctorDTO createDoctor(DoctorDTO doctorDTO);
    List<DoctorDTO> getDoctors();
    DoctorDTO getDoctorById(Long doctorId);
    String deleteDoctor(Long doctorId);
    DoctorDTO updateDoctor(DoctorDTO doctorDTO);
}
