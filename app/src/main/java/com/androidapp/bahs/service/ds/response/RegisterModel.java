package com.androidapp.bahs.service.ds.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Mobikasa on 3/15/2016.
 */
public class RegisterModel
{
    @SerializedName("user")
    @Expose
    private User user;

    /**
     *
     * @return
     * The user
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     * The user
     */
    public void setUser(User user) {
        this.user = user;
    }
    public class User {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("appusername")
        @Expose
        private String appusername;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("country_code")
        @Expose
        private String countryCode;
        @SerializedName("profile_image")
        @Expose
        private Object profileImage;
        @SerializedName("fb_uid")
        @Expose
        private Object fbUid;
        @SerializedName("reset_code")
        @Expose
        private Object resetCode;
        @SerializedName("access_token")
        @Expose
        private String accessToken;
        @SerializedName("spotpog_token")
        @Expose
        private String spotpogToken;
        @SerializedName("push_notifications")
        @Expose
        private String pushNotifications;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("is_deleted")
        @Expose
        private String isDeleted;
        @SerializedName("version")
        @Expose
        private String version;
        @SerializedName("is_phone_verified")
        @Expose
        private String isPhoneVerified;
        @SerializedName("is_active")
        @Expose
        private String isActive;
        @SerializedName("referral_code")
        @Expose
        private String referralCode;
        @SerializedName("is_newer_version")
        @Expose
        private String isNewerVersion;
        @SerializedName("changed_mobile_into")
        @Expose
        private String changedMobileInto;
        @SerializedName("is_marked_spot")
        @Expose
        private String isMarkedSpot;

        /**
         *
         * @return
         * The id
         */
        public String getId() {
            return id;
        }

        /**
         *
         * @param id
         * The id
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         *
         * @return
         * The firstName
         */
        public String getFirstName() {
            return firstName;
        }

        /**
         *
         * @param firstName
         * The first_name
         */
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        /**
         *
         * @return
         * The lastName
         */
        public String getLastName() {
            return lastName;
        }

        /**
         *
         * @param lastName
         * The last_name
         */
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        /**
         *
         * @return
         * The email
         */
        public String getEmail() {
            return email;
        }

        /**
         *
         * @param email
         * The email
         */
        public void setEmail(String email) {
            this.email = email;
        }

        /**
         *
         * @return
         * The appusername
         */
        public String getAppusername() {
            return appusername;
        }

        /**
         *
         * @param appusername
         * The appusername
         */
        public void setAppusername(String appusername) {
            this.appusername = appusername;
        }

        /**
         *
         * @return
         * The username
         */
        public String getUsername() {
            return username;
        }

        /**
         *
         * @param username
         * The username
         */
        public void setUsername(String username) {
            this.username = username;
        }

        /**
         *
         * @return
         * The phone
         */
        public String getPhone() {
            return phone;
        }

        /**
         *
         * @param phone
         * The phone
         */
        public void setPhone(String phone) {
            this.phone = phone;
        }

        /**
         *
         * @return
         * The countryCode
         */
        public String getCountryCode() {
            return countryCode;
        }

        /**
         *
         * @param countryCode
         * The country_code
         */
        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        /**
         *
         * @return
         * The profileImage
         */
        public Object getProfileImage() {
            return profileImage;
        }

        /**
         *
         * @param profileImage
         * The profile_image
         */
        public void setProfileImage(Object profileImage) {
            this.profileImage = profileImage;
        }

        /**
         *
         * @return
         * The fbUid
         */
        public Object getFbUid() {
            return fbUid;
        }

        /**
         *
         * @param fbUid
         * The fb_uid
         */
        public void setFbUid(Object fbUid) {
            this.fbUid = fbUid;
        }

        /**
         *
         * @return
         * The resetCode
         */
        public Object getResetCode() {
            return resetCode;
        }

        /**
         *
         * @param resetCode
         * The reset_code
         */
        public void setResetCode(Object resetCode) {
            this.resetCode = resetCode;
        }

        /**
         *
         * @return
         * The accessToken
         */
        public String getAccessToken() {
            return accessToken;
        }

        /**
         *
         * @param accessToken
         * The access_token
         */
        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        /**
         *
         * @return
         * The spotpogToken
         */
        public String getSpotpogToken() {
            return spotpogToken;
        }

        /**
         *
         * @param spotpogToken
         * The spotpog_token
         */
        public void setSpotpogToken(String spotpogToken) {
            this.spotpogToken = spotpogToken;
        }

        /**
         *
         * @return
         * The pushNotifications
         */
        public String getPushNotifications() {
            return pushNotifications;
        }

        /**
         *
         * @param pushNotifications
         * The push_notifications
         */
        public void setPushNotifications(String pushNotifications) {
            this.pushNotifications = pushNotifications;
        }

        /**
         *
         * @return
         * The createdAt
         */
        public String getCreatedAt() {
            return createdAt;
        }

        /**
         *
         * @param createdAt
         * The created_at
         */
        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        /**
         *
         * @return
         * The updatedAt
         */
        public String getUpdatedAt() {
            return updatedAt;
        }

        /**
         *
         * @param updatedAt
         * The updated_at
         */
        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        /**
         *
         * @return
         * The isDeleted
         */
        public String getIsDeleted() {
            return isDeleted;
        }

        /**
         *
         * @param isDeleted
         * The is_deleted
         */
        public void setIsDeleted(String isDeleted) {
            this.isDeleted = isDeleted;
        }

        /**
         *
         * @return
         * The version
         */
        public String getVersion() {
            return version;
        }

        /**
         *
         * @param version
         * The version
         */
        public void setVersion(String version) {
            this.version = version;
        }

        /**
         *
         * @return
         * The isPhoneVerified
         */
        public String getIsPhoneVerified() {
            return isPhoneVerified;
        }

        /**
         *
         * @param isPhoneVerified
         * The is_phone_verified
         */
        public void setIsPhoneVerified(String isPhoneVerified) {
            this.isPhoneVerified = isPhoneVerified;
        }

        /**
         *
         * @return
         * The isActive
         */
        public String getIsActive() {
            return isActive;
        }

        /**
         *
         * @param isActive
         * The is_active
         */
        public void setIsActive(String isActive) {
            this.isActive = isActive;
        }

        /**
         *
         * @return
         * The referralCode
         */
        public String getReferralCode() {
            return referralCode;
        }

        /**
         *
         * @param referralCode
         * The referral_code
         */
        public void setReferralCode(String referralCode) {
            this.referralCode = referralCode;
        }

        /**
         *
         * @return
         * The isNewerVersion
         */
        public String getIsNewerVersion() {
            return isNewerVersion;
        }

        /**
         *
         * @param isNewerVersion
         * The is_newer_version
         */
        public void setIsNewerVersion(String isNewerVersion) {
            this.isNewerVersion = isNewerVersion;
        }

        /**
         *
         * @return
         * The changedMobileInto
         */
        public String getChangedMobileInto() {
            return changedMobileInto;
        }

        /**
         *
         * @param changedMobileInto
         * The changed_mobile_into
         */
        public void setChangedMobileInto(String changedMobileInto) {
            this.changedMobileInto = changedMobileInto;
        }

        /**
         *
         * @return
         * The isMarkedSpot
         */
        public String getIsMarkedSpot() {
            return isMarkedSpot;
        }

        /**
         *
         * @param isMarkedSpot
         * The is_marked_spot
         */
        public void setIsMarkedSpot(String isMarkedSpot) {
            this.isMarkedSpot = isMarkedSpot;
        }

    }




}
