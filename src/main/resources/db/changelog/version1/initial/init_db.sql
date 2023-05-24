CREATE TABLE IF NOT EXISTS roles
(
    id   SERIAL PRIMARY KEY,
    role VARCHAR(45) not null unique
    );

CREATE TABLE IF NOT EXISTS extra_users_data
(
    id             BIGSERIAL PRIMARY KEY,
    id_passport     VARCHAR(20),
    name           VARCHAR(45),
    lastname       VARCHAR(45),
    date_of_birth    DATE,
    driving_license VARCHAR(45) unique,
    phone          VARCHAR(20),
    register_date   DATE
    );

CREATE TABLE IF NOT EXISTS users
(
    id               BIGSERIAL PRIMARY KEY,
    login            VARCHAR(45) not null unique,
    password         VARCHAR(45) not null,
    id_role           INT         not null,
    id_extra_users_data BIGINT,
    FOREIGN KEY (id_role) REFERENCES roles (id),
    FOREIGN KEY (id_extra_users_data) REFERENCES extra_users_data (id)
    );

CREATE TABLE IF NOT EXISTS cars_marks
(   id   SERIAL PRIMARY KEY,
    mark VARCHAR(45) unique
    );

CREATE TABLE IF NOT EXISTS cars_models
(
    id     SERIAL PRIMARY KEY,
    model  VARCHAR(45) not null,
    id_mark INT         not null,
    FOREIGN KEY (id_mark) REFERENCES cars_marks (id)
    );

CREATE TABLE IF NOT EXISTS cars
(
    id          SERIAL PRIMARY KEY,
    car_number   VARCHAR(20) not null unique,
    price       INT         not null,
    limitations VARCHAR(200),
    id_model     INT         not null,
    FOREIGN KEY (id_model) REFERENCES cars_models (id)
    );

CREATE TABLE IF NOT EXISTS orders
(
    id           BIGSERIAL PRIMARY KEY,
    start_date    DATE    not null,
    end_date      DATE    not null,
    status       BOOLEAN not null,
    id_car        INT     not null,
    id_user       BIGINT  not null,
    refuse_reason VARCHAR(200),
    admins_login  VARCHAR(45),
    FOREIGN KEY (id_car) REFERENCES cars (id),
    FOREIGN KEY (id_user) REFERENCES users (id)
    );
