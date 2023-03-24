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