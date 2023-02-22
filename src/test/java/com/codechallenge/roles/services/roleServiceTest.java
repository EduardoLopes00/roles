package com.codechallenge.roles.services;


import com.codechallenge.roles.data.models.Role;
import com.codechallenge.roles.data.repositories.MembershipRepository;
import com.codechallenge.roles.data.repositories.RoleRepository;

import com.codechallenge.roles.infra.exceptions.AlreadyExistsException;
import com.codechallenge.roles.infra.exceptions.CannotDeleteException;
import com.codechallenge.roles.infra.exceptions.NotFoundException;
import com.codechallenge.roles.utils.mockObjects.TeamMocks;
import com.codechallenge.roles.utils.mockObjects.UserMocks;
import com.codechallenge.roles.web.DTO.RoleDTO;
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
import static com.codechallenge.roles.utils.mockObjects.TeamMocks.*;
import static com.codechallenge.roles.utils.mockObjects.UserMocks.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class roleServiceTest {

    @InjectMocks
    private RoleService roleService;

    @Mock
    private MembershipRepository membershipRepository;
    @Mock
    private RoleRepository roleRepository;

    @Test
    public void shouldCreateNewRole_WhenPassARoleThatDoesntExists() {
        Role testingRole = DEVELOPER_ROLE();

        when(roleRepository.findFirstByNameIgnoreCase(testingRole.getName())).thenReturn(null);
        when(roleRepository.save(any(Role.class))).thenReturn(testingRole);

        Role roleSUT = roleService.createRole(RoleDTO.fromModel(testingRole));

        assertNotNull(roleSUT);
        assertEquals(testingRole.getId(), roleSUT.getId());
    }

    @Test
    public void shouldFailInCreateNewRole_WhenTheRoleAlreadyExists() {
        Role testingRole = DEVELOPER_ROLE();

        when(roleRepository.findFirstByNameIgnoreCase(testingRole.getName())).thenReturn(testingRole);

        assertThrows(AlreadyExistsException.class, () -> roleService.createRole(RoleDTO.fromModel(testingRole)));
    }

    @Test
    public void shouldGetRoleByUserIdAndTeamId_whenItExists() {
        Role testingRole = DEVELOPER_ROLE();

        when(membershipRepository.findFirstByTeamIdAndUserId(VARYINGPLUMCRICKET_TEAM_UUID, MARIONKERTZMANN_USER_UUID)).thenReturn(DEFAULT_MEMBERSHIP());

        Role roleSUT = roleService.getRoleByUserIdAndTeamId(MARIONKERTZMANN_USER_UUID, VARYINGPLUMCRICKET_TEAM_UUID);

        assertNotNull(roleSUT);
        assertEquals(roleSUT.getId(), testingRole.getId());
    }

    @Test
    public void shouldNotGetRoleByUserIdAndTeamId_whenItDoesntExists() {
        when(membershipRepository.findFirstByTeamIdAndUserId(VARYINGPLUMCRICKET_TEAM_UUID, MARIONKERTZMANN_USER_UUID)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> roleService.getRoleByUserIdAndTeamId(MARIONKERTZMANN_USER_UUID,VARYINGPLUMCRICKET_TEAM_UUID ));
    }

    @Test
    public void shouldDeleteRole_whenItExists() {
        Role testingRole = DEVELOPER_ROLE();

        when(roleRepository.findById(testingRole.getId())).thenReturn(Optional.of(testingRole));

        when(membershipRepository.findByRoleId(testingRole.getId())).thenReturn(null);

        roleService.deleteRole(testingRole.getId());

        verify(roleRepository, times(1)).delete(testingRole);
    }

    @Test
    public void shouldNotDeleteRole_whenItNotExists() {
        Role testingRole = DEVELOPER_ROLE();
        when(roleRepository.findById(testingRole.getId())).thenThrow(new NotFoundException(testingRole.getId().toString()));


        assertThrows(NotFoundException.class, () -> roleRepository.findById(testingRole.getId()));
    }

    @Test
    public void shouldNotDeleteRole_whenItIsLinkedToMemberships() {
        Role testingRole = DEVELOPER_ROLE();

        when(roleRepository.findById(testingRole.getId())).thenReturn(Optional.of(testingRole));

        when(membershipRepository.findByRoleId(testingRole.getId())).thenReturn(DEFAULT_MEMBERSHIP_LIST());

        assertThrows(CannotDeleteException.class, () -> roleService.deleteRole(testingRole.getId()));
    }
}
