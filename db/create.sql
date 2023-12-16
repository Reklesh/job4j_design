create table christmas_tree(
	id serial primary key,
	price money,
    live bool,
    height decimal(1,1)
);
insert into christmas_tree(price, live, height) values(5000, 'true', 0.9);
select * from christmas_tree;
update christmas_tree set price = 3000;
select * from christmas_tree;
delete from christmas_tree;
select * from christmas_tree;