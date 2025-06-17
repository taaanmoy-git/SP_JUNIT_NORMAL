package com.example.demo.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.exception.EmployeeAlreadyPresentException;
import com.example.demo.exception.EmployeeNotFound;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    public EmployeeRepository repository;

    @Override
    public EmployeeDTO getEmployeeById(Long id) throws EmployeeNotFound {
        Optional<Employee> optionalEmp = repository.findById(id);
        if (optionalEmp.isEmpty()) {
            throw new EmployeeNotFound("By The given id employee not found: " + id);
        }
        return EmployeeDTO.toDTO(optionalEmp.get());
    }

    @Override
    public List<EmployeeDTO> getEmployeeByName(String name) throws EmployeeNotFound {
        List<Employee> employees = repository.findByName(name);
        if (employees == null || employees.isEmpty()) {
            throw new EmployeeNotFound("By The given name employee not found: " + name);
        }

        return employees.stream()
                .map(EmployeeDTO::toDTO)
                .toList();
    }

    @Override
    public List<EmployeeDTO> getEmployeeBetweenAge(int age1, int age2) throws EmployeeNotFound {
        List<Employee> employees = repository.findEmployeeByAgeBetween(age1, age2);
        if (employees == null || employees.isEmpty()) {
            throw new EmployeeNotFound("Employee not found between the age: " + age1 + ", and: " + age2);
        }

        return employees.stream()
                .map(EmployeeDTO::toDTO)
                .toList();
    }

    @Override
    public String createNewEmployee(EmployeeDTO empDTO) throws EmployeeAlreadyPresentException {
        Optional<Employee> optEmp= repository.findByEmail(empDTO.getEmail());
        
        if(optEmp.isPresent()) {
        	throw new EmployeeAlreadyPresentException("The employee with email already exist:"+empDTO.getEmail());
        }

        Employee emp = new Employee();
        emp.setName(empDTO.getName());
        emp.setAge(empDTO.getAge());
        emp.setEmail(empDTO.getEmail());

        repository.saveAndFlush(emp);
        return "Employee data saved";
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO updatedEmployee) throws EmployeeNotFound {
        Optional<Employee> optionalEmp = repository.findById(id);
        if (optionalEmp.isEmpty()) {
            throw new EmployeeNotFound("By The given id employee not found: " + id);
        }

        Employee emp = optionalEmp.get();
        emp.setName(updatedEmployee.getName());
        emp.setAge(updatedEmployee.getAge());
        emp.setEmail(updatedEmployee.getEmail());

        repository.saveAndFlush(emp);

        return EmployeeDTO.toDTO(emp);
    }
}
