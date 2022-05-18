package nus.project.BeerMarket.model;

import java.sql.SQLException;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class User {
    
    private String username;
    private String password;
    private Integer userId;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public static User create(SqlRowSet rs) {
        User user = new User();
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setUserId(rs.getInt("user_id"));
        return user;
    }

    
}
