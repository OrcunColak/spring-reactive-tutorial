package com.colak.springreactivetutorial.service;

import com.colak.springreactivetutorial.jpa.Employee;
import reactor.core.publisher.Flux;

public interface EmployeeService {
    Flux<Employee> findAll();
}
