drop database if exists mybeermarket;

create database mybeermarket;

use mybeermarket;

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
create table user (
    user_id int not null auto_increment,
    username varchar(32) not null,
    password varchar(32) not null,
    name varchar(64),
    post_id int,

    primary key(user_id),

    constraint fk_post_id
    foreign key(post_id)
    references post(post_id)
);
