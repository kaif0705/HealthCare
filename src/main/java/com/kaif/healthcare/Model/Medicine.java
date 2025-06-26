package com.kaif.healthcare.Model;

import jakarta.persistence.*;
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


    public Medicine(String medicine){
        this.medicine= medicine;
    }

    @ManyToMany(cascade= CascadeType.PERSIST)
    @JoinTable(
            name= "medicine_prescription",
            joinColumns= @JoinColumn(name= "medicine_id"),
            inverseJoinColumns= @JoinColumn(name= "prescription_id")
    )
    private List<Prescription> prescription= new ArrayList<>();

    private String medicine;

}
