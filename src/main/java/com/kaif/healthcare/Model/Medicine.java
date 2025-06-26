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

    @ManyToMany(mappedBy= "medicine")
    private List<Prescription> prescription= new ArrayList<>();

    private String medicine;

}
