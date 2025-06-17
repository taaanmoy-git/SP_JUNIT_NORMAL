package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByName(String name);

    @Query("SELECT e FROM Employee e WHERE e.age >= :age1 AND e.age <= :age2")
    List<Employee> findEmployeeByAgeBetween(
        @Param("age1") int age1,
        @Param("age2") int age2
    );

    @Query("SELECT e FROM Employee e WHERE e.email = :email")
    Optional<Employee> findByEmail(
        @Param("email") String name
    );

}
