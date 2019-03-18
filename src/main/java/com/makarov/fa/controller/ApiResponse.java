package com.makarov.fa.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.makarov.fa.resourses.Resource;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Data
public class ApiResponse {

    @JsonIgnore
    private HttpStatus statusCode;

    private String message;

    private List<? extends Resource> content;

    public ApiResponse(HttpStatus statusCode, String message, List<? extends Resource> content) {
        this.statusCode = statusCode;
        this.message = message;
        this.content = content;
    }

    public ApiResponse(HttpStatus statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public <T extends Resource> ApiResponse(HttpStatus statusCode, String message, T content) {
        this(statusCode, message, Collections.singletonList(content));
    }

    @JsonProperty("statusCode")
    public int getStatusCodeNumber() {
        return statusCode.value();
    }
}
