package org.kamenchuk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RentServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(RentServiceApp.class,args);
    }
}
