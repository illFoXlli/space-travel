create table client
(
    id   bigint auto_increment primary key,
    name varchar(200) not null,
    constraint chk_client_name_length check (length(name) >= 3)
);

create table planet
(
    id   varchar(20) primary key,
    name varchar(500) not null,
    constraint chk_planet_id_format check (regexp_like(id, '^[A-Z0-9]+$'))
);

create table ticket
(
    id             bigint auto_increment primary key,
    created_at     timestamp   not null,
    client_id      bigint      not null,
    from_planet_id varchar(20) not null,
    to_planet_id   varchar(20) not null,

    constraint fk_ticket_client foreign key (client_id) references client (id),
    constraint fk_ticket_from_planet foreign key (from_planet_id) references planet (id),
    constraint fk_ticket_to_planet foreign key (to_planet_id) references planet (id),
    constraint chk_ticket_planets_different check (from_planet_id <> to_planet_id)
);
