package com.codechallenge.roles.services;

import com.codechallenge.roles.data.models.Membership;
import com.codechallenge.roles.data.repositories.MembershipRepository;
import com.codechallenge.roles.data.repositories.RoleRepository;
import com.codechallenge.roles.web.DTO.MembershipDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.codechallenge.roles.utils.mockObjects.MembershipMocks.*;
import static com.codechallenge.roles.utils.mockObjects.MembershipMocks.DEFAULT_MEMBERSHIP_LIST;
import static com.codechallenge.roles.utils.mockObjects.RoleMocks.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class membershipServiceTest {

    @InjectMocks
    private MembershipService membershipsService;
    @Mock
    private MembershipRepository membershipRepository;
    @Mock
    private RoleRepository roleRepository;

    @Test
    public void shouldReturnNewMembership_WhenTryToAssignRoleToANonexistentMembership() {
        MembershipDTO membershipDTO = MembershipDTO.fromModel(DEFAULT_MEMBERSHIP());

        Membership expectedMembership = membershipDTO.toModel(DEVELOPER_ROLE());

        when(roleRepository.findById(membershipDTO.getRoleId())).thenReturn(Optional.ofNullable(DEVELOPER_ROLE()));

        when(membershipRepository.findFirstByTeamIdAndUserId(membershipDTO.getTeamId(), membershipDTO.getUserId())).thenReturn(null);
        when(membershipRepository
                .saveAndFlush(expectedMembership))
                .thenReturn(expectedMembership);

        Membership membershipSUT = membershipsService.assignRole(membershipDTO);

        assertNotNull(membershipSUT);
        assertEquals(expectedMembership.getId(), membershipSUT.getId());
    }

    @Test
    public void shouldReturnUpdatedMembership_WhenTryToAssignRoleToAnExistentMembership() {
        MembershipDTO membershipDTO = MembershipDTO.fromModel(DEFAULT_MEMBERSHIP_WITH_ANOTHER_ROLE());

        Membership expectedMembership = membershipDTO.toModel(TESTER_ROLE());
        Membership developerMembership = membershipDTO.toModel(DEVELOPER_ROLE());

        when(roleRepository.findById(membershipDTO.getRoleId())).thenReturn(Optional.ofNullable(TESTER_ROLE()));

        when(membershipRepository.findFirstByTeamIdAndUserId(membershipDTO.getTeamId(), membershipDTO.getUserId())).thenReturn(developerMembership);
        when(membershipRepository
                .saveAndFlush(expectedMembership))
                .thenReturn(expectedMembership);

        Membership membershipSUT = membershipsService.assignRole(membershipDTO);

        assertNotNull(membershipSUT);
        assertEquals(expectedMembership.getId(), membershipSUT.getId());
    }

    @Test
    public void shouldReturnAllMembershipsByRole_whenCalledWithARoleWhoseHasMembershipAssociated() {
        UUID roleId = DEVELOPER_UUID;

        when(membershipRepository.findByRoleId(roleId)).thenReturn(DEFAULT_MEMBERSHIP_LIST());

        List<Membership> membershipListSUT = membershipsService.getMembershipsByRole(roleId);

        assertNotNull(membershipListSUT);
        assertEquals(DEFAULT_MEMBERSHIP_LIST().get(0).getRole().getId(), membershipListSUT.get(0).getRole().getId());
    }

    @Test
    public void shouldReturnNoneMemberships_whenCalledWithARoleWhoseHasNoMembershipAssociated() {
        UUID roleId = TESTER_UUID;

        when(membershipRepository.findByRoleId(roleId)).thenReturn(EMPTY_MEMBERSHIP_LIST());

        List<Membership> membershipListSUT = membershipsService.getMembershipsByRole(roleId);

        assertNotNull(membershipListSUT);
        assertEquals(0, EMPTY_MEMBERSHIP_LIST().size());
    }
}
