package com.kaif.healthcare.Controller;

import com.kaif.healthcare.Payloads.DoctorDTOs.DoctorDTO;
import com.kaif.healthcare.Service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    /* Create Doctor */
    @PostMapping("/public/doctors")
    public ResponseEntity<DoctorDTO> createDoctor(@Valid @RequestBody DoctorDTO doctorDTO) {
        DoctorDTO createDoctor= doctorService.createDoctor(doctorDTO);
        return new ResponseEntity<>(createDoctor, HttpStatus.CREATED);
    }

    /* Get All Doctors */
    @GetMapping("/public/doctors")
    public ResponseEntity<List<DoctorDTO>> getDoctors() {
        List<DoctorDTO> doctors= doctorService.getDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    /* Get Doctor By ID */
    @GetMapping("/public/doctors/{doctorId}")
    public ResponseEntity<DoctorDTO> getDoctor(@PathVariable Long doctorId) {
        DoctorDTO doctor= doctorService.getDoctorById(doctorId);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    /* Update Doctor */
    @PutMapping("/public/doctors")
    public ResponseEntity<DoctorDTO> updateDoctor(@Valid @RequestBody DoctorDTO doctorDTO) {
        DoctorDTO updateDoctor= doctorService.updateDoctor(doctorDTO);
        return new ResponseEntity<>(updateDoctor, HttpStatus.OK);
    }

    /* Delete Doctor By ID */
    @DeleteMapping("/public/doctors/{doctorId}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long doctorId) {
        String doctorDeleted= doctorService.deleteDoctor(doctorId);
        return new ResponseEntity<>(doctorDeleted, HttpStatus.OK);
    }


}
