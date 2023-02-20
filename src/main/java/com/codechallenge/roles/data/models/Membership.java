package com.codechallenge.roles.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="membership", uniqueConstraints = @UniqueConstraint(columnNames = {"team_id", "user_id"}))
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "team_id", nullable = false)
    private UUID teamId;
}

