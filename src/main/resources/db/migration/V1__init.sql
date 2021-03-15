create table product_category
(
    id            bigint generated by default as identity
        constraint product_category_pkey
            primary key,
    category_name varchar(255)
);

alter table product_category
    owner to postgres;

create table products
(
    id             bigint generated by default as identity
        constraint products_pkey
            primary key,
    active         boolean,
    description    varchar(255),
    image_url      varchar(255),
    name           varchar(255),
    unit_price     numeric(19, 2),
    units_in_stock integer,
    category_id    bigint
        constraint fk1bfbbw5vei53vhmynbdfxq50n
            references product_category
);

alter table products
    owner to postgres;

create table users
(
    id       bigint generated by default as identity
        constraint users_pkey
            primary key,
    email    varchar(255),
    password varchar(255)
);

alter table users
    owner to postgres;

create table card_details
(
    id              bigint generated by default as identity
        constraint card_details_pkey
            primary key,
    card_name       varchar(255),
    card_number     varchar(255),
    card_type       varchar(255),
    expiration_date date,
    security_code   varchar(255),
    user_id         bigint
        constraint fktfeaaq5bfhi2tlsrwm0m52him
            references users
);

alter table card_details
    owner to postgres;

create table orders
(
    id             bigint generated by default as identity
        constraint orders_pkey
            primary key,
    date_created   timestamp,
    total_price    numeric(19, 2),
    total_quantity integer,
    user_id        bigint
        constraint fk32ql8ubntj5uh44ph9659tiih
            references users
);

alter table orders
    owner to postgres;

create table order_item
(
    id          bigint generated by default as identity
        constraint order_item_pkey
            primary key,
    category_id bigint,
    description varchar(255),
    image_url   varchar(255),
    name        varchar(255),
    product_id  bigint,
    unit_price  numeric(19, 2),
    units       integer,
    order_id    bigint
        constraint fkt4dc2r9nbvbujrljv3e23iibt
            references orders
);

alter table order_item
    owner to postgres;

create table reviews
(
    id         bigint generated by default as identity
        constraint reviews_pkey
            primary key,
    date       timestamp,
    text       varchar(255),
    product_id bigint
        constraint fkpl51cejpw4gy5swfar8br9ngi
            references products,
    user_id    bigint
        constraint fkcgy7qjc1r99dp117y9en6lxye
            references users
);

alter table reviews
    owner to postgres;

create table shopping_cart
(
    id             bigint generated by default as identity
        constraint shopping_cart_pkey
            primary key,
    total_price    numeric(19, 2),
    total_quantity integer,
    user_id        bigint
        constraint fkr1irjigmqcpfrvggavnr7vjyv
            references users
);

alter table shopping_cart
    owner to postgres;

create table shopping_cart_item
(
    id               bigint generated by default as identity
        constraint shopping_cart_item_pkey
            primary key,
    category_id      bigint,
    description      varchar(255),
    image_url        varchar(255),
    name             varchar(255),
    productid        bigint,
    unit_price       numeric(19, 2),
    units            integer,
    shopping_cart_id bigint
        constraint fktaxfo8drwlxjtg1f1y9h4t5t2
            references shopping_cart
);

alter table shopping_cart_item
    owner to postgres;






insert into product_category
(category_name)
values ('book');

insert into products (active, description, image_url, name, unit_price, units_in_stock, category_id)
VALUES(true,'d','sdf','sdf',78,23,1);

insert into products (active, description, image_url, name, unit_price, units_in_stock, category_id)
VALUES(true,'d','sdf','sdf',75,23,1);

