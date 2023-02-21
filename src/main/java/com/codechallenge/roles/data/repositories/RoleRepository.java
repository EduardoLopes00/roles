package com.codechallenge.roles.data.repositories;

import com.codechallenge.roles.data.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    public Role findFirstByNameIgnoreCase(String name);

}
