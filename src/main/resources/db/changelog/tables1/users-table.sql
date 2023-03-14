CREATE TABLE IF NOT EXISTS users
(
    id               BIGSERIAL PRIMARY KEY,
    login            VARCHAR(45) not null,
    password         VARCHAR(45) not null,
    id_role           INT         not null,
    id_extra_users_data BIGINT,
    FOREIGN KEY (id_role) REFERENCES roles (id),
    FOREIGN KEY (id_extra_users_data) REFERENCES extra_users_data (id)
);