package com.example.fullstack41.exceptions;

import com.example.fullstack41.dto.UsersDTO;


public class CustomErrorType extends UsersDTO {
	
    private String errorMessage;
    
    public CustomErrorType(final String errorMessage){
        this.errorMessage = errorMessage;
    }
    
    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
