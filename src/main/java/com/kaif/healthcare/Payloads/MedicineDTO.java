package com.kaif.healthcare.Payloads;

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
