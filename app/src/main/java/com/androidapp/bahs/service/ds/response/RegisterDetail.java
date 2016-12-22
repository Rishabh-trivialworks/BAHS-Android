package com.androidapp.bahs.service.ds.response;

/**
 * Created by Mobikasa on 12/19/2016.
 */

public class RegisterDetail {

    String first_name;
    String last_name;
    String email;
    String password;
    String device_type;
    String device_id;
    String device_token;

    public RegisterDetail(String first_name, String last_name, String email, String password, String device_type, String device_id, String device_token) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.device_type = device_type;
        this.device_id = device_id;
        this.device_token = device_token;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }
}
