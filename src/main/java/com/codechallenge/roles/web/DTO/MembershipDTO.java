package com.codechallenge.roles.web.DTO;

import com.codechallenge.roles.data.models.Membership;
import com.codechallenge.roles.data.models.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import lombok.*;

import java.util.UUID;

import static java.util.Optional.ofNullable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MembershipDTO {
    @JsonProperty
    private UUID id;

    @JsonProperty
    @Nonnull
    private UUID roleId;

    @Nonnull
    @JsonProperty
    private UUID userId;

    @JsonProperty
    @Nonnull
    private UUID teamId;

    public static MembershipDTO fromModel(Membership membership) {
        if (membership == null) {
            return null;
        }

        return MembershipDTO.builder()
                .id(membership.getId())
                .roleId(membership.getRole().getId())
                .userId(membership.getUserId())
                .teamId(membership.getTeamId())
                .build();
    }

    public Membership toModel(Role role) {
        return Membership.builder()
                .id(this.id)
                .userId(this.userId)
                .role(role)
                .teamId(this.teamId)
                .build();
    }

    public Membership toModel() {
        return Membership.builder()
                .id(this.id)
                .userId(this.userId)
                .role(Role.builder().id(this.getRoleId()).build())
                .teamId(this.teamId)
                .build();
    }
}
