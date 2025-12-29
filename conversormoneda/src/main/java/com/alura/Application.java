package com.alura;

import com.alura.service.ExchangeRateService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        ExchangeRateService service = context.getBean(ExchangeRateService.class);
        service.loadUSDRates();
        System.out.println("Tipos de cambio cargados exitosamente");
    }
}
