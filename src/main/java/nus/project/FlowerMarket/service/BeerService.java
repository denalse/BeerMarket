package nus.project.FlowerMarket.service;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class BeerService {

    private Logger logger = LoggerFactory.getLogger(BeerService.class);

    private final String url = "https://api.punkapi.com/v2/beers/random";
    
    // public List<String> getBeer(Integer searchId) {
    //     return getBeer(searchId, "name", "description", "tips");
    // }

    
    public ArrayList<String> getBeer(Integer searchId) {//, String name, String description, String tips) {

        // String url = UriComponentsBuilder.fromUriString(BEER_SEARCH)
        // .queryParam("id", searchId)
        // .queryParam("name", name)
        // .queryParam("description", description)
        // .queryParam("brewers_tips", tips)
        // .toUriString();
    
        RequestEntity<Void> req = RequestEntity
            .get(url)
            .accept(MediaType.APPLICATION_JSON)
            .build();
    
        
        RestTemplate template = new RestTemplate();
            ///String globalImg="test";
            JsonObject object = null;
            
            ResponseEntity<String> resp = template.exchange(req, String.class);
            try (InputStream is = new ByteArrayInputStream(resp.getBody().getBytes())) {
                JsonReader reader = Json.createReader(is);
                object = reader.readObject();
            
            System.out.println(object.getJsonObject("id").getJsonObject("name").getJsonObject("description").getJsonObject("brewers_tips").getJsonString("image_url"));
            String image = object.getJsonObject("id")
                .getJsonObject("name") //key
                .getJsonObject("description") //key
                .getJsonObject("brewers_tips") //key
                .getString("image_url"); //value
                System.out.printf("\r\n img>>> %s\n", image);

            ArrayList<String> beerUrl = new ArrayList<String>();

                        int j = 26; //Number of id limit to 26
                        for (int i=0; i<j; i++) {

                            //JsonObject q = array.getJsonObject(i);
                                //String image = object.getString("image_url");

                            System.out.printf("\r\r image>>> %s\n", image);

                            beerUrl.add(image);
                            System.out.println("URL >>>>>" + beerUrl);
                                                    
                            logger.info("\r\n Image >>> " + image);
                        }
                            
                            
                        return beerUrl;

            } catch(Exception ex){

                ex.printStackTrace();
            }

                return null;
        
    }


}
