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

    public Role createRole(RoleDTO roleDTO) {
        Role foundRole = roleRepository.findFirstByNameIgnoreCase(roleDTO.getName());

        if (foundRole != null) {
            throw new AlreadyExistsException(roleDTO.getName());
        }

        Role newRole = roleRepository.save(roleDTO.toModel());

        return newRole;
    }
}
