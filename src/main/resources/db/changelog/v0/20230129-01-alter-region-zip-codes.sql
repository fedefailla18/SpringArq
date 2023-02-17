--liquibase formatted sql
--changeset ffailla filename:20230129-01-alter-user-to-have-address.sql
ALTER TABLE regions DROP COLUMN zipcodes;
ALTER TABLE regions ADD COLUMN zip_codes VARCHAR(10)[] default '{}';
