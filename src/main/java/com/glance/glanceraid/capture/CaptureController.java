package com.glance.glanceraid.capture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class CaptureController {

    @PostMapping("/monsters/{monsterId}/click")
    public Map<String, Object> click(@PathVariable Long monsterId) {

        log.info("클릭 수신!!! monsterId={}", monsterId);

        return Map.of(
                "monsterId", monsterId,
                "result", "RECEIVED",
                "receivedAt", Instant.now().toString());
    }
}