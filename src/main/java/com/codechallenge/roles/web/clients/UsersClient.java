package com.codechallenge.roles.web.clients;

import com.codechallenge.roles.data.models.Team;
import com.codechallenge.roles.data.models.User;
import com.codechallenge.roles.web.DTO.TeamsDTO;
import com.codechallenge.roles.web.DTO.UsersDTO;
import com.codechallenge.roles.web.interfaces.UsersClientInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UsersClient implements UsersClientInterface {
    @Autowired
    private WebClient usersWebClient;
    public List<UsersDTO> getUsers() {
        List<UsersDTO> users = this.usersWebClient.method(HttpMethod.GET).retrieve().bodyToMono(new ParameterizedTypeReference<List<UsersDTO>>() {}).block();

        return users;
    }

    public User getUserById(UUID userId) {
        User user = this.usersWebClient.method(HttpMethod.GET).uri("/" + userId).retrieve().bodyToMono(User.class).block();

        return user;
    }
}
