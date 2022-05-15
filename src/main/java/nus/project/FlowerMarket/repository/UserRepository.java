package nus.project.FlowerMarket.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import nus.project.FlowerMarket.model.User;

import static nus.project.FlowerMarket.repository.Queries.*;

import java.util.Optional;

@Repository
public class UserRepository {
    
    @Autowired
    private JdbcTemplate temp;


    public Optional<User> getUserByUsername(String username) {
        
        final SqlRowSet rs = temp.queryForRowSet(SQL_GET_USER_BY_USERNAME, username);
                
            if(!rs.next())
                return Optional.empty();

            final User user = User.create(rs);
            return Optional.of(user);
    }

    //insert user data to the user table, save
    public boolean insertUser(User user) {
        
        final Integer added =  temp.update(SQL_INSERT_USER, 
                                    user.getUsername(),
                                    user.getPassword());
                              
                return added > 0;
    
    }
    //user authetication, else create new login for user
    public User authenticate(String username, String password) {

        final SqlRowSet rs = temp.queryForRowSet(
            SQL_AUTHENTICATE_USER, username, password);

            if(!rs.next())
                return null;
        // return password because it is important 
        // return rs.getInt("user_id");
            return User.create(rs);
    }
}
