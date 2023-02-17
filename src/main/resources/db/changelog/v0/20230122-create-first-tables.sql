--liquibase formatted sql
--changeset ffailla filename:20230122-create-first-tables.sql

CREATE TABLE users (
                      id UUID DEFAULT gen_random_uuid(),
                      email VARCHAR(255),
                      first_name VARCHAR(255),
                      last_name VARCHAR(255),
                      phone VARCHAR(255),
                      address VARCHAR(255),
                      created_at TIMESTAMP DEFAULT NOW(),

                      CONSTRAINT id_users PRIMARY KEY ( id )
);

CREATE TABLE mechanics (
                          id UUID DEFAULT gen_random_uuid(),
                          users_id UUID,
                          FOREIGN KEY (users_id) REFERENCES users(id),
                          specialities VARCHAR(255),
                          created_at TIMESTAMP DEFAULT NOW(),
                          UNIQUE (id)
);

CREATE TABLE appointments (
                             id UUID DEFAULT gen_random_uuid(),
                             mechanics_id UUID,
                             FOREIGN KEY (mechanics_id) REFERENCES mechanics(id),
                             users_id UUID,
                             FOREIGN KEY (users_id) REFERENCES users(id),
                             date TIMESTAMP,
                             status VARCHAR(255),
                             created_at TIMESTAMP DEFAULT NOW(),
                             UNIQUE (id)
);

CREATE TABLE repair_type (
                             id UUID PRIMARY KEY,
                             type VARCHAR(255)
);

create table type_of_jobs (
                              id UUID ,
                              type VARCHAR(255) ,
                              PRIMARY KEY (id)
);

CREATE TABLE repairs (
                         id UUID DEFAULT gen_random_uuid(),
                         type_of_jobs_id UUID,
                         FOREIGN KEY (type_of_jobs_id) REFERENCES type_of_jobs(id),
                         mechanics_id UUID,
                         date TIMESTAMP,
                         status VARCHAR(255),
                         repair_type_id UUID,
                         FOREIGN KEY (repair_type_id) REFERENCES repair_type(id),
                         created_at TIMESTAMP DEFAULT NOW()
);

create table cars (
                      id UUID  DEFAULT gen_random_uuid() PRIMARY KEY ,
                      users_id UUID ,
                      FOREIGN KEY (users_id) REFERENCES users(id),
                      brand VARCHAR(255) ,
                      make VARCHAR(255) ,
                      model VARCHAR(255) ,
                      engine VARCHAR(255) ,
                      trim VARCHAR(255) ,
                      year INT 
);

CREATE TABLE repair_orders (
                             id UUID PRIMARY KEY,
                             appointments_id UUID,
                             FOREIGN KEY (appointments_id) REFERENCES appointments(id),
                             repair_type_id UUID,
                             FOREIGN KEY (repair_type_id) REFERENCES repair_type(id),
                             car_id UUID,
                             FOREIGN KEY (car_id) REFERENCES cars(id),
                             appointment_date TIMESTAMP,
                             description VARCHAR(255),
                             cost FLOAT,
                             created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE comments (
                          id UUID PRIMARY KEY,
                          repair_order_id UUID,
                          FOREIGN KEY (repair_order_id) REFERENCES repair_orders(id),
                          mechanics_id UUID,
                          FOREIGN KEY (mechanics_id) REFERENCES mechanics(id),
                          comment VARCHAR(255),
                          date TIMESTAMP DEFAULT NOW()
);
