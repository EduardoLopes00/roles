package com.codechallenge.roles.web.controllers;

import com.codechallenge.roles.data.models.Role;
import com.codechallenge.roles.web.DTO.RoleDTO;
import com.codechallenge.roles.web.controllers.interfaces.RoleControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("roles")
public class RoleController implements RoleControllerInterface {

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody RoleDTO role) {
        return null;
    }
}
