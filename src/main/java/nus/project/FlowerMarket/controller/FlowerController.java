package nus.project.FlowerMarket.controller;

import java.io.IOException;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import nus.project.FlowerMarket.model.Flower;
import nus.project.FlowerMarket.repository.PostRepository;

@Controller
@RequestMapping(path="/")
public class FlowerController {
    private Logger logger = LoggerFactory.getLogger(FlowerController.class);

    @Autowired
    private PostRepository postRepo;

    //This is the welcome page
    @GetMapping(path="/welcome")
    public ModelAndView getWelcome() {
        
        ModelAndView mvc = new ModelAndView();
        
        mvc.setViewName("welcome");
        mvc.setStatus(HttpStatus.OK);
        return mvc;
    }

    //This is to redirect to Home page
    @GetMapping(path="/home")
    public ModelAndView getHome() {
        
        ModelAndView mvc = new ModelAndView();
        
        mvc.setViewName("welcome");
        mvc.setStatus(HttpStatus.OK);
        return mvc;
    }

    //This is to redirect to About page
    @GetMapping(path="/about")
    public ModelAndView getAbout() {
        
        ModelAndView mvc = new ModelAndView();
        
        mvc.setViewName("about");
        mvc.setStatus(HttpStatus.OK);
        return mvc; 
    }

        //This is to redirect to Community page
        @GetMapping(path="/community")
        public ModelAndView getCommunity() {
            
            ModelAndView mvc = new ModelAndView();
            
            mvc.setViewName("community");
            mvc.setStatus(HttpStatus.OK);
            return mvc; 
        }

    // @GetMapping(path="/search")
    // public ModelAndView getSearch(@RequestParam (name= "flower_name") String)


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
        } catch (IOException e) {
            e.printStackTrace();
        }

        Flower f = new Flower();
        f.setImage(buff);
        f.setComment(comment);
        f.setPoster(poster);
        f.setImageType(imageType); 
        
        mvc.setViewName("community");
        Integer updCount = postRepo.insertPost(f);
        mvc.addObject("updateCount", updCount);
        mvc.setStatus(HttpStatus.OK);
        return mvc; 
    
    }
}
