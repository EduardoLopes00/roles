package com.codechallenge.roles.web.controllers.interfaces;

import com.codechallenge.roles.data.models.Membership;
import com.codechallenge.roles.web.DTO.MembershipDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

public interface MembershipControllerInterface {
    public ResponseEntity<Membership> assignRoleToMembership(MembershipDTO membershipDTO);

    public ResponseEntity<List<Membership>> getMembershipsByRole(@PathVariable UUID roleId);
}
