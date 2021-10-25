INSERT INTO dict_value_type (title)
VALUES ('string');
INSERT INTO dict_value_type (title)
VALUES ('number');

INSERT INTO product_type(title)
VALUES ('Стандартный продукт');

INSERT INTO product_type_dict(title, sort_order, product_type_id, dict_value_type_id)
VALUES ('Страна производства',
        10,
        (SELECT id FROM product_type LIMIT 1),
        (SELECT id FROM dict_value_type WHERE title = 'string')),
       ('Год изготовления',
        20,
        (SELECT id FROM product_type LIMIT 1),
        (SELECT id FROM dict_value_type WHERE title = 'number'))
;

UPDATE product SET product_type_id = (SELECT id FROM product_type LIMIT 1);



