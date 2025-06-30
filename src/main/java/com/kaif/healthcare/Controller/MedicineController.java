package com.kaif.healthcare.Controller;

import com.kaif.healthcare.Payloads.MedicineDTO;
import com.kaif.healthcare.Service.MedicineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    /* Create Medicine */
    @PostMapping("/public/medicines")
    public ResponseEntity<MedicineDTO> addMedicine(@Valid @RequestBody MedicineDTO medicine) {
        MedicineDTO addedMedicine= medicineService.addMedicine(medicine);
        return new ResponseEntity<>(addedMedicine, HttpStatus.CREATED);
    }

    /* Find All Medicine */
    @GetMapping("/public/medicines")
    public ResponseEntity<List<MedicineDTO>> getAllMedicines() {
        List<MedicineDTO> medicines= medicineService.getAllMedicines();
        return new ResponseEntity<>(medicines, HttpStatus.OK);
    }

}
