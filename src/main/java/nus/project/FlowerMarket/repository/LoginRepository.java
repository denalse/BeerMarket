package nus.project.FlowerMarket.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import static nus.project.FlowerMarket.repository.Queries.*;

@Repository
public class LoginRepository {
    
    @Autowired
    private JdbcTemplate template;

    public String getLogin(String username) {

        final SqlRowSet rs = template.queryForRowSet(
            SQL_SELECT_USER, username);

            if(!rs.next())
                return null;
        //return password because it is important 
        return rs.getString("password");

    }
}
