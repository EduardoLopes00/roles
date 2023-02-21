package com.codechallenge.roles.web.controllers.interfaces;

import com.codechallenge.roles.data.models.Role;
import com.codechallenge.roles.web.DTO.RoleDTO;
import org.springframework.http.ResponseEntity;

public interface RoleControllerInterface {
    public ResponseEntity<Role> createRole(RoleDTO roleDTO);

}
