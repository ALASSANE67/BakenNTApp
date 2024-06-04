package com.NTApp.demo.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UtilisClasse {
    public  UtilisClasse(){

    }
    public  static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus){
        return  new ResponseEntity<>("{\"message\":\""+responseMessage+"\"}",httpStatus);
     }
}
