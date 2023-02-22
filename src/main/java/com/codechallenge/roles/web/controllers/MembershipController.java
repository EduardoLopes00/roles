package com.codechallenge.roles.web.controllers;

import com.codechallenge.roles.data.models.Membership;
import com.codechallenge.roles.services.MembershipService;
import com.codechallenge.roles.web.DTO.MembershipDTO;
import com.codechallenge.roles.web.controllers.interfaces.MembershipControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("memberships")
public class MembershipController implements MembershipControllerInterface {

    @Autowired
    MembershipService membershipService;

    @PostMapping(value="/assign")
    public ResponseEntity<Membership> assignRoleToMembership(@RequestBody MembershipDTO membershipDTO) {
        Membership membership = membershipService.assignRole(membershipDTO);

        return ResponseEntity.ok(membership);
    }
}
