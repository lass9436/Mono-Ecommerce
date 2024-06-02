INSERT INTO users (username, password, nickname, role, point)
VALUES ('username1', '$2a$10$..k52ypcmQn/bFasGMmiWe3Cvi/Hk.Tl7C.4Adi72/HT3jkQYOfR6', 'nickname1', 'USER', 100000),
       ('username2', '$2a$10$o3TkcPkjyzAzOnNAFCnGc.zuluHL91zX9ekE4ZWuKQmQirp2iR31q', 'nickname2', 'USER', 50000),
       ('username3', '$2a$10$aYr10cgx6sQAmGj4GGdo.OISDYc2g7EqU8FtdmMJKDQlqd1.ZFCPO', 'nickname3', 'USER', 25000);

INSERT INTO item (name, price, quantity)
VALUES ('Item 1', 1000, 10),
       ('Item 2', 1500, 20),
       ('Item 3', 2000, 15),
       ('Item 4', 2500, 30),
       ('Item 5', 3000, 25),
       ('Item 6', 3500, 5),
       ('Item 7', 4000, 12),
       ('Item 8', 4500, 8),
       ('Item 9', 5000, 17),
       ('Item 10', 5500, 22);

-- 주문 데이터 추가
INSERT INTO orders (order_date, status, user_id) VALUES
    ('2024-06-01 10:00:00', 'ORDERED', 1),
    ('2024-06-02 11:00:00', 'SHIPPING', 2),
    ('2024-06-03 12:00:00', 'DELIVERED', 3);

-- 주문 상품 데이터 추가
INSERT INTO order_item (order_id, item_id, quantity, total_price) VALUES
    (1, 1, 2, 2000),
    (1, 2, 3, 4500),
    (1, 3, 1, 1500),
    (2, 4, 1, 2500),
    (2, 5, 2, 6000),
    (2, 6, 1, 3500),
    (3, 7, 3, 12000),
    (3, 8, 2, 9000),
    (3, 9, 1, 5000);


