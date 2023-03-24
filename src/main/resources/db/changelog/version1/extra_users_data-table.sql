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