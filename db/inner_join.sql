create table tractor(
	id serial primary key,
    name varchar(255)
);

create table attachments(
    id serial primary key,
    name varchar(255),
    tractor_id int references tractor(id)
);

insert into tractor(name) values ('MTZ-80');
insert into tractor(name) values ('DT-75');
insert into tractor(name) values ('T-25');

insert into attachments(name, tractor_id) values ('plow', 2);
insert into attachments(name, tractor_id) values ('trailer', 1);
insert into attachments(name, tractor_id) values ('mower', 3);

select tr.name, at.name  
from attachments as at join tractor as tr on at.tractor_id = tr.id;

select tr.name as Трактор, at.name as Навеска 
from attachments as at join tractor as tr on at.tractor_id = tr.id;

select tr.name Трактор, at.name as "Навесное оборудование" 
from attachments at join tractor tr on at.tractor_id = tr.id;