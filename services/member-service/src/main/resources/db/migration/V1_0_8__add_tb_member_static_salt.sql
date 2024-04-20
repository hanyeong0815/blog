CREATE TABLE IF NOT EXISTS member.member_static_salt (
    id                  uuid          PRIMARY KEY         DEFAULT uuid_generate_v4(),
    member_id           uuid,
    username            VARCHAR(255),
    static_salt         VARCHAR(255),
    created_at          timestamp(6)         NOT NULL            DEFAULT CURRENT_TIMESTAMP(6)
);

CREATE INDEX idx_member_static_salt_member_id ON member.member_static_salt(member_id);
CREATE INDEX idx_member_static_salt_username ON member.member_static_salt(username);
