package com.colak.springreactivetutorial.fileupload;

import com.colak.springreactivetutorial.BaseITTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.MultiValueMap;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.net.URI;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Testcontainers
@Tag("Integration")
class FileStorageControllerTest extends BaseITTest  {

    @Autowired
    private WebTestClient webTestClient;

    private MultiValueMap<String, HttpEntity<?>> generateBody() {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("files", new ClassPathResource("foo.txt"));
        return builder.build();
    }

    @Test
    void testUpload() {
        URI uri = URI.create("/v1/filestorage/upload");

        webTestClient.post()
                .uri(uri)
                .bodyValue(generateBody())
                .exchange()
                .expectStatus().isOk();

    }
}
