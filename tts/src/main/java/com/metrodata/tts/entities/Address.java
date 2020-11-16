/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodata.tts.entities;

import lombok.Data;

@Data
public class Address {
    private String city;	
    private String id;
    private String province;	
    private String street1;	
    private String street2;	
    private String zipCode;	

}
