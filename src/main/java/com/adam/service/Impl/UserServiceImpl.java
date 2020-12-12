package com.adam.service.Impl;

import com.adam.api.request.DeleteByUserIdRequest;
import com.adam.model.User;
import com.adam.repository.UserRepository;
import com.adam.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public String getNewUserIdInsertTable() {
        int getMaxUserId = 0 ;
        try {
            getMaxUserId = userRepository.getMaxUserId();
        } catch (Exception ex) {
            LOG.info(" getNewUserIdInsertTable failed : ", ex);
        }
        getMaxUserId ++;
        String getNewUserIdInsertTable = "U" + String.valueOf(getMaxUserId);
        return getNewUserIdInsertTable;
    }

    @Override
    public boolean addUser(User user) {
        boolean isSucceeded = false;
        try {
            userRepository.save(user);
            isSucceeded = true;
        } catch (Exception ex) {
            LOG.error(" save user failed : ", ex);
        }
        return isSucceeded;
    }

    @Override
    public boolean deleteByUserId(DeleteByUserIdRequest deleteByUserIdRequest) {
        boolean isSucceeded = false;
        try {
            userRepository.deleteByUserId(deleteByUserIdRequest.getUserId());
            isSucceeded = true;
        } catch (Exception ex) {
            LOG.info(" deleteByUserId failed : ", ex);
        }
        return isSucceeded;
    }

    @Override
    public User getUserAll(String userId) {
        User user = userRepository.getUserAll(userId);
        return user;
    }

}
