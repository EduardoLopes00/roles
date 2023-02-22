package com.codechallenge.roles.web.controllers.interfaces;

import com.codechallenge.roles.data.models.Role;
import com.codechallenge.roles.web.DTO.RoleDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public interface RoleControllerInterface {
    public ResponseEntity<Role> createRole(RoleDTO roleDTO);

    public ResponseEntity<Role> getRoleByUserIdAndTeamId(
            @RequestParam UUID userId,
            @RequestParam UUID teamId);

    public ResponseEntity<String> deleteRole(@PathVariable UUID roleId);

}
