package nus.project.FlowerMarket.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin {
    
    private int imageId;
    private String image_src;

    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImage_src() {
        return image_src;
    }
    public void setImage_src(String image_src) {
        this.image_src = image_src;
    }

    public static Admin populate(ResultSet rs) throws SQLException {
        final Admin image  = new Admin();
        image.setImage_src(rs.getString("image_src"));
        image.setImageId(rs.getInt("image_id"));
        
        return image;
    }
    
}
