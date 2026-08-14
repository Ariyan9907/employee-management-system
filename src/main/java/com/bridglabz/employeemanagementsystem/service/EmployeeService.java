package com.bridglabz.employeemanagementsystem.service;

import com.bridglabz.employeemanagementsystem.dto.EmployeeRequestDTO;
import com.bridglabz.employeemanagementsystem.dto.EmployeeResponseDTO;
import com.bridglabz.employeemanagementsystem.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeeService {
    EmployeeResponseDTO addEmployee(EmployeeRequestDTO request);

    EmployeeResponseDTO getEmployeeById(Long id);

    List<EmployeeResponseDTO> getAllEmployees();

    EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO requestDTO);

    String deleteEmployee(Long id);

}
