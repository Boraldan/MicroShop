INSERT INTO coupon (name, discount, valid, creat_at) VALUES
 ('FirstCoupon', 10, true, NOW()),
 ('SecondCoupon', 20, true, NOW()),
 ('ThirdCoupon', 30, false, NOW());

INSERT INTO t_order (customer_id, seller_id, coupon_id, sub_total, total, creat_at, status, pay) VALUES
(1, 1, 1, 50.00, 45.00, NOW(), 'CREATED', 'PAID'),
(2, 1, NULL, 100.00, 100.00, NOW(), 'CANCELLED', 'PAID'),
(3, 1, 2, 75.00, 70.00, NOW(), 'CREATED', 'PART_PAID');

INSERT INTO order_item (item_id, order_id, quantity, price_item, price) VALUES
(1, 1, 2, 25.00, 50.00),
(2, 1, 1, 20.00, 20.00),
(3, 2, 3, 10.00, 30.00);