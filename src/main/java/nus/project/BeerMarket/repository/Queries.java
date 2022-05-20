package nus.project.BeerMarket.repository;

public interface Queries {
    
    //POSTING, Save post into databse
    public static final String SQL_INSERT_POST =
    "insert into post(photo, comment, poster, mediatype) values (?, ?, ?, ?)";

    public static final String SQL_GET_POST_BY_POST_ID =
    "select photo, comment, poster, mediatype, post_id from post where post_id = ?";
    
    //Get post(image) from specific poster!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public static final String SQL_GET_POSTER_IMAGE =
    "select post_id from post where poster = ?";
     
    // "select count(*) as user_count from user where username='${username}' and password='${password}'";
    // "select * from user where username="${username}" and password="${password}"";
    //"select * from user where password = '....' and username = '...'";
}
