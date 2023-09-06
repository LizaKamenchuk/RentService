ALTER TABLE cars ADD Column id_extra_data_car INT;
ALTER TABLE  cars ADD CONSTRAINT id_extra_data_car FOREIGN KEY (id_extra_data_car) REFERENCES extra_data_car(id)