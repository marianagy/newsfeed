package com.project.newsfeed.service.user.dto;

import java.util.Objects;

public class UserDTO {

    private String username;
    private String password;
    private Boolean flag;

    //Todo: sa pun si lista de notificari cand termin cu notificarile


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
                Objects.equals(password, userDTO.password) &&
                Objects.equals(flag, userDTO.flag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, flag);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", flag=" + flag +
                '}';
    }
}
