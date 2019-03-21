package com.project.newsfeed.service.user.dto;

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
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", flag=" + flag +
                '}';
    }
}
