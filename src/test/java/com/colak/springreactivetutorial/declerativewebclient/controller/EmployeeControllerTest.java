package com.colak.springreactivetutorial.declerativewebclient.controller;

import com.colak.springreactivetutorial.declerativewebclient.jpa.Employee;
import com.colak.springreactivetutorial.declerativewebclient.service.EmployeeService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

// Load only EmployeeController
@WebFluxTest(controllers = EmployeeController.class)
@AutoConfigureWebTestClient
@Tag("Unit")
class EmployeeControllerTest {

    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private EmployeeService employeeService;

    @Test
    void testFindAll() {
        when(employeeService.findAll())
                .thenReturn(Flux.just(
                                new Employee(1, "employee1", "lastname1"),
                                new Employee(2, "employee2", "lastname2")
                        )
                );

        final String url = EmployeeController.EMPLOYEE_URL;
        webTestClient
                .get()
                .uri(url)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(Employee.class)
                .hasSize(2)
                .consumeWith(listEntityExchangeResult -> {
                    List<Employee> employeeList = listEntityExchangeResult.getResponseBody();
                    assert employeeList != null;
                    Employee lastEmployee = employeeList.getLast();

                    assertEquals(2, lastEmployee.getId());
                });
    }

    @Test
    void findById_ok() {
        var employee = new Employee(1, "employee1", "lastname1");

        when(employeeService.findById(employee.getId()))
                .thenReturn(Mono.just(employee));

        final String url = String.format("%s/%s", EmployeeController.EMPLOYEE_URL, employee.getId());
        webTestClient
                .get()
                .uri(url)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(Employee.class)
                .consumeWith(result -> {
                    var receivedEmployee = result.getResponseBody();

                    assert receivedEmployee != null;
                    assertEquals(employee.getId(), receivedEmployee.getId());
                    assertEquals(employee.getFirstName(), receivedEmployee.getFirstName());
                    assertEquals(employee.getLastName(), receivedEmployee.getLastName());

                });
    }

    @Test
    void findById_not_ok() {
        var employee = new Employee(1, "employee1", "lastname1");

        // Return empty mono
        when(employeeService.findById(employee.getId()))
                .thenReturn(Mono.empty());

        final String url = String.format("%s/%s", EmployeeController.EMPLOYEE_URL, employee.getId());
        webTestClient
                .get()
                .uri(url)
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    void deleteById_ok() {
        var employee = new Employee(1, "employee1", "lastname1");

        // Return empty mono
        when(employeeService.deleteById(employee.getId()))
                .thenReturn(Mono.empty());

        final String url = String.format("%s/%s", EmployeeController.EMPLOYEE_URL, employee.getId());
        webTestClient
                .delete()
                .uri(url)
                .exchange()
                .expectStatus()
                .isNoContent();
    }
}
