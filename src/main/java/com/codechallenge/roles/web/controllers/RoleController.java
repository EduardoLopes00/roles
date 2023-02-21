package com.codechallenge.roles.web.controllers;

import com.codechallenge.roles.data.models.Role;
import com.codechallenge.roles.services.RoleService;
import com.codechallenge.roles.web.DTO.RoleDTO;
import com.codechallenge.roles.web.controllers.interfaces.RoleControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("roles")
public class RoleController implements RoleControllerInterface {

    @Autowired
    RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody RoleDTO roleDTO) {
        Role newRole = roleService.createRole(roleDTO);

        return ResponseEntity.ok(newRole);
    }
}
