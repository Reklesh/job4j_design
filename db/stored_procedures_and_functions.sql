create table products (
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create
or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    END;
$$;

call insert_data('product_2', 'producer_2', 15, 32);

create
or replace procedure delete_data(d_id integer)
language 'plpgsql'
as $$
    BEGIN
        delete from products
		where id = d_id;
    END;
$$;

call delete_data(1);

ALTER SEQUENCE products_id_seq RESTART WITH 1;

create
or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
returns void
language 'plpgsql'
as
$$
    BEGIN
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    END;
$$;

select f_insert_data('product_1', 'producer_1', 25, 60);

create
or replace function f_delete_data(d_id integer)
returns varchar
language 'plpgsql'
as
$$
    declare
        result varchar;
    BEGIN
	    select into result name
        from products
        where id = d_id;
	    delete from products
		where id = d_id;
        return result;
    END;
$$;

select f_delete_data(1);