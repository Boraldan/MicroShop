CREATE TABLE IF NOT EXISTS  t_storage (
                                          id UUID PRIMARY KEY,
--                          item_id BIGINT,
                         quantity BIGINT,
                         reserve BIGINT
);

-- Создание таблицы car_storage
CREATE TABLE IF NOT EXISTS  car_storage (
                                            id UUID PRIMARY KEY,
--                              item_id BIGINT,
                             quantity BIGINT,
                             reserve BIGINT
);

CREATE TABLE IF NOT EXISTS  bike_storage (
    id UUID PRIMARY KEY,
--                                             item_id BIGINT,
                                            quantity BIGINT,
                                            reserve BIGINT
);

CREATE TABLE IF NOT EXISTS  car_wheel_storage (
                                                  id UUID PRIMARY KEY,
--                                              item_id BIGINT,
                                             quantity BIGINT,
                                             reserve BIGINT
);

CREATE TABLE IF NOT EXISTS  bike_wheel_storage (
                                                   id UUID PRIMARY KEY,
--                                              item_id BIGINT,
                                             quantity BIGINT,
                                             reserve BIGINT
);