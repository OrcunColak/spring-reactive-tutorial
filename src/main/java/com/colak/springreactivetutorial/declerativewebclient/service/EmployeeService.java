package com.colak.springreactivetutorial.declerativewebclient.service;

import com.colak.springreactivetutorial.declerativewebclient.jpa.Employee;
import reactor.core.publisher.Flux;

public interface EmployeeService {
    Flux<Employee> findAll();
}
