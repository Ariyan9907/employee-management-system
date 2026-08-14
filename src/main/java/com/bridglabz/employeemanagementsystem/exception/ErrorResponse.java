package com.bridglabz.employeemanagementsystem.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timstamp;

    public ErrorResponse(int status,String message){
        this.status=status;
        this.message=message;
        this.timstamp= LocalDateTime.now();
    }

}
