package com.colak.springreactivetutorial.declerativewebclient.service.impl;

import com.colak.springreactivetutorial.declerativewebclient.jpa.Employee;
import com.colak.springreactivetutorial.declerativewebclient.repository.EmployeeRepository;
import com.colak.springreactivetutorial.declerativewebclient.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Flux<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Mono<Employee> findById(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Mono<Void> deleteById(int id) {
        // If the entity is not found in the persistence store it is silently ignored.
        return employeeRepository.deleteById(id);
    }
}
