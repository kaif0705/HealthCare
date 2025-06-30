package com.kaif.healthcare.Payloads;

import com.kaif.healthcare.Model.Prescription;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDTO {

    private Long id;
    private String medicine;
    private List<PrescriptionDTO> prescriptionDTO= new ArrayList<>();

}
