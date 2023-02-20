package com.codechallenge.roles.data.repositories;

import com.codechallenge.roles.data.models.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MembershipRepository extends JpaRepository<Membership, UUID> {
}
