CREATE TABLE review
(
    id         serial primary key not null ,
    pluses_description  text,
    minuses_description text,
    rating              smallint,
    product_id          bigint,
    user_id             bigint,
    foreign key (product_id) references product (id),
    foreign key (user_id) references shop_user (id)
);

CREATE TABLE comment
(
    id         serial primary key not null ,
    value      text,
    product_id bigint,
    user_id    bigint,
    foreign key (product_id) references product (id),
    foreign key (user_id) references shop_user (id)
);

CREATE TABLE shop_order
(
    id         serial primary key not null ,
    user_id                  bigint,
    delivery_address         text,
    delivery_address_fias_id text,
    comment                  text,
    foreign key (user_id) references shop_user (id)

);

CREATE TABLE shop_order_order_item
(
    id         serial primary key not null ,
    product_id bigint,
    order_id   bigint,
    cost       numeric,
    foreign key (product_id) references product (id),
    foreign key (order_id) references shop_order (id)
)
