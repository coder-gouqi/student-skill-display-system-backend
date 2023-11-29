package com.cuit.studentskilldisplaysystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.cuit.studentskilldisplaysystem.mapper")
@SpringBootApplication
public class StudentSkillDisplaySystemBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentSkillDisplaySystemBackendApplication.class, args);
    }

}
