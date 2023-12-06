package com.colak.springreactivetutorial.config;

import com.colak.springreactivetutorial.controller.EmployeeClientController;
import com.colak.springreactivetutorial.controller.EmployeeController;
import com.colak.springreactivetutorial.declarativeclient.EmployeeClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
class HttpClientConfiguration {

    @Value("${client.baseurl}")
    private String baseUrl;

    @Bean
    EmployeeClient productClient(WebClient.Builder builder) {
        WebClient webClient = builder
                .baseUrl(baseUrl)
                .build();
        var wca = WebClientAdapter.create(webClient);
        return HttpServiceProxyFactory.builder().exchangeAdapter(wca)
                .build()
                .createClient(EmployeeClient.class);
    }
}
