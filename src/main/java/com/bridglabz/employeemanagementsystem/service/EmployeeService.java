package com.bridglabz.employeemanagementsystem.service;

import com.bridglabz.employeemanagementsystem.dto.EmployeeRequestDTO;
import com.bridglabz.employeemanagementsystem.dto.EmployeeResponseDTO;
import com.bridglabz.employeemanagementsystem.exception.IdInvalidException;
import com.bridglabz.employeemanagementsystem.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeeService {
    EmployeeResponseDTO addEmployee(EmployeeRequestDTO request);

    EmployeeResponseDTO getEmployeeById(Long id) throws IdInvalidException;

    List<EmployeeResponseDTO> getAllEmployees();

    EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO requestDTO) throws IdInvalidException;

    String deleteEmployee(Long id);

}
