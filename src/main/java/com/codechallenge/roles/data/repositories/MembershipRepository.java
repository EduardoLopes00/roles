package com.codechallenge.roles.data.repositories;

import com.codechallenge.roles.data.models.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface MembershipRepository extends JpaRepository<Membership, UUID> {
    public Membership findFirstByTeamIdAndUserId(UUID teamId, UUID userId);

//    @Query("update Membership m set m.roleId = cast(:roleId as org.hibernate.type.UUIDCharType) where m.teamId = cast(:teamId as org.hibernate.type.UUIDCharType) and m.userId = cast(:userId as org.hibernate.type.UUIDCharType)")
//    public Membership addRoleToMembership(@Param("roleId") UUID roleId, @Param("teamId") UUID teamId, @Param("userId") UUID userId);

}
