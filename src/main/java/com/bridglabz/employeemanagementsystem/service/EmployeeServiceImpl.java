package com.bridglabz.employeemanagementsystem.service;

import com.bridglabz.employeemanagementsystem.dto.EmployeeRequestDTO;
import com.bridglabz.employeemanagementsystem.dto.EmployeeResponseDTO;
import com.bridglabz.employeemanagementsystem.exception.IdInvalidException;
import com.bridglabz.employeemanagementsystem.model.Employee;
import com.bridglabz.employeemanagementsystem.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }

    // Adds a new employee to the database.
    @Override
    public EmployeeResponseDTO addEmployee(EmployeeRequestDTO requestDTO) {
        Employee employee = new Employee();
        employee.setName(requestDTO.getName());
        employee.setEmail(requestDTO.getEmail());
        employee.setDepartment(requestDTO.getDepartment());
        employee.setSalary(requestDTO.getSalary());
        employee.setPhone(requestDTO.getPhone());

        return convertToResponseDto(employeeRepository.save(employee));

    }

    // Retrieves an employee by their ID.
    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) throws IdInvalidException {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IdInvalidException("id not found"));
        return convertToResponseDto(employee);
    }

    // Retrieves all employees from the database.
    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(this::convertToResponseDto)
                .toList();
    }

    // Updates an existing employee by their ID.
    @Override
    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO requestDTO) throws IdInvalidException {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IdInvalidException("id not found"));
        employee.setName(requestDTO.getName());
        employee.setEmail(requestDTO.getEmail());
        employee.setDepartment(requestDTO.getDepartment());
        employee.setSalary(requestDTO.getSalary());
        employee.setPhone(requestDTO.getPhone());
        return convertToResponseDto(employeeRepository.save(employee));
    }

    // Deletes an employee by their ID.
    @Override
    public String deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        return "Deleted succefully";

    }

    //converting entity to ResponseDTO
    private EmployeeResponseDTO convertToResponseDto(Employee employee){
        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
        responseDTO.setId(employee.getId());
        responseDTO.setName(employee.getName());
        responseDTO.setEmail(employee.getEmail());
        responseDTO.setDepartment(employee.getDepartment());
        responseDTO.setSalary(employee.getSalary());
        responseDTO.setPhone(employee.getPhone());

        return responseDTO;

    }


}
