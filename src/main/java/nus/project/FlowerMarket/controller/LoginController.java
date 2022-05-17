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

@Controller //what is the diff between path= and without
@RequestMapping("/authenticate")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(FlowerController.class);


    @Autowired
    private UserService userSvc;
    

    @GetMapping("/logout")
    public String getLogout(HttpSession sess) {
        sess.invalidate();
        return "welcome";
    }

    @PostMapping
    public ModelAndView getLogin(@RequestBody MultiValueMap<String, String> payload
            , HttpSession sess) {

        String username = payload.getFirst("username");
        String password = payload.getFirst("password");

        System.out.printf("+++ username: %s, password: %s\n", username, password);

        ModelAndView mvc = new ModelAndView();

        if (!userSvc.authenticate(username, password)) {
            // Not successful
            mvc.setViewName("error");
            mvc.setStatus(HttpStatus.FORBIDDEN);

        } else {
            // Successful
            sess.setAttribute("username", username);
            // mvc = new ModelAndView("redirect:/protected/hello");
        }
        
        return mvc;
    }
}
