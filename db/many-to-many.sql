create table music_group(
    id serial primary key,
    name varchar(255)
);
 
create table song(
    id serial primary key,
    name varchar(255)
);
 
create table music_group_song(
    id serial primary key,
    music_group_id int references music_group(id),
    song_id int references song(id)
);

insert into music_group(name) values ('Imminence');
insert into music_group(name) values ('Architects');

insert into song(name) values ('Infectious');
insert into song(name) values ('Animals');

insert into music_group_song(music_group_id, song_id) values (1, 1);
insert into music_group_song(music_group_id, song_id) values (1, 2);
insert into music_group_song(music_group_id, song_id) values (2, 1);
insert into music_group_song(music_group_id, song_id) values (2, 2);

select * from music_group_song;