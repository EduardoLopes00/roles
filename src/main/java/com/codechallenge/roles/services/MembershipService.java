package com.codechallenge.roles.services;

import com.codechallenge.roles.data.models.Membership;
import com.codechallenge.roles.data.models.Role;
import com.codechallenge.roles.data.repositories.MembershipRepository;
import com.codechallenge.roles.data.repositories.RoleRepository;
import com.codechallenge.roles.infra.exceptions.AlreadyExistsException;

import com.codechallenge.roles.infra.exceptions.NotFoundException;
import com.codechallenge.roles.services.interfaces.MembershipServiceInterface;
import com.codechallenge.roles.web.DTO.MembershipDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class MembershipService implements MembershipServiceInterface {

    @Autowired
    MembershipRepository membershipRepository;

    @Autowired
    RoleRepository roleRepository;
    public Membership assignRole(MembershipDTO membershipDTO) {
        Role role = roleRepository.findById(membershipDTO.getRoleId()).orElseThrow(() -> new NotFoundException(membershipDTO.getRoleId().toString()));

        Membership membership = membershipRepository.findFirstByTeamIdAndUserId(membershipDTO.getTeamId(), membershipDTO.getUserId());

        if (membership != null) {
            if (membership.hasTheSameRole(membershipDTO.getRoleId())){
                throw new AlreadyExistsException(membershipDTO.getRoleId().toString());
            }

            membership.setRole(role);
            return membershipRepository.saveAndFlush(membership);
        }

        return membershipRepository.saveAndFlush(membershipDTO.toModel(role));
    }

    public List<Membership> getMembershipsByRole(UUID roleId) {
        List<Membership> membershipList = membershipRepository.findByRoleId(roleId).stream().toList();

        return membershipList;
    }
}
