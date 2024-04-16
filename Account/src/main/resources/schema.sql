CREATE TABLE t_person
(
    id       UUID PRIMARY KEY,
    username VARCHAR NOT NULL,
    fio      VARCHAR,
    age      INTEGER,
    phone    BIGINT,
    email    VARCHAR NOT NULL,
    card     BIGINT
);

CREATE TABLE customer
(
    id      UUID PRIMARY KEY,
    cart_id UUID
);

CREATE TABLE admin_shop
(
    id UUID PRIMARY KEY
);

CREATE TABLE seller
(
    id    UUID PRIMARY KEY,
    phone BIGINT  NOT NULL,
    email VARCHAR NOT NULL,
    card  BIGINT
);

CREATE TABLE company
(
    id           UUID PRIMARY KEY REFERENCES seller (id),
    company_name VARCHAR
);

CREATE TABLE sole_trader
(
    id        UUID PRIMARY KEY REFERENCES seller (id),
    sole_name VARCHAR
);

CREATE TABLE coupon
(
    id          UUID PRIMARY KEY,
    coupon_name VARCHAR,
    discount    DOUBLE PRECISION,
    valid       BOOLEAN,
    creat_at    TIMESTAMP
);

CREATE TABLE t_order
(
    id           UUID PRIMARY KEY,
    coupon_id    UUID,
    sub_total    NUMERIC,
    total        NUMERIC,
    order_status VARCHAR,
    pay          VARCHAR,
    creat_at     TIMESTAMP,
    customer_id  UUID,
    seller_id    UUID,
    FOREIGN KEY (coupon_id) REFERENCES Coupon (id),
    FOREIGN KEY (customer_id) REFERENCES Customer (id),
    FOREIGN KEY (seller_id) REFERENCES Seller (id)
);

CREATE TABLE unit_order
(
    id         UUID PRIMARY KEY,
    item_id    BIGINT,
    item_title VARCHAR,
    quantity   INTEGER,
    price_item NUMERIC,
    price_unit NUMERIC,
    order_id   UUID,
    FOREIGN KEY (order_id) REFERENCES t_order (id)
);
