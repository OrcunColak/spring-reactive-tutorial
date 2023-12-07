package com.colak.springreactivetutorial.controller;

import com.colak.springreactivetutorial.declarativeclient.EmployeeSummary;
import com.colak.springreactivetutorial.declarativeclient.EmployeeSummaryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
public class EmployeeClientController {

    private final EmployeeSummaryClient employeeSummaryClient;

    @GetMapping("/v1/employees")
    public Flux<EmployeeSummary> getAll() {
        return employeeSummaryClient.fetchAll()
                .map(employee -> new EmployeeSummary(employee.getId(), employee.getFirstName()));
    }

}
