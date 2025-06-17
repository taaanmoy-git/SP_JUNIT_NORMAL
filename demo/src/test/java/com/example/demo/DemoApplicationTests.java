package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Testing EmployeeServiceImpl
import java.util.Optional;

// Testing EmpServiceIMPL

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.exception.EmployeeNotFound;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.serviceimpl.EmployeeServiceImpl;

@SpringBootTest
//@AutoConfigureTestDatabase // if db not present
@ExtendWith(MockitoExtension.class) // Mock and Inject mock better performance
class DemoApplicationTests {

	//@BeforeAll ,@AfterAll must be public static
	@BeforeAll
	public static void startBeforeALL() {
		System.out.println("---------Test Start--------");
	}
	
	@AfterAll
	public static void endAfterALL() {
		System.out.println("--------Test ended-----------");
	}
	
	@BeforeEach
	void startBefore() {
		System.out.println("Start befor eatch");
	}
	
	public Employee employee;
	@BeforeEach
    void setup() {
		System.out.println("Resetting emp data");
        employee = new Employee();
        employee.setId(1L);
        employee.setName("John");
        employee.setAge(30);
        employee.setEmail("john@example.com");
    }
	
	@Mock
	public EmployeeRepository repository;
	
	@InjectMocks
	public EmployeeServiceImpl service;
	
	@Test
	@DisplayName("Employee found")
	void testGetEmployeeById_Success() throws EmployeeNotFound {
		Mockito.when(repository.findById(employee.getId()))
					.thenReturn(Optional.of(employee));
		
		EmployeeDTO empDTO= service.getEmployeeById(employee.getId());
		
		Assertions.assertEquals("John",empDTO.getName());
		assertEquals(30,empDTO.getAge());
		assertEquals("john@example.com", empDTO.getEmail());
	}
	
	@Test
	@DisplayName("EmployeeNotFound Exception")
	void testGetEmployeeById_Failed(){
		Mockito.when(repository.findById(employee.getId()))
						.thenReturn(Optional.empty());
		
		
		EmployeeNotFound empNotFoundException= Assertions
				.assertThrows(EmployeeNotFound.class, ()-> service.getEmployeeById(employee.getId()));
		
		assertEquals("By The given id employee not found: " + employee.getId(), empNotFoundException.getMessage());
		
	}

}
