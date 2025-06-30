package com.kaif.healthcare.Payloads;

import com.kaif.healthcare.Model.Medicine;
import com.kaif.healthcare.Model.Patient;
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
public class PrescriptionDTO {

    // For the composite ID
    private Long doctorId;
    private Long patientId;

    @NotBlank
    @Size(min = 4)
    private String prescription;

    private PatientDTO patientDTO;
    private List<MedicineDTO> medicinesDTO= new ArrayList<>();

}
