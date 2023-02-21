package com.codechallenge.roles.data.models;

import com.codechallenge.roles.web.DTO.TeamsDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Team extends TeamsDTO {
    @JsonProperty
    private UUID teamLeadId;

    @JsonProperty
    private List<UUID> teamMemberIds;
}
