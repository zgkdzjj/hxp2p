package com.hxweaving.hxp2p;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
@EnableDiscoveryClient
public class UaaService {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(UaaService.class, args);
        System.out.println("abc");
        // run.getBean(DefaultSecurityFilterChain.class)

        //UsernamePasswordAuthenticationFilter
    }
}