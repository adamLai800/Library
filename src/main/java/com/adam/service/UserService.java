package com.adam.service;

import com.adam.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface UserService extends CrudRepository<User, Integer> {

    @Query(value = "SELECT user_name FROM user WHERE user_id =? ", nativeQuery = true)
    String getUserName(String userId);

}

