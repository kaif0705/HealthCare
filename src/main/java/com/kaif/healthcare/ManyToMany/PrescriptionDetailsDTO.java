package com.kaif.healthcare.ManyToMany;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionDetailsDTO {

    // For the composite ID
    private Long doctorId;
    private Long patientId;

    @NotBlank
    @Size(min = 4)
    private String prescription;

    //ID --> owning side
    private List<Long> medicinesIds= new ArrayList<>();

}
