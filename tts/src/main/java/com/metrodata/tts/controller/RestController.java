/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.tts.controller;

import com.metrodata.tts.entities.*;
import com.metrodata.tts.entities.rest.*;
import com.metrodata.tts.service.LoginRestService;
import com.metrodata.tts.service.RegisterRestService;
import com.metrodata.tts.service.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RestController {

    String idProfil;
    
    @Autowired
    LoginRestService service;
    
    @Autowired
    RegisterRestService serviceRegister;
    
    @Autowired
    UserRestService serviceUser;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("input", new LoginInput());
        return "redirect:/login";
    }
    
    @GetMapping("register")
    public String register(Model model){
        model.addAttribute("register", new RegisterDataInput());
        return "register";
    }
    
    @PostMapping("register")
    public String registerDaftar(RegisterDataInput input){
        serviceRegister.register(input);
        return "redirect:/";
    }
    
    @GetMapping("login")
    public String login(Model model){
        model.addAttribute("input", new LoginInput());
        return "login";
    }
    
    @PostMapping("login")
    public String login(LoginInput input){
        idProfil = service.GetId(input);
        return "redirect:/dataUser";
    }
    
    @GetMapping("dataUser")
    public String tampil(Model model){
        model.addAttribute("user",serviceUser.getById(idProfil));
        model.addAttribute("address",serviceUser.getAddressById(idProfil));
        model.addAttribute("contact",serviceUser.getContactById(idProfil));
        model.addAttribute("occupation",serviceUser.getOccById(idProfil));
        model.addAttribute("education",serviceUser.getEduById(idProfil));
        return "index";
    }
    
    @PostMapping("save/basic")
    public String saveBasic(User user){
        serviceUser.saveBasic(user);
        return "redirect:/dataUser";
    }
    
    @PostMapping("save/address")
    public String saveAddress(Address address){
        serviceUser.saveAddress(address);
        return "redirect:/dataUser";
    }
    
       @PostMapping("save/contact")
    public String saveContact(Contact contact){
        serviceUser.saveContact(contact);
        return "redirect:/dataUser";
    }
    
    @PostMapping("save/occupation")
    public String saveOccupation(CurrentOccupationData occupation){
        serviceUser.saveOccupation(occupation);
        return "redirect:/dataUser";
    }
    
    @PostMapping("save/education")
    public String saveEducation(EducationData education){
        serviceUser.saveEducation(education);
        return "redirect:/dataUser";
    }
    
    

}
