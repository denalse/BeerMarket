package nus.project.FlowerMarket.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nus.project.FlowerMarket.model.Flower;
import nus.project.FlowerMarket.repository.PostRepository;

@RestController
@RequestMapping(path="/community/post")
public class FlowerRestController {
    
    @Autowired
    private PostRepository postRepo;

    @GetMapping(path="/{postId}/image")
    public ResponseEntity<byte[]> getPostImage(@PathVariable Integer postId) {

        Optional<Flower> opt =  postRepo.getPostById(postId);

        if(opt.isEmpty())
            return ResponseEntity.notFound().build();

        final Flower postVal = opt.get();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", postVal.getImageType());
        headers.add("Cache-control", "max-age=604800");

        return ResponseEntity.ok()
            .headers(headers)
            .body(postVal.getImage());
    }
}
