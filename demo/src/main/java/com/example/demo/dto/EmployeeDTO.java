package com.example.demo.dto;

import com.example.demo.entity.Employee;

public class EmployeeDTO {

    private Long id;
    private String name;
    private int age;
    private String email;

    public EmployeeDTO() {}

    public EmployeeDTO(Long id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmployeeDTO [id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + "]";
    }

    // Convert Entity to DTO
    public static EmployeeDTO toDTO(Employee emp) {
        if (emp == null) return null;
        return new EmployeeDTO(emp.getId(), emp.getName(), emp.getAge(), emp.getEmail());
    }

    // Convert DTO to Entity
    public static Employee toEntity(EmployeeDTO empDTO) {
        if (empDTO == null) return null;
        Employee emp = new Employee();
        emp.setId(empDTO.getId());
        emp.setName(empDTO.getName());
        emp.setAge(empDTO.getAge());
        emp.setEmail(empDTO.getEmail());
        return emp;
    }
}
