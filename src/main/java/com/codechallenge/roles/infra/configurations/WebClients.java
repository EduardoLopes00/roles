package com.codechallenge.roles.infra.configurations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Configuration
public class WebClients {

    @Autowired
    private ClientsURL clientsURL;

    @Bean
    public WebClient teamsWebClient(WebClient.Builder builder) {
        return builder.baseUrl(clientsURL.getTeamsClientURL()).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }

    @Bean
    public WebClient usersWebClient(WebClient.Builder builder) {
        return builder.baseUrl(clientsURL.getUsersClientURL()).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }
}
