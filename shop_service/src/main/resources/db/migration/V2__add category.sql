CREATE TABLE category(
    id serial primary key,
    title text
);

ALTER TABLE product ADD COLUMN category_id BIGINT;
ALTER TABLE product ADD CONSTRAINT product_category FOREIGN KEY (category_id) REFERENCES category (id);
