package com.colak.springreactivetutorial.declerativewebclient.config;

import com.colak.springreactivetutorial.declerativewebclient.declarativeclient.EmployeeSummaryClient;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
class HttpClientConfiguration {


    @Bean
    EmployeeSummaryClient employeeSummaryClient(@Value("${client.baseurl}") String baseUrl,
                                                @Nonnull WebClient.Builder builder) {
        WebClient webClient = builder
                .baseUrl(baseUrl)
                .build();
        var wca = WebClientAdapter.create(webClient);
        return HttpServiceProxyFactory.builder().exchangeAdapter(wca)
                .build()
                .createClient(EmployeeSummaryClient.class);
    }
}
