drop database if exists myflowermarket;

create database myflowermarket;

use myflowermarket;

-- post photo to database digital ocean
create table post (
    post_id int not null auto_increment,
    photo mediumblob,
    comment mediumtext,
    poster varchar(64),
    mediatype varchar(256),
    
    primary key(post_id)
);

-- register user to database digital ocean
create table register (
    user_id int not null auto_increment,
    username varchar(32) not null,
    password varchar(32) not null,

    primary key(user_id)
)