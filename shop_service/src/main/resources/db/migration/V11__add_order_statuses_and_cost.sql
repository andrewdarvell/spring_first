ALTER TABLE shop_order ADD COLUMN cost NUMERIC;

CREATE TABLE order_status(
                        id serial primary key,
                        title text
);

INSERT INTO order_status(title) values ('Принят');
INSERT INTO order_status(title) values ('В работе');
INSERT INTO order_status(title) values ('Завершён');

ALTER TABLE shop_order ADD COLUMN status_id BIGINT;
ALTER TABLE shop_order ADD CONSTRAINT status_fk FOREIGN KEY (status_id) REFERENCES order_status (id);

UPDATE shop_order SET status_id = (SELECT id FROM order_status WHERE title = 'Принят');
