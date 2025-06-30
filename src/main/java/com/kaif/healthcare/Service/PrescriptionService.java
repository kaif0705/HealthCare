package com.kaif.healthcare.Service;

import com.kaif.healthcare.Model.PrescriptionId;
import com.kaif.healthcare.Payloads.PrescriptionDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface PrescriptionService {
    PrescriptionDTO createPrescription(PrescriptionDTO prescription);
    List<PrescriptionDTO> getAllPrescriptions();
    PrescriptionDTO getPrescriptionById(PrescriptionId prescriptionId);
    String deletePrescriptionById(@Valid PrescriptionId prescriptionId);
    PrescriptionDTO updatePrescription(PrescriptionDTO prescriptionDTO);
}
