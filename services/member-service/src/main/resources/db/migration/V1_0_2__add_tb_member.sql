create table if not exists member.member (
    id          uuid            default         uuid_generate_v4(),
    username    varchar(50)     not null,
    password    varchar(256),
    status      varchar(30),

    constraint  pk_member_id    primary key(id),
    constraint  uq_username     unique(username)
);

create unique index udx_member_username ON member.member(username);