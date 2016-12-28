package com.androidapp.bahs.service.bean;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mobikasa on 12/27/2016.
 */

public class UserModel implements Parcelable {
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;

    private String nickName;
    @SerializedName("email")
    private String emailAddress;
    @SerializedName("password")
    private String password;
    @SerializedName("confirm_password")
    private String confirmPassword;

    private String phoneNumber;
    private String mailingAddress;
    private String interviewLocation;
    private String referredBy;
    @SerializedName("profile_images")
    private String profileImagePath;
    @SerializedName("dob")
    private String dateOfBirth;
    @SerializedName("gender")
    private String gender;
    @SerializedName("send_categories")
    private String sendCategories;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getInterviewLocation() {
        return interviewLocation;
    }

    public void setInterviewLocation(String interviewLocation) {
        this.interviewLocation = interviewLocation;
    }

    public String getReferredBy() {
        return referredBy;
    }

    public void setReferredBy(String referredBy) {
        this.referredBy = referredBy;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSendCategories() {
        return sendCategories;
    }

    public void setSendCategories(String sendCategories) {
        this.sendCategories = sendCategories;
    }

    public UserModel(String firstName, String lastName, String nickName, String emailAddress, String password, String confirmPassword, String phoneNumber, String mailingAddress, String interviewLocation, String referredBy, String profileImagePath) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phoneNumber = phoneNumber;
        this.mailingAddress = mailingAddress;
        this.interviewLocation = interviewLocation;
        this.referredBy = referredBy;
        this.profileImagePath = profileImagePath;
    }

    // "De-parcel object
    public UserModel(Parcel in) {
        firstName = in.readString();
        lastName  = in.readString();
        nickName  = in.readString();
        emailAddress=in.readString();
        password=in.readString();
        confirmPassword=in.readString();
        phoneNumber=in.readString();
        mailingAddress=in.readString();
        interviewLocation=in.readString();
        referredBy=in.readString();
        profileImagePath=in.readString();

    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(nickName);
        dest.writeString(emailAddress);
        dest.writeString(password);
        dest.writeString(confirmPassword);
        dest.writeString(phoneNumber);
        dest.writeString(mailingAddress);
        dest.writeString(interviewLocation);
        dest.writeString(referredBy);
        dest.writeString(profileImagePath);

    }



}
