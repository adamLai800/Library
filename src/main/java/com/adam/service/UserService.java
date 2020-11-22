package com.adam.service;


import com.adam.api.request.DeleteByUserIdRequest;
import com.adam.model.User;

public interface UserService {

    String getNewUserIdInsertTable();

    boolean addUser(User user);

    boolean deleteByUserId(DeleteByUserIdRequest deleteByUserIdRequest);

    User getUserAll(String userId);

}

