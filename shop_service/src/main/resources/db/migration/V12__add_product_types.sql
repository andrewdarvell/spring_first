

CREATE TABLE product_type
(
    id    serial primary key,
    title text
);

CREATE TABLE dict_value_type
(
    id    serial primary key,
    title text
);

CREATE TABLE product_type_dict
(
    id                 serial primary key,
    title              text,
    sort_order         numeric,
    product_type_id    bigint,
    dict_value_type_id bigint,
    foreign key (product_type_id) references product_type (id),
    foreign key (dict_value_type_id) references dict_value_type (id)
);

CREATE TABLE product_type_value
(
    id                   serial primary key,
    product_id           bigint,
    product_type_dict_id bigint,
    value                text,
    foreign key (product_id) references product (id)
);

ALTER TABLE product
    ADD COLUMN product_type_id bigint;

ALTER TABLE product
    ADD CONSTRAINT product_type_fk FOREIGN KEY (product_type_id) REFERENCES product_type (id);
