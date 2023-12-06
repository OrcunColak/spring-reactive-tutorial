package com.colak.springreactivetutorial.declarativeclient;

import com.colak.springreactivetutorial.controller.EmployeeController;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

public interface EmployeeClient {

    @GetExchange(EmployeeController.EMPLOYEE_URL)
    Mono<EmployeeClientResponse> fetchAll();
}
