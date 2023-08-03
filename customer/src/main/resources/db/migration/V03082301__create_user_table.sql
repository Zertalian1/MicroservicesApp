CREATE TABLE IF NOT EXISTS customers
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name VARCHAR,
    last_name  VARCHAR,
    email      VARCHAR UNIQUE
);

INSERT INTO
    customers(first_name, last_name, email)
VALUES
    ('test_first_name', 'test_last_name', 'test_email')