CREATE TABLE vehicle (id SERIAL NOT NULL, license_plate varchar(255) NOT NULL, PRIMARY KEY (id));
CREATE TABLE kilometrage (id SERIAL NOT NULL, "date" date NOT NULL, "start" int4 NOT NULL, "end" int4 NOT NULL, vehicle_id int4 NOT NULL, PRIMARY KEY (id));
CREATE TABLE app_user (id SERIAL NOT NULL, username varchar(50) NOT NULL, password varchar(30) NOT NULL, PRIMARY KEY (id));
CREATE TABLE configuration (id SERIAL NOT NULL, "key" varchar(255) NOT NULL, "value" varchar(255) NOT NULL, PRIMARY KEY (id));
CREATE TABLE user_token (id SERIAL NOT NULL, token varchar(255) NOT NULL, app_user_id int4 NOT NULL, expiration DATE NOT NULL, PRIMARY KEY (id));
CREATE INDEX vehicle_id ON vehicle (id);
CREATE INDEX kilometrage_id ON kilometrage (id);
CREATE INDEX app_user_id ON app_user (id);
ALTER TABLE kilometrage ADD CONSTRAINT FKkilometrag760451 FOREIGN KEY (vehicle_id) REFERENCES vehicle (id);
ALTER TABLE user_token ADD CONSTRAINT FKuser_token760451 FOREIGN KEY (app_user_id) REFERENCES app_user (id);

