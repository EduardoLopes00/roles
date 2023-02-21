package com.codechallenge.roles.services.interfaces;

import com.codechallenge.roles.data.models.Role;
import com.codechallenge.roles.web.DTO.RoleDTO;

public interface RoleServiceInterface {
    public Role createRole(RoleDTO role);
}
