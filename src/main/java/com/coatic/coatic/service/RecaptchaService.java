package com.coatic.coatic.service;

import com.coatic.coatic.clases.RecaptchaUtil;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RecaptchaService {


  //Private key of captcha
    private static final String RECAPTCHA_SECRET = "";
    private static final String RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    public String verifyRecaptcha(String ip, String recaptchaResponse){

        Map<String, String> body = new HashMap<>();

        body.put("secret", RECAPTCHA_SECRET);
        body.put("response", recaptchaResponse);
        body.put("remoteip", ip);

        ResponseEntity<Map> recaptchaResponseEntity = 
        restTemplateBuilder.build().postForEntity(RECAPTCHA_VERIFY_URL +"?secret={secret}&response={response}&remoteip={remoteip}",body, Map.class, body);

        Map<String, Object> responseBody = recaptchaResponseEntity.getBody();
        
        boolean recaptchaSuccess = (Boolean) responseBody.get("success");

        if (! recaptchaSuccess) {
            List<String> errorCodes = (List) responseBody.get("error-codes");
        
            String errorMessage = errorCodes.stream().map(s -> RecaptchaUtil.RECAPTCHA_ERROR_CODE.get(s)).collect(Collectors.joining(", "));
            
            return errorMessage;
        } else {
            return "";
        }

    }

}
