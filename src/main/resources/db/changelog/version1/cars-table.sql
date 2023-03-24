CREATE TABLE IF NOT EXISTS cars
(
    id          SERIAL PRIMARY KEY,
    car_number   VARCHAR(20) not null unique,
    price       INT         not null,
    limitations VARCHAR(200),
    id_image     INT,
    id_model     INT         not null,
    FOREIGN KEY (id_model) REFERENCES cars_models (id)
    );