package com.colak.springreactivetutorial.declerativewebclient.declarativeclient;

import com.colak.springreactivetutorial.declerativewebclient.controller.EmployeeController;
import com.colak.springreactivetutorial.declerativewebclient.jpa.Employee;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Flux;

public interface EmployeeSummaryClient {

    @GetExchange(EmployeeController.EMPLOYEE_URL)
    Flux<Employee> fetchAll();
}
