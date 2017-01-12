package com.androidapp.bahs.service.bean;

import android.graphics.Bitmap;

import com.androidapp.bahs.service.AbsBindObject;

import java.io.File;


public class User extends AbsBindObject implements Comparable<User> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	private String id;
	private String name;
	private String password;
	private String confirmPassword;

	private File imgFile;
	private boolean isFacebook;
	private boolean isLinkedIn;
	private int index;

	private String status;
	private String user_detail;
	private String first_name;
	private String last_name;
	private String complete_name;
	private String email;
	private String gender;
	private String image_path;
	private String register_through;
	private String verification_code;
	private int is_requested_forgot_pasword;
	private String ethnicity;
	private String access_token;
	private String fb_token;
	private String fb_uid;
	private String linkedin_uid;
	private String secondary_email;
	private String roll;
	private String age;
	private String created_at;
	private String updated_at;
	private String is_primary_given;
	private String email1;
	private String email2;
	private String email3;
	private String invite_from;
	private String invite_to;
	private String invite_confirm_status;
	private String confirmed;
	private String from_user_id;
	private String to_user_id;
	private boolean isSeleted;
	private String stack_order;
	private String message;
	private Bitmap image;
	private String Question;
	private String Answer;
	private String education;
	private String google_id;

	public String getGoogle_id() {
		return google_id;
	}

	public void setGoogle_id(String google_id) {
		this.google_id = google_id;
	}

	public String getQuestion() {
		return Question;
	}
	public void setQuestion(String Question) {
		this.Question = Question;
	}

	public String getAnswer() {
		return Answer;
	}
	public void setAnswer(String Answer) {
		this.Answer = Answer;
	}

	public Bitmap getimage() {
		return image;
	}
	public void setimage(Bitmap image) {
		this.image = image;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStack_order() {
		return stack_order;
	}
	public void setStack_order(String stack_order) {
		this.stack_order = stack_order;
	}
	public boolean isSeleted() {
		return isSeleted;
	}
	public void setSeleted(boolean isSeleted) {
		this.isSeleted = isSeleted;
	}
	public String getFrom_user_id() {
		return from_user_id;
	}
	public void setFrom_user_id(String from_user_id) {
		this.from_user_id = from_user_id;
	}
	public String getTo_user_id() {
		return to_user_id;
	}
	public void setTo_user_id(String to_user_id) {
		this.to_user_id = to_user_id;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getInvite_confirm_status() {
		return invite_confirm_status;
	}
	public void setInvite_confirm_status(String invite_confirm_status) {
		this.invite_confirm_status = invite_confirm_status;
	}
	public String getInvite_from() {
		return invite_from;
	}
	public void setInvite_from(String invite_from) {
		this.invite_from = invite_from;
	}
	public String getInvite_to() {
		return invite_to;
	}
	public void setInvite_to(String invite_to) {
		this.invite_to = invite_to;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getEmail3() {
		return email3;
	}
	public void setEmail3(String email3) {
		this.email3 = email3;
	}


	public String getIs_primary_given() {
		return is_primary_given;
	}
	public void setIs_primary_given(String is_primary_given) {
		this.is_primary_given = is_primary_given;
	}
	public boolean isFacebook() {
		return isFacebook;
	}
	public void setFacebook(boolean isFacebook) {
		this.isFacebook = isFacebook;
	}
	public boolean isLinkedIn() {
		return isLinkedIn;
	}
	public void setLinkedIn(boolean isLinkedIn) {
		this.isLinkedIn = isLinkedIn;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public File getImgFile() {
		return imgFile;
	}
	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUser_detail() {
		return user_detail;
	}
	public void setUser_detail(String user_detail) {
		this.user_detail = user_detail;
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
	public String getComplete_name() {
		return complete_name;
	}
	public void setComplete_name(String complete_name) {
		this.complete_name = complete_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public String getRegister_through() {
		return register_through;
	}
	public void setRegister_through(String register_through) {
		this.register_through = register_through;
	}
	public String getVerification_code() {
		return verification_code;
	}
	public void setVerification_code(String verification_code) {
		this.verification_code = verification_code;
	}
	public int getIs_requested_forgot_pasword() {
		return is_requested_forgot_pasword;
	}
	public void setIs_requested_forgot_pasword(int is_requested_forgot_pasword) {
		this.is_requested_forgot_pasword = is_requested_forgot_pasword;
	}
	public String getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getFb_token() {
		return fb_token;
	}
	public void setFb_token(String fb_token) {
		this.fb_token = fb_token;
	}
	public String getFb_uid() {
		return fb_uid;
	}
	public void setFb_uid(String fb_uid) {
		this.fb_uid = fb_uid;
	}
	public String getLinkedin_uid() {
		return linkedin_uid;
	}
	public void setLinkedin_uid(String linkedin_uid) {
		this.linkedin_uid = linkedin_uid;
	}
	public String getSecondary_email() {
		return secondary_email;
	}
	public void setSecondary_email(String secondary_email) {
		this.secondary_email = secondary_email;
	}
	public String getRoll() {
		return roll;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
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
	public String getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}

	@Override
	public int compareTo(User another) {

		  return this.complete_name.compareTo(another.complete_name);
	}


	public String getEducation() {
		return education;
	}

	public void setEducation(String edu) {
		education = edu;
	}
}
