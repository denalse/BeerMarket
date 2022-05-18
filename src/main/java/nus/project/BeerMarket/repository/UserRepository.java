package nus.project.BeerMarket.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import nus.project.BeerMarket.model.User;

import static nus.project.BeerMarket.repository.Queries.*;

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
    

    public int countUsersByNameAndPassword(String username, String password) {
            
        try{
            SqlRowSet rs = temp.queryForRowSet(SQL_AUTHENTICATE_USER, username, password);
                
            if (!rs.next()) {
                //  x = rs.getString(1);
                //  System.out.print(x);
                    return 0;
                }else{
                    return rs.getInt("user_count");
                }

            } catch (Exception ex) {
                    System.out.println(ex);
                    
                
            }
            return 0;
            //return rs.getInt("user_count");
        }

}
