--liquibase formatted sql
--changeset ffailla filename:20230129-01-alter-user-to-have-address.sql
ALTER TABLE users ADD COLUMN address_id UUID;
alter table users add
    FOREIGN KEY (address_id) REFERENCES addresses(id);
