package com.androidapp.bahs.service.utils;


public class Constants {

    public static final String REVIEW_URL_GOOGLE = "market://details?id=performix.com.performix";
    public static int mProgramListPage = 0;
    public static int mMyWorkoutProgramListPage = 0;
    public static int mMyWorkoutGalleryPage = 0;
    public static int mMyWorkoutProgramGalleryPage = 0;
    public static int mFeedsPage = 0;
    public static int mCommentsPage = 0;
    public static int mLikesPage = 0;
    public static int mNotificationsPage = 0;

    public static boolean mHideEditButtonOnDestroy = true;

    public interface IntentExtras {
        String HIT_API = "hit-api";
        String INVITE_FRIENDS = "invite-friends";
        String DIRECTORY_NAME = "directoryName";
        String GALLERY_IMAGE_ID = "gallery_image_id";
        String SHARE_FEED_WORKOUT_NAME = "workout_name";
        String CROPPED_IMAGE_PATH = "cropped-image-path";
    }


    public interface GeneralConstants
    {
        String IMAGE_STORE ="Image Capture";
        int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
        int RESULT_LOAD_IMAGE = 101;
    }

    public interface ServiceConstants {
        String FOLLOWING_URL = "/user/totalfollwings?";
        String FOLLOWERS_URL = "/user/totalfollwers?";
        String LOGIN="users/signin";
        String REGISTER="users/register";
        String SENT_POST = "/sendfie/sentposts?";
        String RECEIVED_POST = "/sendfie/receivedposts?";

        String RESTAURANT_LIKE = "/sendfie/likeunlikerestaurant?";
        String POST_LIKE = "/sendfie/likeunlikepost?";

        String POST_LIKE_LIST = "/sendfie/postlikelists?";
        String POST_COMMENT_LIST = "/sendfie/postcommentslists?";

        String REPORT_POST = "/sendfie/reportabousepost?";
        String DELETE_POST = "/sendfie/deletepost?";
        String REPORT_POST_IMAGE = "/user/reportabousepostimage?";

        String ALL_NOTIFICATIONS = "/user/allnotifications?";
        String NOTIFICATION_COUNTER = "/user/totalnotifications?";

        String BLOCK_USER = "/user/blockanotheruser";
        String UNBLOCK_USER = "/user/unblockanotheruser";

        String TEST_REGISTER="users/signupemail";//"viewsharedtosongs?page=1";

    }

    public interface WebConstants {

        String LISTING ="http://www.androidbegin.com/tutorial/";
        String BASE_URL_CONSTANT="http://cardamom-live.mobikasa.net/api/v1/";//"http://app.getadue.com/api/v1/users/";//
        int NETWORK_ERROR = 10000;
        int FAILED_ERROR = 10001;
        String INVALID_RESPONSE_MESSAGE = "Invalid reposne from server. Please try again later.";
        String NETWORK_MESSAGE = "Could not establish connection. Please check network settings or contact your mobile operator.";

        int CONNECTIONTIMEOUT = 30 * 1000;
        int READTIMEOUT = 30 * 1000;

        String HTTP_METHOD_POST = "POST";
        String HTTP_METHOD_GET = "GET";

        String HOST_API = "http://performix.mobikasa.net/api/v1";//"http://team.mobikasa.net:8089"; // Staging IP
        String IMAGES_PATH = "http://performix.mobikasa.net";
        //	String HOST_API = "http://54.85.101.196"; // Live IP
    }

    public interface PerformixLinksAndID {
        String WEBSITE = "http://www.performixdriven.com/";
        String FACEBOOK = "http://www.facebook.com/performixdriven";
        String TWITTER = "http://www.twitter.com/performixdriven";
        String INSTAGRAM = "http://www.instagram.com/performixdriven";
        String HELP_CENTER = "info@performixdriven.com";
        String TERMS_OF_USE = "http://performix.mobikasa.net/workoutapp/terms-and-conditions";
    }

    public interface FacebookUrls {
        String FB_URL = "https://fb.me/1611942559069083";
    }

    public interface FragmentTagConstants {
        int FRAGMENT_LOGIN = 0;
        int FRAGMENT_REGISTER = 1;
        int FRAGMENT_RESET_PASSWORD = 2;
        int FRAGMENT_PROGRAM_LIST = 3;
        int FRAGMENT_PROGRAM_WORKOUT_DETAILS = 4;
        int FRAGMENT_WORKOUT_VIDEO = 5;
        int FRAGMENT_MORE_OPTIONS = 6;
        int FRAGMENT_ACCOUNT_SETTINGS = 7;
        int FRAGMENT_MY_PROFILE = 8;
        int FRAGMENT_MY_WORKOUT = 9;
        int FRAGMENT_MY_WORKOUT_PROGRAM_DETAILS = 10;
        int FRAGMENT_MY_WORKOUT_PROGRAM_VIDEO_DETAILS = 11;
        int FRAGMENT_GOTO_MY_GALLERY = 12;
        int FRAGMENT_VIEW_MY_IMAGE = 13;
        int FRAGMENT_FEED = 14;
        int FRAGMENT_SHARE_FEED = 15;
        int FRAGMENT_NOTIFICATION = 16;
    }

