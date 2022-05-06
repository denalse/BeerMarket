drop database if exists myflowermarket;

create database myflowermarket;

use myflowermarket;

create table market (
    order_id int not null auto_increment,
    photo mediumblob,
    comment mediumtext,
    poster varchar(64),
    mediatype varchar(256),
    
    primary key(order_id)
);