CREATE
DATABASE test;

USE
test;

DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`
(
    `product_id`       int NOT NULL AUTO_INCREMENT,
    `product_name`     varchar(100) DEFAULT NULL,
    `product_quantity` int          DEFAULT NULL,
    `product_price`    float(7, 2
) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
);

INSERT INTO `products`
VALUES (1, 'Wireless Mouse', 20, 19.99),
       (2, 'Mechanical Keyboard', 30, 49.99),
       (3, 'HD Monitor', 20, 129.99),
       (4, 'USB-C Charger', 100, 14.99),
       (5, 'Webcam 1080p', 25, 39.99),
       (6, 'Bluetooth Speaker', 40, 24.99),
       (7, 'Laptop Stand', 35, 29.99),
       (8, 'Gaming Headset', 15, 59.99),
       (9, 'External Hard Drive 1TB', 10, 79.99),
       (10, 'LED Desk Lamp', 60, 22.50),
       (11, 'Torch', 43, 200.00),
       (12, 'Laptop', 10, 60000.00),
       (13, 'Television', 90, 9000.00),
       (14, 'pen', 100, 35.00),
       (15, 'ssd', 3, 12000.00);

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `u_id`          int NOT NULL AUTO_INCREMENT,
    `user_password` varchar(100) DEFAULT NULL,
    `user_email`    varchar(100) DEFAULT NULL,
    `user_dp`       longblob,
    PRIMARY KEY (`u_id`)
);

