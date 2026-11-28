package com.glance.glanceraid.monster;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface MonsterTemplateRepository extends JpaRepository<MonsterTemplate, Long> {
    List<MonsterTemplate> findByMonsterTypeAndActiveTrue(MonsterType monsterType);
}