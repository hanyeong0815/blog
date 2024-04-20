create table if not exists member.member_roles (
    id          bigserial       not null,
    member_id   uuid        not null,
    role        varchar(30),

    constraint pk_member_roles_id primary key(id),
    constraint fk_member_id foreign key(member_id) references member.member(id)
);