CREATE TABLE IF NOT EXISTS roles
(
    id   SERIAL,
    role VARCHAR(45) not null
);
CREATE TABLE IF NOT EXISTS extraUsersData
(
    idPassport     VARCHAR(20) PRIMARY KEY,
    name           VARCHAR(45),
    lastname       VARCHAR(45),
    dateOfBirth    DATE,
    drivingLicense VARCHAR(45),
    phone          VARCHAR(10),
    registerDate   DATE
);

CREATE TABLE IF NOT EXISTS users
(
    id               BIGSERIAL PRIMARY KEY,
    login            VARCHAR(45) not null,
    password         VARCHAR(45) not null,
    role             INT         not null,
    idExtraUsersData BIGINT,
    FOREIGN KEY (role) REFERENCES roles (id),
    FOREIGN KEY (idExtraUsersData) REFERENCES extraUsersData (idPassport)
);
CREATE TABLE IF NOT EXISTS carsMarks
(
    id   SERIAL PRIMARY KEY,
    mark VARCHAR(45)
);
CREATE TABLE IF NOT EXISTS carsModels
(
    id     SERIAL PRIMARY KEY,
    model  VARCHAR(45) not null,
    idMark INT         not null,
    FOREIGN KEY (idMark) REFERENCES carsMarks (id)
);

CREATE TABLE IF NOT EXISTS cars
(
    id          SERIAL PRIMARY KEY,
    carNumber   VARCHAR(20) not null,
    price       INT         not null,
    limitations VARCHAR(200),
    idImage     INT,
    idModel     INT         not null,
    FOREIGN KEY (idModel) REFERENCES carsModels (id)
);
CREATE TABLE IF NOT EXISTS requests
(
    id           BIGSERIAL PRIMARY KEY,
    startDate    DATE    not null,
    endDate      DATE    not null,
    status       BOOLEAN not null,
    idCar        INT     not null,
    idUser       BIGINT     not null,
    refuseReason VARCHAR(200),
    FOREIGN KEY (idCar) REFERENCES cars (id),
    FOREIGN KEY (idUser) REFERENCES users (id)
);
CREATE TABLE IF NOT EXISTS orders
(
    id        BIGSERIAL PRIMARY KEY,
    idRequest BIGINT not null,
    idAdmin   BIGINT not null,
    FOREIGN KEY (idRequest) REFERENCES requests (id),
    FOREIGN KEY (idAdmin) REFERENCES users (id)
);

INSERT INTO roles(role)
VALUES ('admin'),
       ('user');
INSERT INTO users (login, password, role)
VALUES ('admin', 'admin1', 1),
       ('user', 'user1', 2);
