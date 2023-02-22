package com.codechallenge.roles.data.repositories;

import com.codechallenge.roles.data.models.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface MembershipRepository extends JpaRepository<Membership, UUID> {
    public Membership findFirstByTeamIdAndUserId(UUID teamId, UUID userId);

    public List<Membership> findByRoleId(UUID roleId);

}
