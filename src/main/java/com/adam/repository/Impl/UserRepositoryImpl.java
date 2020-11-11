package com.adam.repository.Impl;

import com.adam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryImpl {

    @Autowired
    private UserRepository userRepository;

    public String getNewUserIdInsertTable() {
        int getMaxUserId = userRepository.getMaxUserCount();
        System.out.println(" getMaxUserId " + Integer.toBinaryString(getMaxUserId));
        if(getMaxUserId != 0 ){
            getMaxUserId = userRepository.getMaxUserId();
            System.out.println(getMaxUserId);
            getMaxUserId = getMaxUserId + 1;
        }else{
            getMaxUserId = 1;
        }
        System.out.println(getMaxUserId);
        String getNewUserIdInsertTable = "U" + String.valueOf(getMaxUserId);
        return getNewUserIdInsertTable;
    }

}
