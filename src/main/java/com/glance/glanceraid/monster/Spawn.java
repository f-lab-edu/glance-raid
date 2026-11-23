package com.glance.glanceraid.monster;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "spawn")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Spawn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spawnId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "monster_id", nullable = false)
    private MonsterTemplate monsterTemplate;

    @Column(nullable = false)
    private int maxCatcher;

    @Column(nullable = false)
    private long rewardPoint;

    @Column(nullable = false)
    private Instant spawnedAt;

    @Column(nullable = false)
    private Instant expiresAt;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    private Spawn(MonsterTemplate monsterTemplate, Instant spawnedAt) {
        this.monsterTemplate = monsterTemplate;
        this.maxCatcher = monsterTemplate.getMaxCatcher();
        this.rewardPoint = monsterTemplate.getRewardPoint();
        this.spawnedAt = spawnedAt;
        this.expiresAt = spawnedAt.plusSeconds(monsterTemplate.getDurationSec());
    }

    public static Spawn create(MonsterTemplate monsterTemplate, Instant spawnedAt) {
        return new Spawn(monsterTemplate, spawnedAt);
    }
}