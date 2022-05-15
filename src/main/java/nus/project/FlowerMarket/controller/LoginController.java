package nus.project.FlowerMarket.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import nus.project.FlowerMarket.model.User;
import nus.project.FlowerMarket.service.UserService;

@Controller
@RequestMapping (path="/")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(FlowerController.class);


    @Autowired
    private UserService userSvc;
    

    @GetMapping(path="/logout")
    public String getLogout(HttpSession sess) {
        sess.invalidate();
        return "welcome";
    }

    @PostMapping (path="/authenticate")
    public ModelAndView goLogin(@RequestBody MultiValueMap<String,String> payload) {
        
        String username = payload.getFirst("username");
        String password = payload.getFirst("password");

        System.out.printf(">>>>>> username: %s, password: %s\n", username, password);

        ModelAndView mvc = new ModelAndView();

        User userAuthenticate = userSvc.authenticate(username, password);

        if (userAuthenticate == null) {
            
            //not successfull
            mvc.setStatus(HttpStatus.UNAUTHORIZED);
            mvc.setViewName("error");

            return mvc;
            
        } 
        else {
           
            //successful
            mvc.setViewName("welcome");
            mvc.addObject("username", username);
            mvc.setStatus(HttpStatus.OK);

        }
        
        return mvc;
    }
}
