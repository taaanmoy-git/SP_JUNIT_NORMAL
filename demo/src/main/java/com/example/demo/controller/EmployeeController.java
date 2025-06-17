package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.serviceimpl.EmployeeServiceImpl;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	public EmployeeServiceImpl service;

	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@ResponseBody
	public EmployeeDTO findEmployeeById(@PathVariable(name="id") Long id) throws EmployeeNotFound {
		return service.getEmployeeById(id);
	}
	
}
