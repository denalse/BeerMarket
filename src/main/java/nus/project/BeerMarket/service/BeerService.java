package nus.project.BeerMarket.service;


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
                
        ArrayList<String> beerUrl = new ArrayList<String>();

            // int j=1; //Number of id limit to 26
            // for (int i=0; i<j; i++) {
                                        
                RequestEntity<Void> req = RequestEntity
                .get(url)
                .accept(MediaType.APPLICATION_JSON)
                .build();

                RestTemplate template = new RestTemplate();

                JsonArray array = null;

                ResponseEntity<String> resp = template.exchange(req, String.class);
                try (InputStream is = new ByteArrayInputStream(resp.getBody().getBytes())) {
                    JsonReader reader = Json.createReader(is);
                    array = reader.readArray();

                // System.out.println(object.getJsonObject("id").getJsonObject("name").getJsonObject("description").getJsonObject("brewers_tips").getJsonString("image_url"));
                String image = ((JsonObject) array.get(0)).getString("image_url"); //value
                String name = ((JsonObject) array.get(0)).getString("name"); //value
                String description = ((JsonObject) array.get(0)).getString("description"); //value
                String tips = ((JsonObject) array.get(0)).getString("brewers_tips"); //value

                    //JsonObject q = array.getJsonObject(i);

                    System.out.printf("\r\r\n image>>> %s\n", image);
                    System.out.printf("\r\r\n Name of Beer>>> \n", name);
                    System.out.printf("\r\r\n Description>>> \n", description);
                    System.out.printf("\r\r\n Tips>>> %s\n", tips);

                    beerUrl.add(image);
                    beerUrl.add(name);
                    beerUrl.add(description);
                    beerUrl.add(tips);

                    System.out.println(">>>> BEER URL >>>>>" + beerUrl);
                                            
                    logger.info("\r\n >>>> Image >>> " + image);
                    logger.info("\r\n >>>> Name >>> " + name);
                                        
                                        
                         return beerUrl;

                 } catch(Exception ex){
                    
                    ex.printStackTrace();
                }

            //}

            return null;
        
    }


}
