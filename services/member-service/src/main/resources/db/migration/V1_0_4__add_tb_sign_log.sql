CREATE TABLE IF NOT EXISTS member.sign_log (
    id                  BIGSERIAL      PRIMARY KEY,
    member_id           uuid,
    username            VARCHAR(255),
    event_type          VARCHAR(255)        NOT NULL,
    remarks             VARCHAR(255),
    created_at          timestamp(6)         NOT NULL            DEFAULT CURRENT_TIMESTAMP(6)
);

CREATE INDEX idx ON member.sign_log(member_id);