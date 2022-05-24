package nus.project.BeerMarket.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import nus.project.BeerMarket.model.Post;
import nus.project.BeerMarket.repository.PostRepository;

@Controller
@RequestMapping(path="/market")
public class MarketController {
    
    @Autowired
    private PostRepository postRepo;

    @GetMapping
    public ModelAndView getMarketPlace() {
        
        ModelAndView mvc = new ModelAndView();

        // get a list of images from your database
        ArrayList<Post> post = new ArrayList<>();
        post = postRepo.getAllPost();
        mvc.addObject("allPost", post);
        mvc.setViewName("market");

        return mvc;
    }
}
