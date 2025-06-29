package com.kaif.healthcare.Model;

import com.kaif.healthcare.Emuns.Gender;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name= "PERSON_TYPE")
public class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min= 4, max= 10)
    private String name;

    @NotNull
    @Min(0)
    @Max(100)
    private int age;

    @Enumerated(EnumType.STRING)
    @Valid
    private Gender gender;

    @Size(max= 100)
    private String email;

}
