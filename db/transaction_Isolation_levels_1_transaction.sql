create table autos (
    id    serial primary key,
    name  varchar(50),
    count integer default 0,
    price integer
);

insert into autos (name, count, price) VALUES ('Jetour', 5, 2000000);
insert into autos (name, count, price) VALUES ('Omoda', 10, 3000000);
insert into autos (name, count, price) VALUES ('Exeed', 15, 4000000);

begin transaction;

select * from autos;

insert into autos (name, count, price) VALUES ('Jaecoo', 20, 5000000);

delete from autos where price = 2000000;

update autos set price = 3500000 where name = 'Omoda';

select * from autos;

commit;

delete from autos;
ALTER SEQUENCE autos_id_seq RESTART WITH 1;

insert into autos (name, count, price) VALUES ('Jetour', 5, 2000000);
insert into autos (name, count, price) VALUES ('Omoda', 10, 3000000);
insert into autos (name, count, price) VALUES ('Exeed', 15, 4000000);

begin transaction isolation level repeatable read;

select * from autos;

insert into autos (name, count, price) VALUES ('Jaecoo', 20, 5000000);

delete from autos where price = 2000000;

update autos set price = 3500000 where name = 'Omoda';

ROLLBACK;

begin transaction isolation level repeatable read;

insert into autos (name, count, price) VALUES ('Jaecoo', 20, 5000000);

delete from autos where price = 2000000;

update autos set price = 3600000 where name = 'Omoda';

commit;

delete from autos;
ALTER SEQUENCE autos_id_seq RESTART WITH 1;

insert into autos (name, count, price) VALUES ('Jetour', 5, 2000000);
insert into autos (name, count, price) VALUES ('Omoda', 10, 3000000);
insert into autos (name, count, price) VALUES ('Exeed', 15, 4000000);

begin transaction isolation level serializable;

select sum(count) from autos;

update autos set count = 8 where name = 'Jetour';

commit;