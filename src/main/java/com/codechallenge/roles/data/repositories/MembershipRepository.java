package com.codechallenge.roles.data.repositories;

import com.codechallenge.roles.data.models.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface MembershipRepository extends JpaRepository<Membership, UUID> {
    public Membership findFirstByTeamIdAndUserId(UUID teamId, UUID userId);

}
