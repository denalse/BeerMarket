package nus.project.BeerMarket.repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import nus.project.BeerMarket.model.Post;
import static nus.project.BeerMarket.repository.Queries.*;

@Repository
public class PostRepository {

    @Autowired
    private JdbcTemplate temp;

    public Optional<Post> getPostById(Integer postId) {
        return temp.query(
            SQL_GET_POST_BY_POST_ID,
            
            (ResultSet rs) -> {
                //if resultset is empty return empty
                if(!rs.next())
                    return Optional.empty();

                final Post post = Post.populate(rs);
                return Optional.of(post);
            } , postId);
    }

    //insert post data to the flower post table
    public Integer insertPost(Post post) {
       
        Integer updCount =  temp.update(SQL_INSERT_POST, 
                                post.getImage(),
                                post.getComment(),
                                post.getPoster(),
                                post.getImageType());
        return updCount;

    }

    //get image data from the flower post table
    public ArrayList<Post> getAllPost() {
        ArrayList<Post> getAllPost = new ArrayList<>();
        return temp.query(
            SQL_GET_ALL_POST,
            (ResultSet rs) -> {
                //Checking the rs for the first row and 
                //after and add the post to the arraylist
                while (rs.next() ) {
                    getAllPost.add(Post.show(rs));   
                }
                return getAllPost;
            });

    }
    
}
