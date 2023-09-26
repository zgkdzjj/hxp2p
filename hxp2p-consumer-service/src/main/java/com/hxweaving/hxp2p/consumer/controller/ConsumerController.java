package com.hxweaving.hxp2p.consumer.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.hxweaving.hxp2p.api.consumer.ConsumerAPI;
import com.hxweaving.hxp2p.api.consumer.model.ConsumerDTO;
import com.hxweaving.hxp2p.api.consumer.model.ConsumerRegisterDTO;
import com.hxweaving.hxp2p.common.domain.RestResponse;
import com.hxweaving.hxp2p.consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConsumerController implements ConsumerAPI {

    @Autowired
    ConsumerService consumerService;

    @Override
    @PostMapping()
    public RestResponse<ConsumerDTO> register(@RequestBody ConsumerRegisterDTO consumerRegisterDTO) {
        System.out.println("in register");
        consumerService.register(consumerRegisterDTO);

        return RestResponse.success();
    }
    @GetMapping("/test")
    public String test(){
        return "Hello World";
    }



}
