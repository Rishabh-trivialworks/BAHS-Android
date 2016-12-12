package com.androidapp.bahs.service.ds;

public class UserBean extends AbsBindObject {

    String id;
    String name;
    String user_name;
    String email;
    String weight;
    String height;
    String gender;
    String age;
    String image_path;
    String registered_through;
    String verification_code;
    String access_token;
    String status;
    String created_at;
    String updated_at;
    String password;
    String confirm_password;
    String fb_id;
    String is_requested_forgot_pasword;
    String fb_token;
    String join_out_mailing_list;
    int notification_status;

    public int getNotification_status() {
        return notification_status;
    }

    public void setNotification_status(int notification_status) {
        this.notification_status = notification_status;
    }

    public String getJoined_mailing_list() {
        return join_out_mailing_list;
    }

    public void setJoined_mailing_list(String joined_mailing_list) {
        this.join_out_mailing_list = joined_mailing_list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return user_name;
    }

    public void setUsername(String username) {
        this.user_name = username;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getRegister_through() {
        return registered_through;
    }

    public void setRegister_through(String register_through) {
        this.registered_through = register_through;
    }

    public String getVerification_code() {
        return verification_code;
    }

    public void setVerification_code(String verification_code) {
        this.verification_code = verification_code;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirm_password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirm_password = confirmPassword;
    }

    public String getFb_uid() {
        return fb_id;
    }

    public void setFb_uid(String fb_uid) {
        this.fb_id = fb_uid;
    }

    public String getIs_requested_forgot_pasword() {
        return is_requested_forgot_pasword;
    }

    public void setIs_requested_forgot_pasword(String is_requested_forgot_pasword) {
        this.is_requested_forgot_pasword = is_requested_forgot_pasword;
    }

    public String getFb_token() {
        return fb_token;
    }

    public void setFb_token(String fb_token) {
        this.fb_token = fb_token;
    }

}
