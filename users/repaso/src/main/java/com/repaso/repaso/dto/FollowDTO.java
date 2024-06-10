package com.repaso.repaso.dto;

import com.repaso.repaso.persistence.model.User;

public class FollowDTO {

    String follower;
    String followed;


    public FollowDTO(User user) {
       /* this.name = user.getUsername();
        this.rolname = user.getPermise().getRolName();*/
    }
}
