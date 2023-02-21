package com.codechallenge.roles.web.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class TeamsDTO {

    @Id
    @JsonProperty
    private UUID id;

    @JsonProperty
    private String name;
}
