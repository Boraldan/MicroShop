-- CREATE TABLE IF NOT EXISTS  storage (
--                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
--                          category_name VARCHAR
-- );

-- Создание таблицы car_storage
CREATE TABLE IF NOT EXISTS  car_storage (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             item_id BIGINT,
                             quantity BIGINT,
                             reserve BIGINT
);

CREATE TABLE IF NOT EXISTS  bike_storage (
                                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                            item_id BIGINT,
                                            quantity BIGINT,
                                            reserve BIGINT
);