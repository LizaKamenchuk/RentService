CREATE TABLE IF NOT EXISTS transmission
(
    id SERIAL PRIMARY KEY,
    transmission_type VARCHAR(45)
);

CREATE TABLE IF NOT EXISTS fuel
(
    id SERIAL PRIMARY KEY,
    fuel_type  VARCHAR(45)
);

CREATE TABLE IF NOT EXISTS class
(
    id SERIAL PRIMARY KEY,
    class_type VARCHAR(45)
);


CREATE TABLE IF NOT EXISTS extra_data_car
(
    id SERIAL PRIMARY KEY,
    manufacture_year INT,
    fuel_consumption FLOAT,
    transmission_id INT,
    fuel_id INT,
    class_id INT,
    FOREIGN KEY (transmission_id) REFERENCES transmission (id),
    FOREIGN KEY (fuel_id) REFERENCES fuel (id),
    FOREIGN KEY (class_id) REFERENCES class (id)
);



