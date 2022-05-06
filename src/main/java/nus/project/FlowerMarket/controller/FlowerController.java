package nus.project.FlowerMarket.controller;

import org.slf4j.*;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/")
public class FlowerController {
    private Logger logger = LoggerFactory.getLogger(FlowerController.class);

    //This is the welcome page
    @GetMapping(path="/welcome")
    public ModelAndView getWelcome() {
        
        ModelAndView mvc = new ModelAndView();
        
        mvc.setViewName("welcome");
        mvc.setStatus(HttpStatus.OK);
        return mvc;
    }

    //This is to redirect to home page
    @GetMapping(path="/home")
    public ModelAndView getHome() {
        
        ModelAndView mvc = new ModelAndView();
        
        mvc.setViewName("welcome");
        mvc.setStatus(HttpStatus.OK);
        return mvc;
    }

    // @GetMapping(path="/search")
    // public ModelAndView getSearch(@RequestParam (name= "flower_name") String)


    // @PostMapping
    // public ModelAndView getHome() {

    //     ModelAndView mvc = new ModelAndView();

    //     mvc.setViewName
    // }
}
