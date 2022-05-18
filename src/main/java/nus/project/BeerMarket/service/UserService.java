package nus.project.BeerMarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.project.BeerMarket.model.User;
import nus.project.BeerMarket.repository.UserRepository;

@Service
public class UserService {
   
    @Autowired
    private UserRepository userRepo;

    //insert new users to the
    public boolean insertUser(User user) {

        boolean added = userRepo.insertUser(user);

        if(added) {
            return true;
        } else {
            return false;
        }
        
    }

    //authenticate the user, validate
    public boolean authenticate(String username, String password) {
    
        System.out.println(userRepo.countUsersByNameAndPassword(username, password));
        
        return 1 == userRepo.countUsersByNameAndPassword(username, password);

    }
}

