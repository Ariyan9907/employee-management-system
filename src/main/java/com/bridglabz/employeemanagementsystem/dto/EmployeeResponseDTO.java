package com.bridglabz.employeemanagementsystem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class EmployeeResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String department;
    private BigDecimal salary;
    private String phone;
}