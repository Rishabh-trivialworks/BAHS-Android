package com.androidapp.bahs.service.ds.response;
import com.google.gson.annotations.Expose;
public class LoginRequest
{
    @Expose
    private String username;
    @Expose
    private String Password;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String Password) {
        this.Password = Password;
    }
}
