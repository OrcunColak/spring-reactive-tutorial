package com.colak.springreactivetutorial.controller;

import com.colak.springreactivetutorial.declarativeclient.Employee;
import com.colak.springreactivetutorial.declarativeclient.EmployeeClient;
import com.colak.springreactivetutorial.declarativeclient.EmployeeClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class EmployeeClientController {

    private final EmployeeClient employeeClient;

    @GetMapping("/v1/employees")
    public Flux<Employee> getAll() {
        Mono<EmployeeClientResponse> employeeClientResponse = employeeClient.fetchAll();
        return employeeClientResponse
                .flatMapMany(response -> Flux.fromIterable(response.employees()));
    }

}
