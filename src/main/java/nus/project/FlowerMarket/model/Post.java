package nus.project.FlowerMarket.model;

import java.beans.Transient;
import java.sql.ResultSet;
import java.sql.SQLException;

//@Entity
public class Post {
    private String poster;
    private String comment;
    private String imageType;
    private byte[] image;
    private Integer postId;

    public String getPoster() {
        return poster;
    }
    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImageType() {
        return imageType;
    }
    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }

    public Integer getPostId() {
        return postId;
    }
    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    //serialize means able to convert state to byte stream, 
    // (row set) cannot be serialized while (result set) can
    //do not edit, if not error
    public static Post populate(ResultSet rs) throws SQLException {
        final Post post  = new Post();
        post.setImage(rs.getBytes("photo"));
        post.setComment(rs.getString("comment"));
        post.setPoster(rs.getString("poster"));
        post.setImageType(rs.getString("mediatype"));
        post.setPostId(rs.getInt("post_id"));
        
        return post;
    }

    public static Post save(ResultSet rs) throws SQLException {
        final Post savedpost = new Post();
        savedpost.setImage(rs.getBytes("photo"));
        savedpost.setComment(rs.getString("comment"));
        savedpost.setPoster(rs.getString("poster"));
        savedpost.setImageType(rs.getString("mediatype"));
        savedpost.setPostId(rs.getInt("post_id"));
        
        return savedpost;
    }

    //transient keyword is used to denote that a field 
    //is not to be serialized
    @Transient
    public String getPhotosImagePath() {
        if (image == null || postId == null) return null;
         
        return "/market-photos/" + postId + "/" + image;
    }
}
