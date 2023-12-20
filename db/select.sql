create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('whitefish', 36500, '1693-01-01');
insert into fauna(name, avg_age) values ('tiger', 7300);
insert into fauna(name, avg_age, discovery_date) values ('emu', 11000, '1800-01-01'); 
insert into fauna(name, avg_age, discovery_date) values ('girax', 5000, '2021-01-01');
insert into fauna(name, avg_age, discovery_date) values ('synapturanus', 1500, '2021-01-01');

select * from fauna where name like '%fish%';

select * from fauna where avg_age > 10000 and avg_age < 21000;

select * from fauna where discovery_date is null;

select * from fauna where discovery_date < '01.01.1950'; 