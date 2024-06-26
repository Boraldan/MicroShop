CREATE TABLE IF NOT EXISTS  t_storage (
                                          id UUID PRIMARY KEY,
                         item_id UUID,
                         quantity INT,
                         reserve INT
);

CREATE TABLE IF NOT EXISTS  car_storage (
                                            id UUID PRIMARY KEY,
                             item_id UUID,
                             quantity INT,
                             reserve INT
);

CREATE TABLE IF NOT EXISTS  bike_storage (
    id UUID PRIMARY KEY,
                                            item_id UUID,
                                            quantity INT,
                                            reserve INT
);

CREATE TABLE IF NOT EXISTS  car_wheel_storage (
                                                  id UUID PRIMARY KEY,
                                             item_id UUID,
                                             quantity INT,
                                             reserve INT
);

CREATE TABLE IF NOT EXISTS  bike_wheel_storage (
                                                   id UUID PRIMARY KEY,
                                             item_id UUID,
                                             quantity INT,
                                             reserve INT
);