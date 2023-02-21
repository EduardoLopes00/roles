package com.codechallenge.roles.data.models;

import com.codechallenge.roles.web.DTO.UsersDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class User extends UsersDTO {
    @JsonProperty
    private String firstName;

    @JsonProperty
    private String lastName;

    @JsonProperty
    private String avatarUrl;

    @JsonProperty
    private String location;
}
