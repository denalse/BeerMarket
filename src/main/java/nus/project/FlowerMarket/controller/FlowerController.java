package nus.project.FlowerMarket.controller;

import javax.servlet.http.HttpSession;

// import org.springframework.beans.factory.annotation.Autowired;

// import org.slf4j.*;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping (path="/")
public class FlowerController {
    
    // @Autowired
    // private LoginService loginSvc;
    // private Logger logger = LoggerFactory.getLogger(FlowerController.class);

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

        // This is the welcome page
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

    // This is to redirect to D'Market Place page
    @GetMapping(path="/market")
    public ModelAndView getMarket() {
            
        ModelAndView mvc = new ModelAndView();
            
        mvc.setViewName("market");
        mvc.setStatus(HttpStatus.OK);
        return mvc;
    }

    //This is to redirect to Login page
    @GetMapping(path="/login")
    public ModelAndView getLogin() {
            
        ModelAndView mvc = new ModelAndView();
            
        mvc.setViewName("login");
        mvc.setStatus(HttpStatus.OK);
        return mvc; 
    }

    @GetMapping(path="/register")
    public ModelAndView getRegister() {
            
        ModelAndView mvc = new ModelAndView();
            
        mvc.setViewName("register");
        mvc.setStatus(HttpStatus.OK);
        return mvc; 
    }


    // }
    // @GetMapping(path="/search")
    // public ModelAndView getSearch(@RequestParam (name= "flower_name") String)

}
