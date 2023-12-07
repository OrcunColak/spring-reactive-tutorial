package com.colak.springreactivetutorial.declarativeclient;

import com.colak.springreactivetutorial.controller.EmployeeController;
import com.colak.springreactivetutorial.jpa.Employee;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Flux;

public interface EmployeeSummaryClient {

    @GetExchange(EmployeeController.EMPLOYEE_URL)
    Flux<Employee> fetchAll();
}
