package com.cuit.studentskilldisplaysystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class StudentSkillDisplaySystemBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentSkillDisplaySystemBackendApplication.class, args);
    }

}
