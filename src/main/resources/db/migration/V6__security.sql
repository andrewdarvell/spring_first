CREATE TABLE shop_user
(
    id       bigserial,
    login    varchar(40) unique not null,
    password varchar(80) not null,
    email    varchar(80) unique,
    enabled boolean,
    primary key (id)
);

create table roles
(
    id   serial,
    name varchar(50) not null,
    primary key (id)
);

CREATE TABLE users_roles
(
    shop_user_id bigint not null,
    role_id int    not null,
    primary key (shop_user_id, role_id),
    foreign key (shop_user_id) references shop_user (id),
    foreign key (role_id) references roles (id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN'),
       ('ROLE_MANAGER');

insert into shop_user (login, password, email, enabled)
values
    ('admin', '$2a$10$FjepnPniBgHYSTdMCXEFWuHq3oXM3hZuCQCsmgERzEnc/eBC88sGe', 'admin@gmail.com', true),
    ('manager', '$2a$10$FjepnPniBgHYSTdMCXEFWuHq3oXM3hZuCQCsmgERzEnc/eBC88sGe', 'manager@gmail.com', true),
    ('user', '$2a$10$FjepnPniBgHYSTdMCXEFWuHq3oXM3hZuCQCsmgERzEnc/eBC88sGe', 'user@gmail.com', true);
