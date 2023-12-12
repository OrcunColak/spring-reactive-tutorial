package com.colak.springreactivetutorial.controller;

import com.colak.springreactivetutorial.declarativeclient.EmployeeSummary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeSummaryControllerTest {

    @Autowired
    WebTestClient webTestClient;

    // Call declarative client. In turn, it will call the local REST service
    @Test
    void test_getAllEmployees() {
        List<EmployeeSummary> response = webTestClient.get()
                .uri(EmployeeSummaryController.EMPLOYEE_SUMMARY_URL)
                .exchange()
                .expectBodyList(EmployeeSummary.class)
                .returnResult()
                .getResponseBody();


        assert response != null;
        assertEquals(2, response.size());
    }

}
