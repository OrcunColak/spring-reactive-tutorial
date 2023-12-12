package com.colak.springreactivetutorial.controller;

import com.colak.springreactivetutorial.declarativeclient.EmployeeSummary;
import com.colak.springreactivetutorial.declarativeclient.EmployeeSummaryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
public class EmployeeSummaryController {

    public static final String EMPLOYEE_SUMMARY_URL = "/v1/employees";

    private final EmployeeSummaryClient employeeSummaryClient;

    // Return result from declarative web client
    // The declarative web client points to an internal REST service
    @GetMapping(EMPLOYEE_SUMMARY_URL)
    public Flux<EmployeeSummary> getAll() {
        return employeeSummaryClient.fetchAll()
                .map(employee -> new EmployeeSummary(employee.getId(), employee.getFirstName()));
    }

}
