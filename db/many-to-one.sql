create table tree(
    id serial primary key,
    name varchar(255)
);

create table fetus(
    id serial primary key,
    name varchar(255),
    tree_id int references tree(id)
);

insert into tree(name) values ('tangerine_tree');
insert into fetus(name, tree_id) values ('tangerine', 1);

select * from fetus;

select * from tree where id in (select tree_id from fetus);