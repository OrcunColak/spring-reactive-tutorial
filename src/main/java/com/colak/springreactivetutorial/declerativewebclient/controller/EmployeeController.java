package com.colak.springreactivetutorial.declerativewebclient.controller;

import com.colak.springreactivetutorial.declerativewebclient.jpa.Employee;
import com.colak.springreactivetutorial.declerativewebclient.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @GetMapping(EMPLOYEE_URL + "/{id}")
    @Operation(summary = "Find by id.")
    Mono<ResponseEntity<Employee>> findById(@PathVariable int id) {
        return employeeService.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping(EMPLOYEE_URL + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete by id.")
    Mono<Void> deleteById(@PathVariable int id) {
        return employeeService.deleteById(id);
    }

    @PostMapping(EMPLOYEE_URL)
    @Operation(summary = "Save Employee.")
    Mono<ResponseEntity<Employee>> saveTodo(@RequestBody Employee employee) {
        return employeeService.save(employee)
                .map(savedTodo -> ResponseEntity.status(HttpStatus.CREATED).body(savedTodo));
    }
}
