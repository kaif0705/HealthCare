package com.kaif.healthcare.Model;

import jakarta.persistence.*;
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
@Entity
public class Medicine {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String medicine;

    public Medicine(String medicine){
        this.medicine= medicine;
    }

    @ManyToMany(mappedBy= "medicines")
    private List<Prescription> prescription= new ArrayList<>();


}
