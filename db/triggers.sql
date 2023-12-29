create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create
or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure tax();
	
insert into products (name, producer, count, price)
values ('product_1', 'producer_1', 5, 100);

alter table products disable trigger tax_trigger;

create
or replace function before_insert_tax()
    returns trigger as
$$
    BEGIN
        NEW.price = NEW.price + NEW.price * 0.1;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger before_insert_tax_trigger
    before insert
    on products
    for each row
    execute procedure before_insert_tax();

insert into products (name, producer, count, price)
values ('product_2', 'producer_2', 10, 100);

alter table products disable trigger before_insert_tax_trigger;

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create
or replace function insert_history_of_price()
    returns trigger as
$$
    BEGIN
        insert into history_of_price (name, price, date)
        values (NEW.name, NEW.price, current_date);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger insert_history_of_price_trigger
    after insert
    on products
    for each row
    execute procedure insert_history_of_price();
	
insert into products (name, producer, count, price)
values ('product_3', 'producer_3', 15, 150);