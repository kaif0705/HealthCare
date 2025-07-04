package com.kaif.healthcare.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicine medicine1 = (Medicine) o;
        // Compare by ID, as ID is the unique identifier for persisted entities
        return Objects.equals(id, medicine1.id);
    }

    @Override
    public int hashCode() {
        // Hash by ID
        return Objects.hash(id);
    }


}
