package nus.project.BeerMarket.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

// import org.slf4j.*;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import nus.project.BeerMarket.model.Beer;
import nus.project.BeerMarket.service.BeerService;


@Controller
@RequestMapping (path="/")
public class MainController {
    
    @Autowired
    private BeerService beerSvc;

    // private Logger logger = LoggerFactory.getLogger(MainController.class);

    public String view() {
        return "index";
    }

    // This is the welcome page
    @GetMapping(path="/welcome")
    public ModelAndView getWelcome() {
        
        ModelAndView mvc = new ModelAndView();
        
        mvc.setViewName("welcome");
        mvc.setStatus(HttpStatus.OK);
        return mvc;
    }

    // This is the home page
    @GetMapping(path="/home")
    public ModelAndView getHome() {
        
        ModelAndView mvc = new ModelAndView();
        
        mvc.setViewName("welcome");
        mvc.setStatus(HttpStatus.OK);
        return mvc;
    }

    // This is to redirect to About page
    @GetMapping(path="/about")
    public ModelAndView getAbout() {
        
        ModelAndView mvc = new ModelAndView();
        
        mvc.setViewName("about");
        mvc.setStatus(HttpStatus.OK);
        return mvc; 
    }

    // This is redirect to Market page
    @GetMapping (path="/marketplace")
    public ModelAndView getMarket() {
        
        ModelAndView mvc = new ModelAndView();
        
        mvc.setViewName("market");
        mvc.setStatus(HttpStatus.OK);

        return mvc; 
    }


    //Do a random search of beer using search Id
    @GetMapping(path="/search")
    public ModelAndView getSearch(@RequestParam Integer searchId) {
   
        ModelAndView mvc = new ModelAndView();

        ArrayList<Beer> list = beerSvc.getBeer(searchId);

        mvc.addObject("list", list);
        mvc.addObject("searchId", searchId);

        mvc.setViewName("showBeer");
        mvc.setStatus(HttpStatus.OK);

        return mvc;
    }
    
}
