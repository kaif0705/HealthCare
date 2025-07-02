package com.kaif.healthcare.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDetailsDTO {

    private Long id;
    private String medicine;

    //Object --> Non-owning side
    private List<PrescriptionDetailsDTO> prescriptionDetailsDTO = new ArrayList<>();

}
