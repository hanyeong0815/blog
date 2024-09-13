create table if not exists profile.blog_domain (
    id              BIGSERIAL,
    profile_id      BIGSERIAL,
    domain          varchar(60),
    status          varchar(30),

    constraint      pk_domain_id    primary key(id),
    constraint      uq_domain       unique(domain)
);

create unique index udx_board_domain_profile_id on profile.blog_domain(profile_id);