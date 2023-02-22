package com.codechallenge.roles.web.controllers.interfaces;

import com.codechallenge.roles.data.models.Membership;
import com.codechallenge.roles.web.DTO.MembershipDTO;
import org.springframework.http.ResponseEntity;

public interface MembershipControllerInterface {
    public ResponseEntity<Membership> assignRoleToMembership(MembershipDTO membershipDTO);
}
