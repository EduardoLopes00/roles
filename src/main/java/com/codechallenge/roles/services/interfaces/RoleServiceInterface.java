package com.codechallenge.roles.services.interfaces;

import com.codechallenge.roles.data.models.Role;
import com.codechallenge.roles.web.DTO.RoleDTO;
import lombok.NonNull;

import java.util.UUID;

public interface RoleServiceInterface {
    public Role createRole(RoleDTO role);

    public Role getRoleByUserIdAndTeamId(@NonNull UUID userId, @NonNull UUID teamId);

    public void deleteRole(UUID roleId);
}
