package com.glance.glanceraid.monster;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class SpawnScheduler {

    // TODO: 프로젝트 진행 후 설정 파일로 분리
    private static final long SPAWN_INTERVAL_MS = 10_000;
    private static final double SPAWN_PROBABILITY = 30; //% 단위로 표시

    private final SpawnService spawnService;

    @Scheduled(fixedDelay = SPAWN_INTERVAL_MS)
    public void trySpawn() {
        if (ThreadLocalRandom.current().nextInt(100) < SPAWN_PROBABILITY) {
            spawnService.spawnIfAbsent();
        }
    }
}