CREATE TABLE orders (
    id BIGINT PRIMARY KEY,
    description VARCHAR(255),
    value DOUBLE PRECISION,
    user_id BIGINT,
    delivery_person_id BIGINT,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);
