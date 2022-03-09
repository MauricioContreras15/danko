package com.wposs.danko.login.dto;

import org.json.JSONObject;

public class LoginDTO {

    private boolean user_app;
    private String user;
    private String password;
    private String device;

    public boolean isUser_app() {
        return user_app;
    }

    public void setUser_app(boolean user_app) {
        this.user_app = user_app;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

}
