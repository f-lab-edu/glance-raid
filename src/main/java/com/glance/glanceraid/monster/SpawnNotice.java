package com.glance.glanceraid.monster;

import java.time.Instant;

public record SpawnNotice(
        String type,
        Long spawnId,
        String monsterName,
        int maxCatcher,
        long rewardPoint,
        Instant expiresAt
) {
}