package com.promineotech.jeep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.promineotech" })
public class JeepSalesApplication {

    public static void main(String[] args) {
        SpringApplication.run(JeepSalesApplication.class, args);
    }

}
