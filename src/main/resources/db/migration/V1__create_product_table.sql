CREATE TABLE products (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          unit_price DECIMAL(10, 2) NOT NULL,
                          stock_quantity INT NOT NULL
);