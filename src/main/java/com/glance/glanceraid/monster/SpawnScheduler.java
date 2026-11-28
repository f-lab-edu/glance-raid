package com.glance.glanceraid.monster;

import java.util.concurrent.ThreadLocalRandom;

import com.glance.glanceraid.ws.GameWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tools.jackson.databind.json.JsonMapper;

@Component
@RequiredArgsConstructor
public class SpawnScheduler {

    // TODO: 프로젝트 진행 후 설정 파일로 분리
    private static final long SPAWN_INTERVAL_MS = 10_000;
    private static final double SPAWN_PROBABILITY = 0.3;

    private final SpawnService spawnService;
    private final GameWebSocketHandler gameWebSocketHandler;
    private final JsonMapper jsonMapper;

    @Scheduled(fixedDelay = SPAWN_INTERVAL_MS)
    public void trySpawn() {
        if (ThreadLocalRandom.current().nextDouble() < SPAWN_PROBABILITY) {
            spawnService.spawnIfAbsent()
                    .ifPresent(notice -> gameWebSocketHandler.broadcast(jsonMapper.writeValueAsString(notice)));
        }
    }
}