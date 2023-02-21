package com.codechallenge.roles.web.clients.interfaces;

import com.codechallenge.roles.data.models.Team;
import com.codechallenge.roles.web.DTO.TeamsDTO;

import java.util.List;
import java.util.UUID;

public interface TeamsClientInterface {
    public List<TeamsDTO> getTeams();

    public Team getTeamById(UUID teamId);
}
