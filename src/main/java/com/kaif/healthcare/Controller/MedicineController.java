package com.kaif.healthcare.Controller;

import com.kaif.healthcare.ManyToMany.MedicineListDTO;
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
    public ResponseEntity<MedicineListDTO> addMedicine(@Valid @RequestBody MedicineListDTO medicine) {
        MedicineListDTO addedMedicine= medicineService.addMedicine(medicine);
        return new ResponseEntity<>(addedMedicine, HttpStatus.CREATED);
    }

    /* Find All Medicine */
    @GetMapping("/public/medicines")
    public ResponseEntity<List<MedicineListDTO>> getAllMedicines() {
        List<MedicineListDTO> medicines= medicineService.getAllMedicines();
        return new ResponseEntity<>(medicines, HttpStatus.OK);
    }

    /* Get Medicine By ID*/
    @GetMapping("/public/medicines/{medicineId}")
    public ResponseEntity<MedicineListDTO> getMedicineById(@PathVariable Long medicineId) {
        MedicineListDTO medicine= medicineService.getMedicine(medicineId);
        return new ResponseEntity<>(medicine, HttpStatus.OK);
    }

    /* Update Medicine */
    @PutMapping("/public/medicines")
    public ResponseEntity<MedicineListDTO> updateMedicine(@Valid @RequestBody MedicineListDTO medicineListDTO) {
        MedicineListDTO updated= medicineService.updateMedicine(medicineListDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    /* Delete Medicine */
    @DeleteMapping("/public/medicines/{medicineId}")
    public ResponseEntity<String> deleteMedicine(@PathVariable Long medicineId) {
        String delete= medicineService.deleteMedicine(medicineId);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

}
