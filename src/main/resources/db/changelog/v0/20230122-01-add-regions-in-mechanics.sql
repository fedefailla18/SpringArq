--liquibase formatted SQL
--changeset ffailla filename:20230121-create-regions.sql
--comment here is another file to create more tables

alter table mechanics
add column region_id UUID;
alter table mechanics add
    FOREIGN KEY (region_id) REFERENCES regions(id);

CREATE INDEX region_id_idx ON addresses (region_id);
CREATE INDEX zip_code_idx ON addresses (zip_code);