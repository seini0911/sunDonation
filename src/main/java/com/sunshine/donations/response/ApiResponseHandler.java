package com.sunshine.donations.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ApiResponseHandler {
    public static ResponseEntity<Object> buildResponse(String message, HttpStatus statusCode, Object data){
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("httpStatus", statusCode);
        response.put("data", data);
        return ResponseEntity.status(statusCode)
                .body(response);
    }
}
