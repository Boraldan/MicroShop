
CREATE TABLE cart (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      customer_id BIGINT,
                      coupon_id BIGINT,
                      sub_total DECIMAL(10, 2),
                      total DECIMAL(10, 2),
                      creat_at TIMESTAMP
);

CREATE TABLE cart_unit (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           cart_id BIGINT,
                           item_id BIGINT,
                           quantity INT,
                           price_item DECIMAL(10, 2),
                           price DECIMAL(10, 2),
                           FOREIGN KEY (cart_id) REFERENCES cart(id)
);

INSERT INTO cart (customer_id, coupon_id, sub_total, total, creat_at)
VALUES
    (1, NULL, 100.00, 90.00, now()),
    (2, 101, 75.50, 70.00, now()),
    (3, NULL, 200.00, 190.00, now());