package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.exception.EmployeeAlreadyPresentException;
import com.example.demo.exception.EmployeeNotFound;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("OK");
    }

    @RequestMapping(value="/get/{id}",method=RequestMethod.GET)
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) throws EmployeeNotFound {
        EmployeeDTO employee = service.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping(value = "/getByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByName(@PathVariable String name) throws EmployeeNotFound {
        List<EmployeeDTO> employees = service.getEmployeeByName(name);
        return new ResponseEntity<>(employees,HttpStatus.OK);
    }

    @GetMapping("/getByAgeRange")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByAgeRange(
            @RequestParam int age1,
            @RequestParam int age2) throws EmployeeNotFound {
        List<EmployeeDTO> employees = service.getEmployeeBetweenAge(age1, age2);
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeDTO employeeDTO) throws EmployeeAlreadyPresentException {
        String message = service.createNewEmployee(employeeDTO);
        return ResponseEntity.ok(message);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeDTO employeeDTO) throws EmployeeNotFound {
        EmployeeDTO updatedEmployee = service.updateEmployee(id, employeeDTO);
        return ResponseEntity.ok(updatedEmployee);
    }
}
