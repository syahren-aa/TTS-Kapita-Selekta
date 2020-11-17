/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.tts.service;

import com.metrodata.tts.entities.rest.RegisterDataInput;
import com.metrodata.tts.entities.rest.LoginInput;
import com.metrodata.tts.entities.rest.LoginOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RegisterRestService {
    @Autowired
    RestTemplate restTemplate;
    
    @Value("${api.uri}")
    private String uri;
   
       public String register(RegisterDataInput input){
        HttpEntity<RegisterDataInput> request = new HttpEntity<>(input, null);
        ResponseEntity<RegisterDataInput> responseEntity = restTemplate.exchange("http://116.254.101.228:8080/ma_test/register",
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<RegisterDataInput>(){
                }
        );
        return responseEntity.getBody().getStatus();
    }
}