    public interface PopupTagConstants {
        int POPUP_LOGOUT = 0;
        int POPUP_REGISTRATION_CONFIRMATION = 1;
        int POPUP_LOGIN_AGAIN = 2;
        int POPUP_ADD_TO_MY_WORKOUT = 3;
        int POPUP_WORKOUT_COMPLETION = 4;
        int POPUP_DELETE_MY_WORKOUT = 5;
        int POPUP_DELETE_MY_WORKOUT_IMAGE = 6;
        int POPUP_PROGRAM_BEFORE_IMAGE = 7;
        int POPUP_WORKOUT_COMPLETED = 8;
        int POPUP_WEEK_COMPLETED = 9;
        int POPUP_PROGRAM_COMPLETED = 10;
        int POPUP_PROGRAM_AFTER_IMAGE = 11;
        int POPUP_POSTING_AGREEMENT = 12;
        int POPUP_DELETE_PROFILE_FEED_BEFORE_AFTER_IMAGE = 13;
        int POPUP_DELETE_FEED = 14;
        int POPUP_DELETE_MY_WORKOUT_IMAGES = 15;
        int POPUP_ACTIVATE_ACCOUNT = 16;
        int POPUP_DEACTIVATE_ACCOUNT = 17;
        int POPUP_GCM_NOTIFICATION_STATUS_UPDATE = 18;
        int POPUP_SAVE_MY_PROFILE = 19;
        int POPUP_CANCEL_FEEDBACK = 20;
        int POPUP_VIDEO_AUDIO_FEATURE = 21;
    }

    public interface BottomBarTabConstants {
        int PROGRAMS_TAB = 0;
        int FEED_TAB = 1;
        int MY_WORKOUT_TAB = 2;
        int NOTIFICATION_TAB = 3;
        int MORE_TAB = 4;
    }

    public interface IMAGE_DIRECTORY_CONSTANTS {
        String PROGRAMS_IMAGES = "Programs/";
        String SHARED_IMAGES = "Shared/";
    }

    public interface SERVICE_TAGS {

        /////SERVICE TAG FOR DIFFERENTIATION ON ENTRIES IN PROGRAM Table from ProgramListService and MyWorkout Service
        int PROGRAM_LIST_SERVICE = 0;
        int MYWORKOUT_PROGRAMS_LIST_SERVICE = 1;

        /////LikeUnlikeReportFeed Service ///////////
        int LIKE_UNLIKE_FEED = 1;
        int REPORT_FEED = 2;

        /////AddEditDeleteFeed Service ///////////
        int ADD_FEED = 1;
        int EDIT_FEED = 2;
        int DELETE_FEED = 3;

        /////AddDeleteReportFeed Service ///////////
        int REPORT_COMMENT = 1;
        int ADD_COMMENT = 2;
        int DELETE_COMMENT = 3;

        /////DELETE IMAGES OF GALLERY, BEFORE,AFTER OR USER PROFILE Service ///////////
        int DELETE_USER_PHOTO = 1;
        int DELETE_PROGRAM_PHOTO = 2;
    }

    public interface GCM_CONSTANTS {
        // Google Project Number
        String GOOGLE_PROJECT_ID = "911161344283";
        String MESSAGE_KEY = "message";
        String CUSTOM_KEY = "custom";
        String BADGE = "badge";
        String SUBJECT_KEY = "subject";
        String REGISTER_AGAIN = "register_again";
        String FROM_NOTIFICATION = "from_notification";
    }

    public interface CUSTOM_IMAGEVIEW_TAGS {
        String WORKOUT_TAG = "Workout";
        String FEED_IMAGE_TAG = "Feed";
        String FEED_USER_IMAGE_TAG = "FeedUser";
        String FEED_FULL_IMAGE_TAG = "FeedFull";
        String PENTAGONAL_FULL_IMAGE_TAG = "PentagonalFull";
        String PENTAGONAL_BEFORE_AFTER_IMAGE_TAG = "PentagonalBeforeAfter";
        String PENTAGONAL_USER_PROFILE_TAG = "PentagonalUserProfile";
        String PENTAGONAL_BEFORE_AFTER_IMAGE_NOT_PRESENT_TAG = "0";
    }

    public interface ALPHA_VALUES {
        float BUTTON_ALPHA_ADDED = 0.5f;
        float BUTTON_ALPHA_ADD = 1.0f;
    }
}
