begin transaction;

select * from autos;

select * from autos;

select * from autos;

commit;

begin transaction isolation level repeatable read;

select * from autos;

update autos set price = 3500000 where name = 'Omoda';

select * from autos;

commit;

begin transaction isolation level repeatable read;

update autos set price = 3600000 where name = 'Omoda';

commit;

begin transaction isolation level serializable;

select sum(count) from autos;

update autos set count = 12 where name = 'Omoda';

commit;