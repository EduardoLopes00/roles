package com.codechallenge.roles.utils.mockObjects;

import com.codechallenge.roles.data.models.Membership;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.codechallenge.roles.utils.mockObjects.RoleMocks;
import com.codechallenge.roles.utils.mockObjects.UserMocks;

public class MembershipMocks {
    public static UUID DEFAULT_MEMBERSHIP_UUID = UUID.fromString("5ca72d11-9203-4f11-99ac-ac4496f4d02b");

    public static Membership DEFAULT_MEMBERSHIP() {
        return Membership.builder()
                .id(DEFAULT_MEMBERSHIP_UUID)
                .role(RoleMocks.DEVELOPER_ROLE())
                .userId(UserMocks.MARIONKERTZMANN_USER_UUID)
                .teamId(TeamMocks.VARYINGPLUMCRICKET_TEAM_UUID)
                .build();
    }

    public static Membership ANOTHER_MEMBERSHIP() {
        return Membership.builder()
                .id(DEFAULT_MEMBERSHIP_UUID)
                .role(RoleMocks.DEVELOPER_ROLE())
                .userId(UserMocks.MARIONKERTZMANN_USER_UUID)
                .teamId(TeamMocks.ORDINARYCORALLYNX_TEAM_UUID)
                .build();
    }

    public static Membership DEFAULT_MEMBERSHIP_WITH_ANOTHER_ROLE() {
        return Membership.builder()
                .id(DEFAULT_MEMBERSHIP_UUID)
                .role(RoleMocks.TESTER_ROLE())
                .userId(UserMocks.MARIONKERTZMANN_USER_UUID)
                .teamId(TeamMocks.VARYINGPLUMCRICKET_TEAM_UUID)
                .build();
    }

    public static List<Membership> DEFAULT_MEMBERSHIP_LIST() {
        List<Membership> membershipsList = new ArrayList<>();

        membershipsList.add(DEFAULT_MEMBERSHIP());
        membershipsList.add(ANOTHER_MEMBERSHIP());

        return membershipsList;
    }



}
