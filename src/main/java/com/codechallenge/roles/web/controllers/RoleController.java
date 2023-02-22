package com.codechallenge.roles.web.controllers;

import com.codechallenge.roles.data.models.Role;
import com.codechallenge.roles.services.RoleService;
import com.codechallenge.roles.web.DTO.RoleDTO;
import com.codechallenge.roles.web.controllers.interfaces.RoleControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("roles")
public class RoleController implements RoleControllerInterface {

    @Autowired
    RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody RoleDTO roleDTO) {
        Role newRole = roleService.createRole(roleDTO);

        return ResponseEntity.status(201).body(newRole);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<Role> getRoleByUserIdAndTeamId(
            @RequestParam UUID userId,
            @RequestParam UUID teamId) {
        Role role = roleService.getRoleByUserIdAndTeamId(userId, teamId);

        return ResponseEntity
                .status(200)
                .body(role);
    }
}
