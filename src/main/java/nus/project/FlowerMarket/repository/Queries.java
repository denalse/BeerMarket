package nus.project.FlowerMarket.repository;

public interface Queries {
    
    //POSTING
    public static final String SQL_INSERT_POST =
    "insert into post(photo, comment, poster, mediatype) values (?, ?, ?, ?)";

    public static final String SQL_GET_POST_BY_POST_ID =
    "select photo, comment, poster, mediatype, post_id from post where post_id = ?";

    //LOGIN
    public static final String SQL_SELECT_USER = 
    "select * from user where username = ?";
}
