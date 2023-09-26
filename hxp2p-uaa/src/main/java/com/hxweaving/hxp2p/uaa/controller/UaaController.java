package com.hxweaving.hxp2p.uaa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UaaController {

    @GetMapping
    public String test() {
        return "Hello World, UAA!";
    }
}
