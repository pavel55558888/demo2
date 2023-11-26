package com.example.demo.controllers;

import com.example.demo.models.Staff;
import com.example.demo.repo.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StaffController {

    @Autowired
    private StaffRepo StaffRepo;

    @GetMapping("/staff/add")
    public String add(){
        return "add-staff";
    }

    @PostMapping("/staff/add")
        public String store(@RequestParam String names,
                            @RequestParam String post,
                            @RequestParam String experience,
                            @RequestParam String img,
                            @RequestParam String fullInformation){

        String name = names.split(" ")[0];
        String patronymic = names.split(" ")[1];
        String surname = names.split(" ")[2];

        Staff staff = new Staff(name,patronymic,surname,post,experience,img,fullInformation);
        StaffRepo.save(staff);
        return "redirect:/services";
    }

    @GetMapping("/staff/{id}")
    public String idStaff(@PathVariable(value = "id") long id, Model model){
        Staff Staff = StaffRepo.findById(id).orElse(new Staff());
        model.addAttribute("Staff",Staff);
        return "idStaff";
    }

    @GetMapping("/staff/{id}/update")
    public String updateStaff(@PathVariable(value = "id") long id, Model model){
        Staff Staff = StaffRepo.findById(id).orElse(new Staff());
        model.addAttribute("Staff",Staff);


        return "staff-update";
    }

    @PostMapping("/staff/{id}/update")
    public String update(@PathVariable(value = "id") long id,
                         @RequestParam String name,
                         @RequestParam String post,
                         @RequestParam String experience,
                         @RequestParam String img,
                         @RequestParam String fullInformation){
        Staff Staff = StaffRepo.findById(id).orElse(new Staff());

        Staff.setName(name);
        Staff.setPost(post);
        Staff.setExperience(experience);
        Staff.setImg(img);
        Staff.setFullInformation(fullInformation);
        StaffRepo.save(Staff);
        return "redirect:/staff/" + id;
    }

    @PostMapping("/staff/{id}/delete")
    public String staffDelete(@PathVariable(value = "id") long id){
        Staff Staff = StaffRepo.findById(id).orElse(new Staff());
        StaffRepo.delete(Staff);
        return "redirect:/services";
    }
}
