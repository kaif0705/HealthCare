package com.kaif.healthcare.Model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PrescriptionId implements Serializable {

    private Long doctorId;
    private Long patientId;

    //Comparing the hashcode of both entities
    @Override
    public int hashCode(){
        return Objects.hash(doctorId, patientId);
    }

    //Comparing
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(!(obj instanceof PrescriptionId)) return false;
        PrescriptionId that= (PrescriptionId)obj;
        return Objects.equals(doctorId, that.doctorId)
                &&
                Objects.equals(patientId, that.patientId);
    }

}
