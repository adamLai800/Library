package com.adam.api.response;

import com.adam.model.User;

public class AddUserResponse extends APIResponse {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

