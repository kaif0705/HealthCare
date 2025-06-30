package com.kaif.healthcare.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "medicine")
})
public class Medicine {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String medicine;

    public Medicine(String medicine){
        this.medicine= medicine;
    }

    @ManyToMany(mappedBy= "medicines", cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private List<Prescription> prescription= new ArrayList<>();


}
