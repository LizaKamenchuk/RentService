CREATE TABLE IF NOT EXISTS roles
(
    id   SERIAL PRIMARY KEY,
    role VARCHAR(45) not null unique
    );