package com.colak.springreactivetutorial.repository;

import com.colak.springreactivetutorial.jpa.Employee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Integer> {
}
