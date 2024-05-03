CREATE TABLE IF NOT EXISTS  t_category
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(50),
    description   VARCHAR(300)
);
CREATE TABLE IF NOT EXISTS t_item
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    title      VARCHAR(255),
    category_id  BIGINT REFERENCES t_category (id)
--     storage_id UUID
);

CREATE TABLE IF NOT EXISTS car
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(255),
    factory   VARCHAR(255),
    year_prod INTEGER,
    types     VARCHAR(255),
    fuel      VARCHAR(255)

--                      FOREIGN KEY (id) REFERENCES t_item(id)
);
CREATE TABLE IF NOT EXISTS bike
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(255),
    factory   VARCHAR(255),
    wheels    INTEGER,
    year_prod INTEGER,
    fuel      VARCHAR(255)
--                       storage_id BIGINT,
--                       FOREIGN KEY (id) REFERENCES t_item(id)
);

CREATE TABLE bike_wheel (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR,
    diameter DOUBLE,
    season VARCHAR
);

CREATE TABLE car_wheel (
   id BIGINT PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR,
   diameter DOUBLE,
   season VARCHAR
);


CREATE TABLE price (
                           id UUID PRIMARY KEY,
                           base_price DECIMAL(19, 2),
                           coefficient DOUBLE,
                           custom_price DECIMAL(19, 2),
                           item_id BIGINT REFERENCES t_item(id)
);


CREATE TABLE car_price (
                           id UUID PRIMARY KEY,
                           base_price DECIMAL(19, 2),
                           coefficient DOUBLE,
                           custom_price DECIMAL(19, 2),
                           item_id BIGINT REFERENCES t_item(id)
);


CREATE TABLE bike_price (
                            id UUID PRIMARY KEY,
                           base_price DECIMAL(19, 2),
                           coefficient DOUBLE,
                           custom_price DECIMAL(19, 2),
                           item_id BIGINT REFERENCES t_item(id)
);

CREATE TABLE car_wheel_price (
                                 id UUID PRIMARY KEY,
                           base_price DECIMAL(19, 2),
                           coefficient DOUBLE,
                           custom_price DECIMAL(19, 2),
                           item_id BIGINT REFERENCES t_item(id)
);


CREATE TABLE bike_wheel_price (
                                  id UUID PRIMARY KEY,
                            base_price DECIMAL(19, 2),
                            coefficient DOUBLE,
                            custom_price DECIMAL(19, 2),
                            item_id BIGINT REFERENCES t_item(id)
);




CREATE TABLE IF NOT EXISTS fly
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);