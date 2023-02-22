package com.codechallenge.roles.services;

import com.codechallenge.roles.data.models.Membership;
import com.codechallenge.roles.data.models.Role;
import com.codechallenge.roles.data.repositories.MembershipRepository;
import com.codechallenge.roles.data.repositories.RoleRepository;
import com.codechallenge.roles.infra.exceptions.AlreadyExistsException;
import com.codechallenge.roles.infra.exceptions.CannotDeleteException;
import com.codechallenge.roles.infra.exceptions.NotFoundException;
import com.codechallenge.roles.services.interfaces.RoleServiceInterface;
import com.codechallenge.roles.web.DTO.RoleDTO;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleService implements RoleServiceInterface {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private MembershipRepository membershipRepository;

    public Role createRole(RoleDTO roleDTO) {
        Role foundRole = roleRepository.findFirstByNameIgnoreCase(roleDTO.getName());

        if (foundRole != null) {
            throw new AlreadyExistsException(roleDTO.getName());
        }

        Role newRole = roleRepository.save(roleDTO.toModel());

        return newRole;
    }

    public Role getRoleByUserIdAndTeamId(@NonNull UUID userId, @NonNull UUID teamId) {

        Membership membership = membershipRepository.findFirstByTeamIdAndUserId(teamId, userId);

        if (membership == null) {
            throw new NotFoundException(userId.toString(), teamId.toString());
        }

        return membership.getRole();
    }

    public void deleteRole(UUID roleId) {
        roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException(roleId.toString()));

        List<Membership> memberships = membershipRepository.findByRoleId(roleId);

        if (memberships != null) {
            StringBuilder errorMessage = buildCannotDeleteRoleMessage(memberships);

            throw new CannotDeleteException(roleId.toString(), errorMessage.toString());
        }
    }

    private StringBuilder buildCannotDeleteRoleMessage(List<Membership> memberships) {
        StringBuilder errorMessage = new StringBuilder("It is associated with the following memberships: ");
        memberships.forEach((membership) -> {
            errorMessage.append(membership.getId() + ", ");
        });

        errorMessage.delete(errorMessage.length() - 2, errorMessage.length()).append("! Delete these memberships or change its roles and try again.");

        return errorMessage;
    }
}
