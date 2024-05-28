CREATE TABLE IF NOT EXISTS profile.profile (
    id              BIGSERIAL,
    member_id       uuid,
    username        varchar(50),
    email           varchar(256),
    gender_type     varchar(30),
    name            varchar(50),
    nickname        varchar(50),

    constraint      pk_profile_id       primary key(id)
);

create unique index udx_profile_username on profile.profile(username);