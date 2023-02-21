package com.codechallenge.roles.web.clients;

import com.codechallenge.roles.data.models.Team;
import com.codechallenge.roles.web.DTO.TeamsDTO;
import com.codechallenge.roles.web.clients.interfaces.TeamsClientInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class TeamsClient implements TeamsClientInterface {

    @Autowired
    private WebClient teamsWebClient;

    public List<TeamsDTO> getTeams() {
        List<TeamsDTO> teams = this.teamsWebClient.method(HttpMethod.GET).retrieve().bodyToMono(new ParameterizedTypeReference<List<TeamsDTO>>() {}).block();

        return teams;
    }

    public Team getTeamById(UUID teamId) {
        Team team = this.teamsWebClient.method(HttpMethod.GET).uri("/" + teamId).retrieve().bodyToMono(Team.class).block();

        return team;
    }
}
