package nus.project.FlowerMarket.repository;

import java.sql.ResultSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import nus.project.FlowerMarket.model.Flower;
import static nus.project.FlowerMarket.repository.Queries.*;

@Repository
public class PostRepository {

    @Autowired
    private JdbcTemplate temp;

    public Optional<Flower> getPostById(Integer postId) {
        return temp.query (
            SQL_GET_POST_BY_POST_ID,
            (ResultSet rs) -> {
                if(!rs.next())
                    return Optional.empty();

                final Flower post = Flower.populate(rs);
                return Optional.of(post);
            } , postId);
    }

    //insert post data to the social media table
    public Integer insertPost(Flower post) {
       Integer updCount =  temp.update(SQL_INSERT_POST, 
                                post.getImage(),
                                post.getComment(),
                                post.getPoster(),
                                post.getImageType());
            return updCount;

    }
    
}
