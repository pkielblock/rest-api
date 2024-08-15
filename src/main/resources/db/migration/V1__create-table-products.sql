CREATE TABLE tb_products (
    id uuid PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    value DECIMAL(38, 2) NOT NULL
);