use testdb;

create table users
(
    user_id    bigint unsigned auto_increment
        primary key,
    login      varchar(25) null,
    first_name varchar(25) null,
    last_name  varchar(25) null,
    constraint user_id
        unique (user_id)
);

insert into users(login, first_name, last_name) values ('login1', 'user1', 'userpass1');
insert into users(login, first_name, last_name) values ('login2', 'user2', 'userpass2');
insert into users(login, first_name, last_name) values ('login3', 'user3', 'userpass3');
insert into users(login, first_name, last_name) values ('login4', 'user4', 'userpass4');
insert into users(login, first_name, last_name) values ('login5', 'user5', 'userpas5');
insert into users(login, first_name, last_name) values ('login6', 'user6', 'userpass6');
insert into users(login, first_name, last_name) values ('login7', 'user7', 'userpass7');

