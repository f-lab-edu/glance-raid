package com.glance.glanceraid.monster;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpawnService {

    private final MonsterTemplateRepository monsterTemplateRepository;
    private final SpawnRepository spawnRepository;

    @Transactional
    public Optional<SpawnNotice> spawnIfAbsent() {
        Instant now = Instant.now();

        // 한 번에 한 마리만: 살아있는 몬스터가 있으면 패스
        if (spawnRepository.existsByExpiresAtAfter(now)) {
            return Optional.empty();
        }

        List<MonsterTemplate> candidates =
                monsterTemplateRepository.findByMonsterTypeAndActiveTrue(MonsterType.CAPTURE);
        MonsterTemplate picked = pickByWeight(candidates);
        if (picked == null) {
            log.warn("등장 가능한 몬스터가 없음");
            return Optional.empty();
        }

        Spawn spawn = spawnRepository.save(Spawn.create(picked, now));
        log.info("몬스터 등장: spawnId={}, name={}, 만료={}",
                spawn.getSpawnId(), picked.getMonsterName(), spawn.getExpiresAt());

        return Optional.of(new SpawnNotice(
                "SPAWN",
                spawn.getSpawnId(),
                picked.getMonsterName(),
                spawn.getMaxCatcher(),
                spawn.getRewardPoint(),
                spawn.getExpiresAt()));
    }

    // 가중치 랜덤: 가중치 합 안에서 숫자를 하나 뽑고, 앞에서부터 빼가며 0 이하가 되는 몬스터를 선택
    private MonsterTemplate pickByWeight(List<MonsterTemplate> candidates) {
        int total = candidates.stream().mapToInt(MonsterTemplate::getSpawnWeight).sum();
        if (total <= 0) {
            return null;
        }
        int r = ThreadLocalRandom.current().nextInt(total); // 0 ~ total-1
        for (MonsterTemplate m : candidates) {
            r -= m.getSpawnWeight();
            if (r < 0) {
                return m;
            }
        }
        return null;
    }
}
