CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create users table
CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE orders (
    id UUID PRIMARY KEY,
    description VARCHAR(255),
    value DOUBLE PRECISION,
    user_id UUID,
    delivery_person_name VARCHAR(255),
    delivery_person_phone VARCHAR(255),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);

CREATE INDEX idx_orders_user_id ON orders(user_id);
CREATE INDEX idx_users_email ON users(email);
