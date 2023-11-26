package com.example.demo.controllers;

import com.example.demo.models.Staff;
import com.example.demo.repo.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private StaffRepo StaffRepo;

    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/services")
    public String services(Model model){
        Iterable<Staff> Staff = StaffRepo.findAll();
        model.addAttribute("Staff",Staff);
        return "services";
    }

    @GetMapping("/auth")
    public String auth(){
        return "auth";
    }

}
