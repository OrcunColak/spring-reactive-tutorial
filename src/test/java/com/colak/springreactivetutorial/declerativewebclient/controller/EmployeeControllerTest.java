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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = EmployeeController.class)
@AutoConfigureWebTestClient
@Tag("Unit")
class EmployeeControllerTest {

    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    EmployeeService employeeService;

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
}
