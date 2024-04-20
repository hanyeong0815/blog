CREATE TABLE IF NOT EXISTS member.password_last_update (
    id                  bigserial      PRIMARY KEY,
    member_id           uuid,
    username            VARCHAR(255),
    created_at          timestamp(6)         NOT NULL            DEFAULT CURRENT_TIMESTAMP(6)
);

-- 로그인 시 조회할 가능성이 있기 때문에 인덱스 부여
CREATE INDEX idx_password_last_update_member_id ON member.password_last_update(member_id);
CREATE INDEX idx_password_last_update_username ON member.password_last_update(username);
