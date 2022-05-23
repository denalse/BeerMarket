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

