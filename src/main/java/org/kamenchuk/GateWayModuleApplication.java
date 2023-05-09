package org.kamenchuk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GateWayModuleApplication {
    //TODO Controller private во всех модулях и сделать сдесь контроллеры,
    // которые будут перенаправлять запросы через Feign
    public static void main(String[] args) {
        SpringApplication.run(GateWayModuleApplication.class, args);
    }

}
