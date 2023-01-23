--liquibase formatted SQL
--changeset ffailla filename:20230121-create-regions.sql
--comment here is another file to create more tables

create table regions (
                         id UUID NOT NULL DEFAULT gen_random_uuid(),
                         name VARCHAR(255) NOT NULL,
                         zipCodes VARCHAR(255) NOT NULL,
                         coordinates VARCHAR(255) NOT NULL,
                             PRIMARY KEY (id)
);

CREATE TABLE addresses (
                           id UUID NOT NULL DEFAULT gen_random_uuid(),
                           address VARCHAR(255) NOT NULL,
                           zip_code VARCHAR(255) NOT NULL,
                           region_id UUID NOT NULL,
                           FOREIGN KEY (region_id) REFERENCES regions(id),
                           created_at TIMESTAMP DEFAULT NOW(),
                           PRIMARY KEY (id)
);