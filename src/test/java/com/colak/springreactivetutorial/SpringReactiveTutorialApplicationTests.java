package com.colak.springreactivetutorial;

import com.colak.springreactivetutorial.declarativeclient.EmployeeSummary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringReactiveTutorialApplicationTests {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void test_getAllEmployees() {
        List<EmployeeSummary> response = webTestClient.get()
                .uri("/v1/employees")
                .exchange()
                .expectBodyList(EmployeeSummary.class)
                .returnResult()
                .getResponseBody();


        assertEquals(2, response.size());
    }

}