package com.sriram.Resum8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Resum8Application
{

    public static void main(String[] args)
    {
        SpringApplication.run(Resum8Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate()
    {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

}
