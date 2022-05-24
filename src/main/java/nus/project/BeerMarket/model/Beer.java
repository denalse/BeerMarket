package nus.project.BeerMarket.model;

import java.util.ArrayList;
import java.util.List;

public class Beer {
    
    private String imageUrl;
    private String name;
    private String description;
    private String tips;
    private Integer searchId;

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getTips() {
        return tips;
    }
    public void setTips(String tips) {
        this.tips = tips;
    }    
    

    public Integer getSearchId() {
        return searchId;
    }
    public void setSearchId(Integer searchId) {
        this.searchId = searchId;
    }

    public static List<Beer> beer() {
        ArrayList<Beer> list= new ArrayList<Beer>();
        
        Beer beer = new Beer();
        beer.getImageUrl();
        beer.getName();
        beer.getDescription();
        beer.getTips();

        return list;
    }
        
}
