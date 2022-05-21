package nus.project.BeerMarket.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import nus.project.BeerMarket.model.Post;
import nus.project.BeerMarket.repository.PostRepository;

@RestController
//change from post to market
@RequestMapping(path="/post")
public class PostRestController {
    
     @Autowired
    private PostRepository postRepo;

    @GetMapping(path="/{postId}/image")
    public ResponseEntity<byte[]> getPostImage(@PathVariable Integer postId) {

        Optional<Post> opt =  postRepo.getPostById(postId);

        if(opt.isEmpty())
            return ResponseEntity.notFound().build();

        final Post postVal = opt.get();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", postVal.getImageType());
        headers.add("Cache-control", "max-age=604800");

        return ResponseEntity.ok()
            .headers(headers)
            .body(postVal.getImage());
    }

}
