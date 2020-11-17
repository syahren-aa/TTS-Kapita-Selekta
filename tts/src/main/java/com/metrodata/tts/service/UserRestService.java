/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.tts.service;

import com.metrodata.tts.entities.*;
import com.metrodata.tts.entities.rest.LoginOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRestService {

    @Autowired
    RestTemplate restTemplate;
    
    @Value("${api.uri}")
    private String uri;
    
    public User getById(String id){
        User result;
        Map<String, String> param = new HashMap<>();
        param.put("id", id);
        result = restTemplate.getForObject(uri+"/profile/basic/{id}", User.class, param);
        return result;
    }
    
    public EducationData getEduById(String id){
        EducationData result;
        Map<String, String> param = new HashMap<>();
        param.put("id", id);
        result = restTemplate.getForObject(uri+"/profile/education/{id}", EducationData.class, param);
        return result;
    }
    
    public CurrentOccupationData getOccById(String id){
        CurrentOccupationData result;
        Map<String, String> param = new HashMap<>();
        param.put("id", id);
        result = restTemplate.getForObject(uri+"/profile/currentoccupation/{id}", CurrentOccupationData.class, param);
        return result;
    }
    
    public List<University> getUniversity(){
        List<University> result;
        ResponseEntity<List<University>> response = restTemplate.exchange(
                uri+"/get/universities",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<University>>(){});
        result = response.getBody();
        return result;
    }
    
    
    public Contact getContactById(String id){
        Contact result;
        Map<String, String> param = new HashMap<>();
        param.put("id", id);
        result = restTemplate.getForObject(uri+"/profile/contact/{id}", Contact.class, param);
        return result;
    }
    
    public Address getAddressById(String id){
        Address result;
        Map<String, String> param = new HashMap<>();
        param.put("id", id);
        result = restTemplate.getForObject(uri+"/profile/address/{id}", Address.class, param);
        return result;
    }
    
    public boolean saveBasic(User user){
        try {
            restTemplate.postForObject(uri+"/profile/basic", user, User.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean saveAddress(Address address){
        try{
        restTemplate.postForObject(uri+"/profile/address", address, Address.class);
        return true;
        }catch(Exception e){
            return false;
        }
        }
    
    public boolean saveEducation(EducationData educationData){
        try{
        restTemplate.postForObject(uri+"/profile/education", educationData, EducationData.class);    
        return true;
        }catch(Exception e){
        return false;
    }
    }
    
    public boolean saveOccupation(CurrentOccupationData currentOccupation){
        try{
            restTemplate.postForObject(uri+"/profile/currentoccupation", currentOccupation, CurrentOccupationData.class);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public boolean saveContact(Contact contact){
        try{
            restTemplate.postForObject(uri+"/profile/contact", contact, Contact.class);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public String getUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginOutput data = (LoginOutput)authentication.getPrincipal();
        return data.getUser().getId();
    }

}
