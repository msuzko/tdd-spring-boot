--liquibase formatted sql
--changeset Suzko-MA:car_1

drop table if exists cars;

create table cars
(
    id   bigint generated by default as identity primary key,
    name varchar(50) not null,
    type varchar(50) not null,
    constraint uix_ucp_id_type_method unique (name, type)
);
--rollback drop table cars;
