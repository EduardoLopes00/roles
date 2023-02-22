package com.codechallenge.roles.services.interfaces;

import com.codechallenge.roles.data.models.Membership;
import com.codechallenge.roles.web.DTO.MembershipDTO;

public interface MembershipServiceInterface {

    public Membership assignRole(MembershipDTO membershipDTO);
}