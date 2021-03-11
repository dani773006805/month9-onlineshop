create table product_category
(
    id   bigint generated by default as identity
        constraint product_category_pkey
            primary key,
    category_name varchar(255)
);

alter table product_category
    owner to root;

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
    owner to root;

create table users
(
    id       bigint generated by default as identity
        constraint users_pkey
            primary key,
    email    varchar(255),
    password varchar(255)
);

alter table users
    owner to root;

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
    owner to root;

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
    owner to root;

create table product_order
(
    product_id bigint not null
        constraint fk3q4uv0x8kbl98gs6rhjvw60x3
            references products,
    order_id   bigint not null
        constraint fkjwsik4uvq2sdqtb7x6h1o5f0v
            references orders
);

alter table product_order
    owner to root;

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
    owner to root;

create table shopping_cart
(
    id      bigint generated by default as identity
        constraint shopping_cart_pkey
            primary key,
    user_id bigint
        constraint fkr1irjigmqcpfrvggavnr7vjyv
            references users
);

alter table shopping_cart
    owner to root;

create table product_shop_cart
(
    cart_id    bigint not null
        constraint fk6qnnkr57iapixkof73s5jhuu1
            references shopping_cart,
    product_id bigint not null
        constraint fkccwfiv2lke4cmtijoejuo08m6
            references products
);

alter table product_shop_cart
    owner to root;


insert into product_category(category_name)
values('book');
insert into product_category(category_name)
values('coffee');

insert into products(active,description,image_url,name,unit_price,units_in_stock,
                     category_id)
values(true,'release year:2015','src/main/resources/static/images/image1.png',
       'spring in action',45.98,234,1);

insert into products(active,description,image_url,name,unit_price,units_in_stock,
                     category_id)
values(true,'release year:2019','src/main/resources/static/images/image2.png',
       'spring boot in action',45.98,234,1);