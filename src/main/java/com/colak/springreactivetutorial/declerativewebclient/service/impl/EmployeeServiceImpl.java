package com.colak.springreactivetutorial.declerativewebclient.service.impl;

import com.colak.springreactivetutorial.declerativewebclient.jpa.Employee;
import com.colak.springreactivetutorial.declerativewebclient.repository.EmployeeRepository;
import com.colak.springreactivetutorial.declerativewebclient.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Flux<Employee> findAll() {
        return employeeRepository.findAll();
    }
}
