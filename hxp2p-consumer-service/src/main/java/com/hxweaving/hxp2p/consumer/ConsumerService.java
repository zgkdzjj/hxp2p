package com.hxweaving.hxp2p.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.hxweaving.hxp2p.consumer.agent"})
public class ConsumerService {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerService.class, args);
    }

}
