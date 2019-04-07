package com.project.newsfeed.service.user.dto;

import java.util.Objects;

public class UserDTO {

    private String username;
    private Boolean flag;
    private Integer id;

    //Todo: sa pun si lista de notificari cand termin cu notificarile


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(username, userDTO.username) &&
                Objects.equals(flag, userDTO.flag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, flag);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +

                ", flag=" + flag +
                '}';
    }
}
