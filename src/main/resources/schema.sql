CREATE TABLE users (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nickname VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255),
    username VARCHAR(255),
    point BIGINT
);
