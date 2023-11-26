package com.example.demo.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeveloperController {
    @GetMapping("/developer")
    public String developer(){
    return "developer";
    }
}
