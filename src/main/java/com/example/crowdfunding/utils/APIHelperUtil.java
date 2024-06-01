package com.example.crowdfunding.utils;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.example.crowdfunding.utils.Constants.FAILED;
import static com.example.crowdfunding.utils.Constants.SUCCESS;

public class APIHelperUtil {

    private APIHelperUtil(){}

    private static APIResponse getApiResponseInstance(Object body, HttpStatus status) {
        APIResponse response = new APIResponse();
        response.setStatus(status.is2xxSuccessful()?SUCCESS:FAILED);
        response.setCode(status.value());
        response.setResult(body);
        return response;
    }

    private static APIResponse getApiResponseInstance(HttpStatus status) {
        APIResponse response = new APIResponse();
        response.setStatus(status.is2xxSuccessful()?SUCCESS:FAILED);
        response.setCode(status.value());
        return response;
    }

    public static ResponseEntity<APIResponse> createUnifiedResponse (Object body, HttpStatus status){
        APIResponse response = getApiResponseInstance(body, status);
        return new ResponseEntity<>(response,status);
    }


    public static ResponseEntity<APIResponse> createUnifiedResponse (Object body, HttpStatus status, String message){
        APIResponse response = getApiResponseInstance(body, status);
        response.setMessage(message);
        return new ResponseEntity<>(response,status);
    }

    public static ResponseEntity<APIResponse> createUnifiedResponse (Object body, HttpStatus status, String message, List<String> errors){
        APIResponse response = getApiResponseInstance(body, status);
        response.setMessage(message);
        response.setErrors(errors);
        return new ResponseEntity<>(response,status);
    }

    public static ResponseEntity<APIResponse> createUnifiedResponse (HttpStatus status, List<String> errors){
        APIResponse response = getApiResponseInstance(status);
        response.setErrors(errors);
        return new ResponseEntity<>(response,status);
    }

    public static ResponseEntity<APIResponse> createUnifiedResponse (HttpStatus status, String message){
        APIResponse response = getApiResponseInstance(status);
        response.setMessage(message);
        return new ResponseEntity<>(response,status);
    }
}