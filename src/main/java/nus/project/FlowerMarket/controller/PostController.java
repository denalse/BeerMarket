package nus.project.FlowerMarket.controller;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import nus.project.FlowerMarket.model.Flower;
import nus.project.FlowerMarket.repository.PostRepository;

@Controller
@RequestMapping(path="/post")
public class PostController {

    private Logger logger = LoggerFactory.getLogger(FlowerController.class);

    @Autowired
    private PostRepository postRepo;
    
    @GetMapping(path="/{postId}")
    public ModelAndView getPostById(@PathVariable Integer postId) {
        
        ModelAndView mvc = new ModelAndView();
        Optional<Flower> opt = postRepo.getPostById(postId);
        mvc.addObject("post", opt.get());
        mvc.setViewName("post");
        mvc.setStatus(HttpStatus.OK);
        
        logger.info("\r\n Result  >>>>>" + mvc);
        
        return mvc;
        
    }

    
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView getPost(@RequestParam MultipartFile image,
    @RequestPart String comment, 
    @RequestPart String poster) {

        ModelAndView mvc = new ModelAndView();
    
        String imageName = image.getOriginalFilename();
        long imageSize = image.getSize();
        String imageType = image.getContentType();
        byte[] buff = new byte[0];

        try {
            buff = image.getBytes();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Flower f = new Flower();
        f.setImage(buff);
        f.setComment(comment);
        f.setPoster(poster);
        f.setImageType(imageType); 
        
        mvc.setViewName("result");
        Integer updCount = postRepo.insertPost(f);
        mvc.addObject("updateCount", updCount);
        mvc.setStatus(HttpStatus.OK);
        
        return mvc;
    }
}
