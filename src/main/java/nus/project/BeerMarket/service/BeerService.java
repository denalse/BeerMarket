package nus.project.BeerMarket.service;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.*;
import org.springframework.stereotype.Service;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import nus.project.BeerMarket.model.Beer;

@Service
public class BeerService {

    private Logger logger = LoggerFactory.getLogger(BeerService.class);

    public ArrayList<Beer> getBeer(Integer searchId) {

        final String url = "https://api.punkapi.com/v2/beers/" + searchId;

        RequestEntity<Void> req = RequestEntity
            .get(url)
            .accept(MediaType.APPLICATION_JSON)
            .build();

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = template.exchange(req, String.class);
        System.out.println(">>>> " + resp.getBody());
        try (InputStream is = new ByteArrayInputStream(resp.getBody().getBytes())) {
            JsonReader reader = Json.createReader(is);
            JsonArray array = reader.readArray();

            ArrayList<Beer> list = new ArrayList<>();

            for (int i=0; i<array.size();) {
            
                Beer b = new Beer();
                JsonObject obj = array.getJsonObject(i);
                b.setImageUrl(obj.getString("image_url"));
                b.setName(obj.getString("name"));
                b.setDescription(obj.getString("description"));
                b.setTips(obj.getString("brewers_tips"));

                list.add(b);
                System.out.println(">>>> BEER URL >>>>>" + list);
                logger.info("\r\n >>>> Image >>> " + list);
                return list;
            }

        } catch(Exception ex) {
                    
            ex.printStackTrace();
        }            
                    //}

        return null;
        
    }


}
