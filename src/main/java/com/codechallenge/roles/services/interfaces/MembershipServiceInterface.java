package com.codechallenge.roles.services.interfaces;

import com.codechallenge.roles.data.models.Membership;
import com.codechallenge.roles.web.DTO.MembershipDTO;

import java.util.List;
import java.util.UUID;

public interface MembershipServiceInterface {

    public Membership assignRole(MembershipDTO membershipDTO);

    public List<Membership> getMembershipsByRole(UUID roleId);
}