package nus.project.FlowerMarket.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import nus.project.FlowerMarket.service.LoginService;

@Controller
@RequestMapping(path="/{username}")
public class LoginController {

    @Autowired
    private LoginService loginSvc;
    
//     @GetMapping("/login")
//     public String getLogin() {

//         return "login";
//     }

//     @GetMapping
//     public String getLogout(HttpSession sess) {
//         sess.invalidate();
//         return "index";
//     }

    @PostMapping(path="/login")
    public ModelAndView goLogin(@RequestBody MultiValueMap<String,String> payload) {
        
        String username = payload.getFirst("username");
        String password = payload.getFirst("password");

        System.out.printf(">>>>>> username: %s, password: %s\n", username, password);

        ModelAndView mvc = new ModelAndView();

        if (!loginSvc.authenticate(username, password)) {
            
            //not successfull
            mvc.setViewName("error");
            mvc.setStatus(HttpStatus.FORBIDDEN);

        } else {
           
            //successful
            mvc.setViewName("hello");
            mvc.setStatus(HttpStatus.OK);
            mvc.addObject("username", username);
        }

        return mvc;
    }
}
