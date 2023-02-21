package com.codechallenge.roles.services;

import com.codechallenge.roles.data.models.Role;
import com.codechallenge.roles.data.repositories.RoleRepository;
import com.codechallenge.roles.infra.exceptions.AlreadyExistsException;
import com.codechallenge.roles.services.interfaces.RoleServiceInterface;
import com.codechallenge.roles.web.DTO.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements RoleServiceInterface {

    @Autowired
    RoleRepository roleRepository;

    public Role createRole(RoleDTO role) {
        Role foundRole = roleRepository.findFirstByNameIgnoreCase(role.getName());

        if (foundRole != null) {
            throw new AlreadyExistsException(role.getName());
        }

        Role newRole = roleRepository.save(role.toModel());

        return newRole;
    }
}
