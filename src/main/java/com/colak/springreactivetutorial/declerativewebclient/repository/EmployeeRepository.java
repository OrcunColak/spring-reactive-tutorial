package com.colak.springreactivetutorial.declerativewebclient.repository;

import com.colak.springreactivetutorial.declerativewebclient.jpa.Employee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Integer> {
}
