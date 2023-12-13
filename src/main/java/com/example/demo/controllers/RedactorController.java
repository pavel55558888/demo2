package com.example.demo.controllers;

import com.example.demo.models.redactor.Cardiologist;
import com.example.demo.models.redactor.Therapist;
import com.example.demo.models.redactor.Traumatologist;
import com.example.demo.models.redactor.Ultrasound;
import com.example.demo.repo.redactor.CardiologistRepo;
import com.example.demo.repo.redactor.TherapistRepo;
import com.example.demo.repo.redactor.TraumotologistRepo;
import com.example.demo.repo.redactor.UltrasoundRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class RedactorController {
    @Autowired
    TraumotologistRepo traumotologistRepo;
    @Autowired
    CardiologistRepo cardiologistRepo;
    @Autowired
    TherapistRepo therapistRepo;
    @Autowired
    UltrasoundRepo ultrasoundRepo;

    @GetMapping("/traumatologist/add")
    public String  traumatologist(Model model){
        List<Traumatologist> list = traumotologistRepo.findAll();
        Collections.reverse(list);

        model.addAttribute("list", list);

        return "traumatologist";
    }
    @PostMapping("/traumatologist/add/db")
    public String traumotologistAdd(@RequestParam String fio,
                                    @RequestParam String tel,
                                    @RequestParam String login,
                                    @RequestParam String date){
        Traumatologist traumatologist = new Traumatologist(fio,tel,login,date);
        traumotologistRepo.save(traumatologist);

        return "redirect:/user";
    }

    @GetMapping("/cardiologist/add")
    public String cardiologist(Model model){
        List<Cardiologist> list = cardiologistRepo.findAll();
        Collections.reverse(list);

        model.addAttribute("list", list);

        return "cardiologist";
    }

    @PostMapping("/cardiologist/add/db")
    public String cardiologistAdd(@RequestParam String fio,
                                  @RequestParam String tel,
                                  @RequestParam String login,
                                  @RequestParam String date){
        Cardiologist cardiologist = new Cardiologist(fio,tel,login,date);
        cardiologistRepo.save(cardiologist);

        return "redirect:/user";
    }

    @GetMapping("/therapist/add")
    public String therapist(Model model){
        List<Therapist> list = therapistRepo.findAll();
        Collections.reverse(list);

        model.addAttribute("list", list);

        return "therapist";
    }

    @PostMapping("/therapist/add/db")
    public String therapistAdd(@RequestParam String fio,
                               @RequestParam String tel,
                               @RequestParam String login,
                               @RequestParam String date){
        Therapist therapist = new Therapist(fio,tel,login,date);
        therapistRepo.save(therapist);

        return "redirect:/user";
    }

    @GetMapping("/ultrasound/add")
    public String ultrasound(Model model){
        List<Ultrasound> list = ultrasoundRepo.findAll();
        Collections.reverse(list);

        model.addAttribute("list", list);

        return "ultrasound";
    }

    @PostMapping("/ultrasound/add/db")
    public String ultrasoundAdd(@RequestParam String fio,
                                @RequestParam String tel,
                                @RequestParam String login,
                                @RequestParam String uzi,
                                @RequestParam String date){
        Ultrasound ultrasound = new Ultrasound(fio,tel,login,uzi,date);
        ultrasoundRepo.save(ultrasound);

        return "redirect:/user";
    }
}
