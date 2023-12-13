package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.repo.AnalyzesRepo;
import com.example.demo.repo.OnlineRepo;
import com.example.demo.repo.SupportRepo;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class UserController {
    @Autowired
    private UserRepo UserRepo;

    @Autowired
    private AnalyzesRepo AnalyzesRepo;

    @Autowired
    private OnlineRepo OnlineRepo;

    @Autowired
    private SupportRepo SupportRepo;

    @Autowired
    private PasswordEncoder PasswordEncoder;

    @GetMapping("/login")
    public String login() {
        return "auth";
    }

    @GetMapping("/reg")
    public String reg(@RequestParam(name = "error", defaultValue = "", required = false) String error, Model model) {
        if (error.equals("username")) {
            model.addAttribute("error_username", "Данный логин занят!");
        } else if (error.equals("lengthUsername")) {
            model.addAttribute("lengthUsername", "Логин слишком прост!");
        } else if (error.equals("lengthPassword")) {
            model.addAttribute("lengthPassword", "Пароль слишком прост!");
        } else if (error.equals("containsMail")) {
            model.addAttribute("containsMail", "Неверный e-mail!");
        }
        return "reg";
    }

    @PostMapping("/reg")
    public String addUser(@RequestParam String username,
                          @RequestParam String mail,
                          @RequestParam String password) {
        if (UserRepo.findByUsername(username) != null) {
            return "redirect:/reg?error=username";
        } else if (username.length() <= 5) {
            return "redirect:/reg?error=lengthUsername";
        } else if (password.length() <= 5) {
            return "redirect:/reg?error=lengthPassword";
        } else if (!mail.contains("@") && !mail.contains(".")) {
            return "redirect:/reg?error=containsMail";
        }

        password = PasswordEncoder.encode(password);
        User user = new User(username, mail, password, true, Collections.singleton(Role.USER));
        UserRepo.save(user);

        return "redirect:/login";
    }

    @GetMapping("/user")
    public String user(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Iterable<Analyzes> analyzes = AnalyzesRepo.findAllByUsername(username);

        model.addAttribute("value", analyzes);
        return "user";
    }

    @PostMapping("/user")
    public String Role(@RequestParam String login,
                       @RequestParam String role) {

        User User = UserRepo.findByUsername(login);

        String mail = User.getMail();
        String password = User.getPassword();
        UserRepo.delete(User);

        if (role.length() == 6) {
            User user = new User(login, mail, password, true, Collections.singleton(Role.USER));
            UserRepo.save(user);
        } else if (role.length() == 10) {
            User user = new User(login, mail, password, true, Collections.singleton(Role.REDACTOR));
            UserRepo.save(user);
        } else if (role.length() == 7) {
            User user = new User(login, mail, password, true, Collections.singleton(Role.ADMIN));
            UserRepo.save(user);
        } else if (role.length() == 8) {
            User user = new User(login, mail, password, true, Collections.singleton(Role.DOCTOR));
            UserRepo.save(user);
        }


        return "redirect:/user";
    }

    @PostMapping("/user/add")
    public String useradd(@RequestParam String login,
                          @RequestParam String fcs,
                          @RequestParam String title,
                          @RequestParam String date,
                          @RequestParam String result) {

        Analyzes analyzes = new Analyzes(login, fcs, title, date, result);
        AnalyzesRepo.save(analyzes);

        return "redirect:/user";
    }

    @GetMapping("/search")
    public String search(){
        return "search";
    }

    @PostMapping("/search")
    public String searchUser(@RequestParam String username, Model model) {
        Iterable<Analyzes> analyzes = AnalyzesRepo.findAllByUsername(username);
        model.addAttribute("value", analyzes);
        return "search";
    }

    @GetMapping("/user/password")
    public String password(){
        return "password";
    }

    @PostMapping("/user/newPassword")
    public String newPassword(@RequestParam String pass1, @RequestParam String pass2){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = UserRepo.findByUsername(username);

        if (pass1.equals(pass2)) {
            pass2 = PasswordEncoder.encode(pass2);
            user.setPassword(pass2);
            UserRepo.save(user);
        }
        return "redirect:/user";
    }

    @GetMapping("/online")
    public String online(){
        return "online";
    }

    @PostMapping("/online")
    public String mail(@RequestParam String email,
                       @RequestParam String number,
                       @RequestParam String date,
                       @RequestParam String staff,
                       @RequestParam String problem,
                       @RequestParam String names){
        String name = names.split(" ")[0];
        String patronymic = names.split(" ")[1];
        String surname = names.split(" ")[2];

        Online online = new Online(name,patronymic,surname,email,number,date,staff,problem,"Неоплачено");
        OnlineRepo.save(online);

        return "redirect:/";
    }

    @GetMapping("/online/status")
    public String onlineList(Model model){

        Iterable<Online> online = OnlineRepo.findAll();
        model.addAttribute("value", online);

        return "online-status";
    }

    @PostMapping("online/status/{id}")
    public String onlineStatus(@PathVariable(value = "id") long id){
        Online online = OnlineRepo.findById(id).orElse(new Online());
        OnlineRepo.delete(online);
        return "redirect:/online/status";
    }

    @GetMapping("/support")
    public String support(){
        return "support";
    }

    @PostMapping("/support")
    public String postSupport(@RequestParam String text,
                              @RequestParam String date){

        Support support = new Support(text,date);
        SupportRepo.save(support);

        return "redirect:/";
    }

    @GetMapping("/support/status")
    public String supportStatus(Model model){

        Iterable<Support> support = SupportRepo.findAll();
        model.addAttribute("value", support);

        return "support-status";
    }


}
