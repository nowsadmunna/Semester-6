package org.example.microloanservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MicroLoanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroLoanServiceApplication.class, args);
    }

}
