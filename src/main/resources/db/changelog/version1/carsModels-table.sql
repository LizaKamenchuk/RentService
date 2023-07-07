CREATE TABLE IF NOT EXISTS cars_models
(
    id     SERIAL PRIMARY KEY,
    model  VARCHAR(45) not null,
    id_mark INT         not null,
    FOREIGN KEY (id_mark) REFERENCES cars_marks (id)
    );