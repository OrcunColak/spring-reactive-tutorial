package com.colak.springreactivetutorial.declerativewebclient.service;

import com.colak.springreactivetutorial.declerativewebclient.jpa.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
    Flux<Employee> findAll();

    Mono<Employee> findById(int id);

    Mono<Void> deleteById(int id);

    Mono<Employee> save(Employee employee);

}
