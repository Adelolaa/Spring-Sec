package com.Tosin.Spring.Sec.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping
    public String sayHello(){
        return "Welcome to our class";


    }
//    @PreAuthorize("hasAnyRole('Admin','ceo')")
    @GetMapping("/calculate")
    public String calculate(){
        return String.valueOf(2 + 2);
    }
}
