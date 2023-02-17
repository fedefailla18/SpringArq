--liquibase formatted SQL
--changeset ffailla filename:20230121-create-regions.sql
--comment here is another file to create more tables

create table regions (
                         id UUID  DEFAULT gen_random_uuid(),
                         name VARCHAR(255) ,
                         zipCodes VARCHAR(255) ,
                         coordinates VARCHAR(255) ,
                             PRIMARY KEY (id)
);

CREATE TABLE addresses (
                           id UUID  DEFAULT gen_random_uuid(),
                           address VARCHAR(255) ,
                           zip_code VARCHAR(255) ,
                           region_id UUID ,
                           FOREIGN KEY (region_id) REFERENCES regions(id),
                           created_at TIMESTAMP DEFAULT NOW(),
                           PRIMARY KEY (id)
);