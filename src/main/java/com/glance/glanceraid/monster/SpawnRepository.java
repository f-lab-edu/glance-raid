package com.glance.glanceraid.monster;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface SpawnRepository extends JpaRepository<Spawn, Long> {
    boolean existsByExpiresAtAfter(Instant now);
}