package com.kaif.healthcare.Model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @NotBlank(message = "Street cannot be blank")
    @Size(max = 100, message = "Street cannot exceed 100 characters")
    private String street;

    @NotBlank(message = "City cannot be blank")
    @Size(max = 50, message = "City cannot exceed 50 characters")
    private String city;

    @NotBlank(message = "Zipcode cannot be blank")
    @Size(max = 10, message = "Zipcode cannot exceed 10 characters")
    private String zipcode;

    @NotBlank(message = "State cannot be blank")
    @Size(max = 20, message = "State cannot exceed 20 characters")
    private String state;

}
