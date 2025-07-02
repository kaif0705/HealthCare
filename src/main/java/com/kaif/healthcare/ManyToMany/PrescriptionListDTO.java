package com.kaif.healthcare.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionListDTO {

    private Long doctorId;
    private Long patientId;

    private String prescription;

}
