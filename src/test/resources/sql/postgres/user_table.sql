create table if not exists user_table
(

    user_id    bigint unsigned auto_increment
        primary key,
    ldap_login varchar(25),
    name       varchar(25),
    surname    varchar(25)
);

insert into user_table(ldap_login, name, surname) values ('post1', 'post_user1', 'post_sur1');
insert into user_table(ldap_login, name, surname) values ('post2', 'post_user2', 'post_sur2');
insert into user_table(ldap_login, name, surname) values ('post3', 'post_user3', 'post_sur3');
insert into user_table(ldap_login, name, surname) values ('post4', 'post_user4', 'post_sur4');