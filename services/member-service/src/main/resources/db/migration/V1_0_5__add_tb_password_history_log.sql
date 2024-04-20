CREATE TABLE IF NOT EXISTS member.password_history_log(
    id                  uuid    DEFAULT uuid_generate_v4 () primary key,
    member_id           uuid,
    username            VARCHAR(255),
    personal_signed_digest VARCHAR(255),
    created_at          timestamp(6)         NOT NULL            DEFAULT CURRENT_TIMESTAMP(6)
);

CREATE INDEX idx_password_history_log_username ON member.password_history_log (username);

CREATE INDEX idx_password_history_log_digest_username ON member.password_history_log (personal_signed_digest, username);
