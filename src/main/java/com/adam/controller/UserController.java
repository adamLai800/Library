package com.adam.controller;

import com.adam.model.User;
import com.adam.repository.Impl.UserRepositoryImpl;
import com.adam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/libray")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    //Add
    @PostMapping(path = "/adduser") // Map ONLY POST Requests
    public @ResponseBody
    String addNew(
            @RequestParam String userName) {

        User user = new User();
        user.setUserId(userRepositoryImpl.getNewUserIdInsertTable());
        user.setUserName(userName);
        userRepository.save(user);
        return "User Saved";
    }

    @DeleteMapping(path = "/deleteByUserId") // Map ONLY Delete All Requests
    public @ResponseBody String deleteByUserId(@RequestParam String userId) {
        userRepository.deleteByUserId(userId);
        return "User Delete OK";
    }

}
