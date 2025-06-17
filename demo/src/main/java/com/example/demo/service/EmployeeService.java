package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.exception.EmployeeNotFound;
import com.example.demo.exception.EmployeeAlreadyPresentException;

public interface EmployeeService {

    EmployeeDTO getEmployeeById(Long id) throws EmployeeNotFound;

    List<EmployeeDTO> getEmployeeByName(String name) throws EmployeeNotFound;

    List<EmployeeDTO> getEmployeeBetweenAge(int age1, int age2) throws EmployeeNotFound;

    String createNewEmployee(EmployeeDTO emp) throws EmployeeAlreadyPresentException;

    EmployeeDTO updateEmployee(Long id, EmployeeDTO updatedEmployee) throws EmployeeNotFound;
}
