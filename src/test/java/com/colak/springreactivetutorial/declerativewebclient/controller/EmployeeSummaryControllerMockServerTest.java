package com.colak.springreactivetutorial.declerativewebclient.controller;

import com.colak.springreactivetutorial.declerativewebclient.controller.EmployeeController;
import com.colak.springreactivetutorial.declerativewebclient.controller.EmployeeSummaryController;
import com.colak.springreactivetutorial.declerativewebclient.declarativeclient.EmployeeSummary;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.Header;
import org.mockserver.verify.VerificationTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@Slf4j
public class EmployeeSummaryControllerMockServerTest {

    // See https://java.testcontainers.org/modules/mockserver/
    // e.g mockserver/mockserver:5.15.0
    public static final DockerImageName MOCKSERVER_IMAGE = DockerImageName
            .parse("mockserver/mockserver")
            .withTag("mockserver-" + MockServerClient.class.getPackage().getImplementationVersion());

    @Container
    static MockServerContainer mockServerContainer = new MockServerContainer(MOCKSERVER_IMAGE);

    static MockServerClient mockServerClient;

    @Autowired
    WebTestClient webTestClient;

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        mockServerClient = new MockServerClient(
                mockServerContainer.getHost(),
                mockServerContainer.getServerPort()
        );
        registry.add("client.baseurl", mockServerContainer::getEndpoint);
        log.info("Serving on {}", mockServerContainer.getEndpoint());
    }

    @BeforeEach
    void setUp() {
        mockServerClient.reset();
    }

    // See https://testcontainers.com/guides/testing-rest-api-integrations-using-mockserver/
    // This test mocks internal REST service.
    // The declarative Http Client calls the mock service and returns the test data
    @Test
    void shouldGetEmployeeSummary() {
        // Mock internal REST service
        mockServerClient
                .when(
                        request().withMethod("GET").withPath(EmployeeController.EMPLOYEE_URL)
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8")
                                )
                                .withBody(
                                        json(
                                                """
                                                        [
                                                             {
                                                                 "id": 1,
                                                                 "firstName": "firstName1",
                                                                 "lastName": "lastName1"
                                                             },
                                                             {
                                                                 "id": 2,
                                                                 "firstName": "firstName2",
                                                                 "lastName": "lastName2"
                                                             }
                                                         ]
                                                        """
                                        )
                                )
                );

        List<EmployeeSummary> response = webTestClient.get()
                .uri(EmployeeSummaryController.EMPLOYEE_SUMMARY_URL)
                .exchange()
                .expectBodyList(EmployeeSummary.class)
                .returnResult()
                .getResponseBody();

        assert response != null;
        assertEquals(2, response.size());

        verifyMockServerRequest();
    }

    private void verifyMockServerRequest() {
        mockServerClient.verify(
                request().withMethod("GET").withPath(EmployeeController.EMPLOYEE_URL),
                VerificationTimes.exactly(1)
        );
    }
}

