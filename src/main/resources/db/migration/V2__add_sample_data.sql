INSERT INTO category (id, name, parent_id) VALUES
    (1, 'Electronics', NULL),
    (2, 'Computers', 1),
    (3, 'Laptops', 2);

INSERT INTO product (name, description, price, category_id, sku) VALUES
    ('MacBook Pro III', 'Apple Laptop', 4999.99, 3, 'APPLE-MBP-003'),
    ('Ultimate Version', 'Windows Laptop', 3499.99, 3, 'WINDOWS-V15');