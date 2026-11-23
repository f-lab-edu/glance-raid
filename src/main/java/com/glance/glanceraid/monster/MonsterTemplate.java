package com.glance.glanceraid.monster;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "monster_template")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MonsterTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long monsterId;

    @Column(nullable = false, length = 100)
    private String monsterName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MonsterType monsterType;

    @Column(nullable = false)
    private int maxCatcher;

    @Column(nullable = false)
    private int durationSec;

    @Column(nullable = false)
    private long rewardPoint;

    @Column(nullable = false)
    private int spawnWeight;

    @Column(name = "is_active", nullable = false)
    private boolean active;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;

    @CreatedBy
    @Column(nullable = false, updatable = false, length = 50)
    private String createUser;

    @LastModifiedBy
    @Column(nullable = false, length = 50)
    private String updateUser;
}