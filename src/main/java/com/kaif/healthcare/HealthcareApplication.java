package com.kaif.healthcare;

import com.kaif.healthcare.Repositories.PatientRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HealthcareApplication {

//    @Autowired
//    private ModelMapper modelMapper;

    public static void main(String[] args) {
        SpringApplication.run(HealthcareApplication.class, args);
    }

//    @Bean
//    public ModelMapper modelMapper(){
//        return modelMapper;
//    }

}
