package nus.project.FlowerMarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.project.FlowerMarket.model.User;
import nus.project.FlowerMarket.repository.UserRepository;

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
    public User authenticate(String username, String password) {
    
        return userRepo.authenticate(username, password);
    }
}

