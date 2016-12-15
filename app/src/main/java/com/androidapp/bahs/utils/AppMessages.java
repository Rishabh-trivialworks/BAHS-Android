package com.androidapp.bahs.utils;

public class AppMessages {

    /*-------------------------------- Network Message -------------------------------*/
    public static final String CONNECT_NETWORK = "Please Check Your Network Connection";

    /*-------------------------------- Login Message -------------------------------*/
    public interface CommonSignInSignUpMessages {
        String NO_EMAIL = "Email is blank";
        String NO_PASSWORD = "Password is blank";
        String NEW_NO_PASSWORD = "New password is blank.";
        String CONFIRM_NEW_NO_PASSWORD = "Confirm password is blank.";
        String INVALID_EMAIL = "Invalid Email Address";
        String MIN_PASSWORD_CHECK = "Password must be min. 6 characters.";
        String NEW_MIN_PASSWORD_CHECK = "New Password must be min. 6 characters.";
        String CONFIRM_NEW_MIN_PASSWORD_CHECK = "Confirm Password must be min. 6 characters.";
        String NEW_PASSWORD_DO_NOT_MATCH = "New password and Confirm password do not match.";

        String NO_SUBJECT="Subject is blank.";
        String PASSWORDUPDATEDSUCCESSFULLY="Password updated successfully";
        String NO_INTERNET_AVAILABLE = "No internet connection.";
    }
    /*-------------------------------- Signup Message -------------------------------*/

    public interface SignUpMessages {
        String NO_FIRST_NAME = "Name is blank";
        String NO_LAST_NAME = "Last Name is blank";
        String N0_CHECK_BOX_TERMS_CONDITION = "Please read and accept terms and condition before signup.";
        String FEMALE = "female";

    }

    public interface BundleValueSignIn {
        String EMAIL = "Email is blank";
        String length = "email_length";
    }

    public interface BundleValueSignUp {
        String TERMSANDCONDITIONS_PRIVACYPOLICY = "TERMS_PRIVACY";
        String HEADERNAME = "header_name";

    }

    public interface CommomBundleTags {
        String FROM = "from";
        String SIGNUP = "signup";
        String SIGNOUT = "signout";
        String HOMEACTIVITY = "HomeActivity";
    }

    public interface GooglePlaceBundle{
        String ADDRESS = "address";
        String TYPE = "type";

        String LATITUDE = "Latitude";
        String LONGITUDE = "Longitude";
        String POSTALCODE = "postalCode";
        String LOCALITY = "locality";
        String DISPLAY_DIALOG = "display_dialog";
        String SCHOOL_NAME = "school_name";
        String SCHOOL_ID = "school_id";
    }

    public interface JsonParsingKeys{
        String RESULT = "result";
        String ADDRESS_COMPONENTS = "address_components";
        String TYPES = "types";
        String POSTAL_CODE = "postal_code";
        String LOCALITY = "locality";
        String LONG_NAME = "long_name";
        String GEOMETERY = "geometry";
        String LOCATION = "location";
        String LAT = "lat";
        String LNG = "lng";

        String PREDICTIONS  = "predictions";
        String DESCRIPTION = "description";
        String PLACE_ID = "place_id";

    }

    public interface SignInAndSignUpTags
    {
        String FB="FB";
        String SIGNIN="signin";
        String SIGNUP="signup";
        String GP="GP";
    }

    public interface NetworkMess
    {
        String ServerError500="Server could not fullfill the request.";
    }

    public interface ResiderMenuHeaderName
    {
        String MEET="Meet";
        String CARD = "Card";
        String SCREEN = "screen";
        String DISPLAYSCREEN = "myCard";
        String BACK="back";
        String Drawer="drawer";
        String SAVEDCARD = "Saved Cards";
        String FRIENDS="Friends";
        String CONTACTUS="Contact Us";
    }

    public interface ResponseSignupAndLogFB
    {
        String REGISTERED_THROUGH_LOGIN_FB="Facebook";
        String REGISTERED_THROUG_SETTING="Setting";
        String SENT_WAVE="yes";
        String REGISTERED_THROUGH_SIGNUP="Email";

    }
  public interface ExtraKey{
      String KIDPICURL="kid_pic_url";

  }
    public interface PreferenceKeys{
        String FIRSTWAVE="fisrtwave";
        String FIRSTSAVE="firstsave";
    }
    public interface ContactUsTag {

        String SHARE_A_PROBLEM = "Share a Problem";
        String PROVIDE_GENERAL_FEEDBACK = "Provide General Feedback";
        String OTHER = "Other";
        String CANCEL = "Cancel";
        String SELECT_SUBJECT = "Select Subject";
        String CONTACTUSMESS="Please select subject for the mail.";
        String CONTACTUSMESSSUCCESS="Email sent successfully!";
        String SAMESCREEN="same";
        String BACK="back";

    }
    public interface Platform{
        String PLATFORM="Android";
    }
    public interface PushChatTag
    {
        String PUSHCHATCLASSNAME="ChatActivity";
        String NODATA="Noinsert";
        String LOADMORE="loadmore";
    }
    public interface UIChatFragmentTags
    {
        String DELETE="Delete";
        String CANCEL="Cancel";
        String MESS="Do you really want to delete this conversation with ";
    }
    public interface ChatTags
    {
        String LOADMORE="loadmore";
        String MEETUP="meetup";
        String CONCIERGE="Concierge";
    }
    public interface BroadCastTags {
        String UNREADMESS = "unreadmess";
        String NETWORKONOFF = "network_on_off";
        String MESS = "mess";
        String DISCONNECTED = "disconnected";
        String CONNECTED = "connected";
    }
    public interface CommonTags
    {
        String TAGFRAGMENT="fragment";

    }
    public interface SnackbarStrings
    {
        String PRESS_AGAIN_TO_EXIT="Press again to exit";
    }
    public interface UserKidFragmentTag{

         String USERDATA = "userdata";
    }
    public interface NavBarTag{
        String NAME= "navigation_bar_height";
        String DEFTYPEBOOL= "bool";
        String DEFTYPEDIMEN="dimen";
        String DEFPACKAGE= "android";
    }
    public interface AboutMeTag{
         String USERDATA = "userdata";
        String CONTAINERID="containerID";
        String VALUE="value";

    }
    public interface CardFragmentTag{
     String USERID = "userid";
        String CARDCONTAINERID="cardContainerID";

    }


}

