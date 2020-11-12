package com.adam.repository;

import com.adam.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT COUNT(*) FROM user", nativeQuery = true)
    Integer getMaxUserCount();

    @Query(value = "SELECT MAX(CAST(SUBSTRING(user_id, 2, length(user_id)-1) AS UNSIGNED)) FROM user", nativeQuery = true)
    Integer getMaxUserId();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user WHERE user_id = ? ", nativeQuery = true)
    void deleteByUserId(String userId);

}

