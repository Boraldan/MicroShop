
CREATE TABLE coupon (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255),
                        discount DOUBLE,
                        valid BOOLEAN,
                        creat_at TIMESTAMP
);

CREATE TABLE orders (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        customer_id BIGINT,
                        seller_id BIGINT,
                        coupon_id INT,
                        sub_total DECIMAL(10, 2),
                        total DECIMAL(10, 2),
                        creat_at TIMESTAMP,
                        status VARCHAR(255),
                        pay VARCHAR(255),
                        FOREIGN KEY (coupon_id) REFERENCES coupon(id)
);

CREATE TABLE order_item (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            item_id BIGINT,
                            order_id INT,
                            quantity INT,
                            price_item DECIMAL(10, 2),
                            price DECIMAL(10, 2),
                            FOREIGN KEY (order_id) REFERENCES orders(id)
);
