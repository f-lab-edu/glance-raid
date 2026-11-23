CREATE TABLE monster_template (
    monster_id   BIGINT       NOT NULL AUTO_INCREMENT,
    monster_name VARCHAR(100) NOT NULL,
    monster_type VARCHAR(20)  NOT NULL,
    max_catcher  INT          NOT NULL,
    duration_sec INT          NOT NULL,
    reward_point BIGINT       NOT NULL DEFAULT 0,
    spawn_weight INT          NOT NULL DEFAULT 0,
    is_active    TINYINT(1)   NOT NULL DEFAULT 1,
    created_at   DATETIME(6)  NOT NULL,
    updated_at   DATETIME(6)  NOT NULL,
    create_user  VARCHAR(50)  NOT NULL,
    update_user  VARCHAR(50)  NOT NULL,
    PRIMARY KEY (monster_id),
    KEY idx_monster_template_monster_type_is_active (monster_type, is_active)
);

INSERT INTO monster_template
(monster_name, monster_type, max_catcher, duration_sec, reward_point, spawn_weight, is_active,
 created_at, updated_at, create_user, update_user)
VALUES
    ('슬랙 고양이',   'CAPTURE', 3, 60,  100,  70, 1, UTC_TIMESTAMP(6), UTC_TIMESTAMP(6), 'SYSTEM', 'SYSTEM'),
    ('커피머신 요정', 'CAPTURE', 1, 30,  300,  30, 1, UTC_TIMESTAMP(6), UTC_TIMESTAMP(6), 'SYSTEM', 'SYSTEM'),
    ('마감 드래곤',   'RAID',    0, 300, 1000, 0,  0, UTC_TIMESTAMP(6), UTC_TIMESTAMP(6), 'SYSTEM', 'SYSTEM');

CREATE TABLE spawn (
    spawn_id     BIGINT      NOT NULL AUTO_INCREMENT,
    monster_id   BIGINT      NOT NULL,
    max_catcher  INT         NOT NULL,
    reward_point BIGINT      NOT NULL,
    spawned_at   DATETIME(6) NOT NULL,
    expires_at   DATETIME(6) NOT NULL,
    created_at   DATETIME(6) NOT NULL,
    PRIMARY KEY (spawn_id),
    CONSTRAINT fk_spawn_monster_template FOREIGN KEY (monster_id) REFERENCES monster_template (monster_id)
);