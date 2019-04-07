package com.project.newsfeed.service.user.dto;

import com.project.newsfeed.entity.user.User;

public class UserDTOHelper {

    private UserDTOHelper() {
        //empty private constructor to hide the public implicit one
    }

    public static UserDTO fromEntity(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setId(user.getId());
        userDTO.setFlag(user.getFlag());
        return userDTO;
    }

    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setId(userDTO.getId());
        user.setFlag(userDTO.getFlag());
        return user;
    }

}
