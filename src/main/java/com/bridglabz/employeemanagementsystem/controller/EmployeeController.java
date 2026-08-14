package com.bridglabz.employeemanagementsystem.controller;

import com.bridglabz.employeemanagementsystem.dto.EmployeeRequestDTO;
import com.bridglabz.employeemanagementsystem.dto.EmployeeResponseDTO;
import com.bridglabz.employeemanagementsystem.exception.IdInvalidException;
import com.bridglabz.employeemanagementsystem.model.Employee;
import com.bridglabz.employeemanagementsystem.service.EmployeeService;
import com.bridglabz.employeemanagementsystem.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // Creates a new employee.
    @PostMapping
    public EmployeeResponseDTO saveEmployee(@RequestBody EmployeeRequestDTO requestDTO){
        return employeeService.addEmployee(requestDTO);
    }

    // Retrieves all employees.
    @GetMapping
    public List<EmployeeResponseDTO> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    // Retrieves an employee by their ID.
    @GetMapping("/{id}")
    public EmployeeResponseDTO getEmployeeById(@PathVariable("id") Long id) throws IdInvalidException {
        return (employeeService.getEmployeeById(id));
    }

    // Updates an existing employee by their ID.
    @PutMapping("/{id}")
    public EmployeeResponseDTO updateEmployee(@PathVariable("id") Long id,@RequestBody EmployeeRequestDTO requestDTO) throws IdInvalidException {
        return (employeeService.updateEmployee(id, requestDTO));
    }

    // Deletes an employee by their ID.
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable("id") Long id){
        return (employeeService.deleteEmployee(id));
    }



}
