package com.colak.springreactivetutorial.controller;

import com.colak.springreactivetutorial.declarativeclient.Employee;
import com.colak.springreactivetutorial.declarativeclient.EmployeeClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class EmployeeController {

    public static final String EMPLOYEE_URL = "/internal/employees";

    @GetMapping(EMPLOYEE_URL)
    public Mono<EmployeeClientResponse> getAllFromDatabase() {
        List<Employee> employeeList = List.of(
                new Employee(1, "employee1"),
                new Employee(2, "employee2"));

        EmployeeClientResponse employeeClientResponse = new EmployeeClientResponse(employeeList);
        return Mono.just(employeeClientResponse);
    }
}
