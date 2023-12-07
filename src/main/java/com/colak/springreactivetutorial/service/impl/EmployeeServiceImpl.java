package com.colak.springreactivetutorial.service.impl;

import com.colak.springreactivetutorial.jpa.Employee;
import com.colak.springreactivetutorial.repository.EmployeeRepository;
import com.colak.springreactivetutorial.service.EmployeeService;
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
