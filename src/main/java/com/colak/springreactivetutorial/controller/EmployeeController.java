package com.colak.springreactivetutorial.controller;

import com.colak.springreactivetutorial.jpa.Employee;
import com.colak.springreactivetutorial.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
public class EmployeeController {

    public static final String EMPLOYEE_URL = "/internal/employees";

    private final EmployeeService employeeService;

    // Return from database
    @GetMapping(EMPLOYEE_URL)
    public Flux<Employee> findAll() {
        return employeeService.findAll();
    }
}
