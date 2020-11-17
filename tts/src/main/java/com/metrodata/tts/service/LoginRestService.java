/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.tts.service;

import com.metrodata.tts.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.metrodata.tts.entities.rest.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

@Service
public class LoginRestService {
    @Autowired
    RestTemplate restTemplate;
    
    @Value("${api.uri}")
    private String uri;
   
    int kode;
    public LoginOutput login(LoginInput input){
        HttpEntity<LoginInput> request = new HttpEntity<>(input, null);
        ResponseEntity<LoginOutput> responseEntity = restTemplate.exchange("http://116.254.101.228:8080/ma_test/login",
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<LoginOutput>(){
                }
        );
        kode=responseEntity.getStatusCodeValue();
        return responseEntity.getBody();
    }

    public String GetId(LoginInput input){
        return login(input).getUser().getId();
    }
    
    public String Status(LoginInput input){
        return login(input).getStatus();
    }
   
}
