CREATE TABLE stock_movements (
                                 id BIGSERIAL PRIMARY KEY,
                                 product_id BIGINT NOT NULL,
                                 quantity INT NOT NULL,
                                 movement_type VARCHAR(50) NOT NULL,
                                 movement_time TIMESTAMP NOT NULL,
                                 CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES products(id)
);