package com.androidapp.bahs.service.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.androidapp.bahs.service.AppContext;
import com.androidapp.bahs.service.utils.ServiceConstants;
import com.androidapp.bahs.utils.device.LogUtils;

public class AppSharedPreferences {

    private static AppSharedPreferences instance;


    private final String mAccessToken = "mAccessToken";
    private final String mName = "name";
    private final String mUser_name = "user_name";
    private final String mEmail = "email";
    private final String mAge = "age";
    private final String mGender = "gender";
    private final String mHeight = "height";
    private final String mWeight = "weight";
    private final String mJoin_out_mailing_list = "join_out_mailing_list";
    private final String mImage_path = "image_path";
    private final String mCustomIMEO = "mCustomIMEO";
    private final String mLoggedIn = "mLoggedIn";
    private final String mFb_Id = "mFb_Id";
    private final String mUserId = "mUserId";
    private final String mCroppedImageName = "mCroppedImageName";
    private final String mProgramCompletedIdList = "mProgramCompletedIdList";
    private final String mAfterImageProgramIdList = "mAfterImageProgramIdList";
    private final String mBeforeImageProgramIdList = "mBeforeImageProgramIdList";
    private final String mInviteFriendsFragment = "mInviteFriendsFragment";
    private final String mCurrentProgramId = "mCurrentProgramId";
    private final String mCurrentGalleryProgramId_MyWorkout = "mCurrentGalleryProgramId_MyWorkout";
    private final String mCurrentGalleryProgramId_More = "mCurrentGalleryProgramId_More";
    private final String mCurrentProgramWeekId = "mCurrentProgramWeekId";
    private final String mCurrentProgramWorkoutId = "mCurrentProgramWorkoutId";
    private final String mCurrentFeedId = "mCurrentFeedId";
    private final String mGcmId = "mGcmId";
    private final String mVideoAudioStatus = "mVideoAudioStatus";
    private final String mBatchCount = "mBatchCount";
    private final String mGCMNotificationStatus = "mGCMNotificationStatus";
    private final String mPostingAgreementPopupStatus = "mPostingAgreementPopupStatus";
    private final String mCurrentBottomActiveTab = "mCurrentBottomActiveTab";
    private final String mLastLoginActivityFragmentTag = "mLastLoginActivityFragmentTag";
    private final String mLastProgramTabFragmentTag = "mLastProgramTabFragmentTag";
    private final String mLastFeedTabFragmentTag = "mLastFeedTabFragmentTag";
    private final String mLastMyWorkoutTabFragmentTag = "mLastMyWorkoutTabFragmentTag";
    private final String mLastNotificationTabFragmentTag = "mLastNotificationTabFragmentTag";
    private final String mLastMoreTabFragmentTag = "mLastMoreTabFragmentTag";
    private final String mWalkThroughTutorialStatus = "mWalkThroughTutorialStatus";
    private final String mGetMyWorkoutListRunning = "mGetMyWorkoutListRunning";
    private final String mGetProgramListRunning = "mGetProgramListRunning";
    private final String mGetGalleryListRunning = "mGetGalleryListRunning";
    private final String mGetFeedListRunning = "mGetFeedListRunning";
    private final String mGetCommentListRunning = "mGetCommentListRunning";
    private final String mGetLikeListRunning = "mGetLikeListRunning";
    private final String mGetNotificationListRunning = "mGetNotificationListRunning";
    private final String mVideoAudioPopupStatus = "mVideoAudioPopupStatus";

    private String TAG = this.getClass().getSimpleName();
    private SharedPreferences mPrefs;
    private Editor mPrefsEditor;
    private String mCroppingToolValue = "mCroppingToolValue";
    private String mCroppedImagePath = "mCroppedImagePath";

    public AppSharedPreferences(Context context) {
        this.mPrefs = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        this.mPrefsEditor = mPrefs.edit();
    }

    public static AppSharedPreferences getInstance() {

        if (instance == null) {
            instance = new AppSharedPreferences(AppContext.getInstance().getContext());
        }
        return instance;
    }

    public void clearAllData() {
        boolean val = mPrefs.getBoolean(mInviteFriendsFragment, false);
        boolean val2 = mPrefs.getBoolean(mWalkThroughTutorialStatus, false);
        mPrefsEditor.clear();
        mPrefsEditor.putBoolean(mInviteFriendsFragment, val);
        mPrefsEditor.putBoolean(mWalkThroughTutorialStatus, val2);
        mPrefsEditor.putString(mGcmId, ServiceConstants.GCM_CONSTANTS.REGISTER_AGAIN);
        mPrefsEditor.commit();
    }

    public String getAccessToken() {
        return mPrefs.getString(mAccessToken, "");
    }

    public void setAccessToken(String value) {
        LogUtils.debug("setAccessToken = ", value);
        mPrefsEditor.putString(mAccessToken, value);
        mPrefsEditor.commit();
    }

    public void setCustomIMEINO(String value, Context context) {
        mPrefsEditor.putString(mCustomIMEO, value);
        mPrefsEditor.commit();
    }

    public String getCustomIMEINO(Context context) {
        return mPrefs.getString(mCustomIMEO, "NotFound");
    }

    public String getName() {
        return mPrefs.getString(mName, "");
    }

    public void setName(String value) {
        mPrefsEditor.putString(mName, value);
        mPrefsEditor.commit();
    }

    public String getUserName() {
        return mPrefs.getString(mUser_name, "");
    }

    public void setUserName(String value) {
        mPrefsEditor.putString(mUser_name, value);
        mPrefsEditor.commit();
    }

    public String getEmail() {
        return mPrefs.getString(mEmail, "");
    }

    public void setEmail(String value) {
        mPrefsEditor.putString(mEmail, value);
        mPrefsEditor.commit();
    }

    public String getAge() {
        return mPrefs.getString(mAge, "");
    }

    public void setAge(String value) {
        mPrefsEditor.putString(mAge, value);
        mPrefsEditor.commit();
    }

    public String getHeight() {
        return mPrefs.getString(mHeight, "");
    }

    public void setHeight(String value) {
        mPrefsEditor.putString(mHeight, value);
        mPrefsEditor.commit();
    }

    public String getWeight() {
        return mPrefs.getString(mWeight, "");
    }

    public void setWeight(String value) {
        mPrefsEditor.putString(mWeight, value);
        mPrefsEditor.commit();
    }

    public String getGender() {
        return mPrefs.getString(mGender, "");
    }

    public void setGender(String value) {
        mPrefsEditor.putString(mGender, value);
        mPrefsEditor.commit();
    }

    public String getJoinOutMailList() {
        return mPrefs.getString(mJoin_out_mailing_list, "");
    }

    public void setJoinOutMailList(String value) {
        mPrefsEditor.putString(mJoin_out_mailing_list, value);
        mPrefsEditor.commit();
    }

    public String getImagePath() {
        return mPrefs.getString(mImage_path, "");
    }

    public void setImagePath(String value) {
        mPrefsEditor.putString(mImage_path, value);
        mPrefsEditor.commit();
    }

    public boolean getmLoggedIn() {
        return mPrefs.getBoolean(mLoggedIn, false);
    }

    public void setmLoggedIn(boolean value) {
        mPrefsEditor.putBoolean(mLoggedIn, value);
        mPrefsEditor.commit();
    }

    public boolean getInviteFriendsFragmentVal() {
        return mPrefs.getBoolean(mInviteFriendsFragment, false);
    }

    public void setInviteFriendsFragmentVal(boolean value) {
        mPrefsEditor.putBoolean(mInviteFriendsFragment, value);
        mPrefsEditor.commit();
    }

    public boolean getWalkThroughTuttorialStatus() {
        return mPrefs.getBoolean(mWalkThroughTutorialStatus, false);
    }

    public void setWalkThroughTuttorialStatus(boolean value) {
        mPrefsEditor.putBoolean(mWalkThroughTutorialStatus, value);
        mPrefsEditor.commit();
    }

    public String getFacebookId() {
        return mPrefs.getString(mFb_Id, "");
    }

    public void setFacebookId(String value) {
        mPrefsEditor.putString(mFb_Id, value);
        mPrefsEditor.commit();
    }

    public String getUserId() {
        return mPrefs.getString(mUserId, "");
    }

    public void setUserId(String value) {
        mPrefsEditor.putString(mUserId, value);
        mPrefsEditor.commit();
    }

    public String getCroppedImageName() {
        return mPrefs.getString(mCroppedImageName, "");
    }

    public void setCroppedImageName(String value) {
        mPrefsEditor.putString(mCroppedImageName, value);
        mPrefsEditor.commit();
    }

    public String getBeforeImageProgramIdList() {
        return mPrefs.getString(mBeforeImageProgramIdList, "");
    }

    public void setBeforeImageProgramIdList(String value) {
        mPrefsEditor.putString(mBeforeImageProgramIdList, value);
        mPrefsEditor.commit();
    }

    public String getAfterImageProgramIdList() {
        return mPrefs.getString(mAfterImageProgramIdList, "");
    }

    public void setAfterImageProgramIdList(String value) {
        mPrefsEditor.putString(mAfterImageProgramIdList, value);
        mPrefsEditor.commit();
    }

    public String getProgramCompletedIdList() {
        return mPrefs.getString(mProgramCompletedIdList, "");
    }

    public void setProgramCompletedIdList(String value) {
        mPrefsEditor.putString(mProgramCompletedIdList, value);
        mPrefsEditor.commit();
    }

    public String getCurrentProgramId() {
        return mPrefs.getString(mCurrentProgramId, "");
    }

    public void setCurrentProgramId(String value) {
        mPrefsEditor.putString(mCurrentProgramId, value);
        mPrefsEditor.commit();
    }

    public String getCurrentFeedId() {
        return mPrefs.getString(mCurrentFeedId, "");
    }

    public void setCurrentFeedId(String value) {
        mPrefsEditor.putString(mCurrentFeedId, value);
        mPrefsEditor.commit();
    }


    public String getGcmId() {
        return mPrefs.getString(mGcmId, "");
    }

    public void setGcmId(String value) {
        mPrefsEditor.putString(mGcmId, value);
        mPrefsEditor.commit();
    }

    public String getBatchCount() {
        return mPrefs.getString(mBatchCount, "0");
    }

    public void setBatchCount(String value) {
        mPrefsEditor.putString(mBatchCount, value);
        mPrefsEditor.commit();
    }


    public String getCurrentGalleryProgramId_MyWorkout() {
        return mPrefs.getString(mCurrentGalleryProgramId_MyWorkout, "");
    }

    public void setCurrentGalleryProgramId_MyWorkout(String value) {
        mPrefsEditor.putString(mCurrentGalleryProgramId_MyWorkout, value);
        mPrefsEditor.commit();
    }

    public String getCurrentGalleryProgramId_More() {
        return mPrefs.getString(mCurrentGalleryProgramId_More, "");
    }

    public void setCurrentGalleryProgramId_More(String value) {
        mPrefsEditor.putString(mCurrentGalleryProgramId_More, value);
        mPrefsEditor.commit();
    }

    public String getCurrentProgramWeekId() {
        return mPrefs.getString(mCurrentProgramWeekId, "");
    }

    public void setCurrentProgramWeekId(String value) {
        mPrefsEditor.putString(mCurrentProgramWeekId, value);
        mPrefsEditor.commit();
    }

    public String getmCurrentProgramWorkoutId() {
        return mPrefs.getString(mCurrentProgramWorkoutId, "");
    }

    public void setCurrentProgramWorkoutId(String value) {
        mPrefsEditor.putString(mCurrentProgramWorkoutId, value);
        mPrefsEditor.commit();
    }

    public boolean getMyWorkoutListRunning() {
        return mPrefs.getBoolean(mGetMyWorkoutListRunning, false);
    }

    public void setMyWorkoutListRunning(boolean value) {
        mPrefsEditor.putBoolean(mGetMyWorkoutListRunning, value);
        mPrefsEditor.commit();
    }

    public boolean getProgramListRunning() {
        return mPrefs.getBoolean(mGetProgramListRunning, false);
    }

    public void setProgramListRunning(boolean value) {
        mPrefsEditor.putBoolean(mGetProgramListRunning, value);
        mPrefsEditor.commit();
    }

    public boolean getGalleryListRunning() {
        return mPrefs.getBoolean(mGetGalleryListRunning, false);
    }

    public void setGalleryListRunning(boolean value) {
        mPrefsEditor.putBoolean(mGetGalleryListRunning, value);
        mPrefsEditor.commit();
    }

    public boolean getFeedListRunning() {
        return mPrefs.getBoolean(mGetFeedListRunning, false);
    }

    public void setFeedListRunning(boolean value) {
        mPrefsEditor.putBoolean(mGetFeedListRunning, value);
        mPrefsEditor.commit();
    }


    public boolean getFeedCommentListRunning() {
        return mPrefs.getBoolean(mGetCommentListRunning, false);
    }

    public void setFeedCommentListRunning(boolean value) {
        mPrefsEditor.putBoolean(mGetCommentListRunning, value);
        mPrefsEditor.commit();
    }

    public int getGCMNotificationStatus() {
        return mPrefs.getInt(mGCMNotificationStatus, 1);
    }

    public void setGCMNotificationStatus(int value) {
        mPrefsEditor.putInt(mGCMNotificationStatus, value);
        mPrefsEditor.commit();
    }

    public boolean getFeedLikeListRunning() {
        return mPrefs.getBoolean(mGetLikeListRunning, false);
    }

    public void setFeedLikeListRunning(boolean value) {
        mPrefsEditor.putBoolean(mGetLikeListRunning, value);
        mPrefsEditor.commit();
    }

    public boolean getVideoAudioStatus() {
        return mPrefs.getBoolean(mVideoAudioStatus, false);
    }

    public void setVideoAudioStatus(boolean value) {
        mPrefsEditor.putBoolean(mVideoAudioStatus, value);
        mPrefsEditor.commit();
    }

    public boolean getNotificationListRunning() {
        return mPrefs.getBoolean(mGetNotificationListRunning, false);
    }

    public void setNotificationListRunning(boolean value) {
        mPrefsEditor.putBoolean(mGetNotificationListRunning, value);
        mPrefsEditor.commit();
    }

    public boolean getVideoAudioPopupStatus() {
        return mPrefs.getBoolean(mVideoAudioPopupStatus, false);
    }

    public void setVideoAudioPopupStatus(boolean value) {
        mPrefsEditor.putBoolean(mVideoAudioPopupStatus, value);
        mPrefsEditor.commit();
    }

    public boolean getPostingAgreementPopupStatus() {
        return mPrefs.getBoolean(mPostingAgreementPopupStatus, false);
    }

    public void setPostingAgreementPopupStatus(boolean value) {
        mPrefsEditor.putBoolean(mPostingAgreementPopupStatus, value);
        mPrefsEditor.commit();
    }

    public String getStringValue(String key) {
        return mPrefs.getString(key, "");
    }

    public void commitStringValue(String key, String value) {
        mPrefsEditor.putString(key, value);
        mPrefsEditor.commit();
    }

    public int getCurrentActiveBottomTab() {
        return mPrefs.getInt(mCurrentBottomActiveTab, ServiceConstants.BottomBarTabConstants.PROGRAMS_TAB);
    }

    public void setCurrentActiveBottomTab(int value) {
        mPrefsEditor.putInt(mCurrentBottomActiveTab, value);
        mPrefsEditor.commit();
    }

    public int getLastLoginActivityFragmentTag() {
        return mPrefs.getInt(mLastLoginActivityFragmentTag, ServiceConstants.FragmentTagConstants.FRAGMENT_LOGIN);
    }

    public void setLastLoginActivityFragmentTag(int value) {
        mPrefsEditor.putInt(mLastLoginActivityFragmentTag, value);
        mPrefsEditor.commit();
    }

    public int getLastProgramTabFragmentTag() {
        return mPrefs.getInt(mLastProgramTabFragmentTag, ServiceConstants.FragmentTagConstants.FRAGMENT_PROGRAM_LIST);
    }

    public void setLastProgramTabFragmentTag(int value) {
        mPrefsEditor.putInt(mLastProgramTabFragmentTag, value);
        mPrefsEditor.commit();
    }

    public int getLastMyWorkoutTabFragmentTag() {
        return mPrefs.getInt(mLastMyWorkoutTabFragmentTag, ServiceConstants.FragmentTagConstants.FRAGMENT_MY_WORKOUT);
    }

    public void setLastMyWorkoutTabFragmentTag(int value) {
        mPrefsEditor.putInt(mLastMyWorkoutTabFragmentTag, value);
        mPrefsEditor.commit();
    }


    public int getLastNotificationTabFragmentTag() {
        return mPrefs.getInt(mLastNotificationTabFragmentTag, ServiceConstants.FragmentTagConstants.FRAGMENT_NOTIFICATION);
    }

    public void setLastNotificationTabFragmentTag(int value) {
        mPrefsEditor.putInt(mLastNotificationTabFragmentTag, value);
        mPrefsEditor.commit();
    }


    public int getLastMoreTabFragmentTag() {
        return mPrefs.getInt(mLastMoreTabFragmentTag, ServiceConstants.FragmentTagConstants.FRAGMENT_MORE_OPTIONS);
    }

    public void setLastMoreTabFragmentTag(int value) {
        mPrefsEditor.putInt(mLastMoreTabFragmentTag, value);
        mPrefsEditor.commit();
    }

    public int getLastFeedTabFragmentTag() {
        return mPrefs.getInt(mLastFeedTabFragmentTag, ServiceConstants.FragmentTagConstants.FRAGMENT_FEED);
    }

    public void setLastFeedTabFragmentTag(int value) {
        mPrefsEditor.putInt(mLastFeedTabFragmentTag, value);
        mPrefsEditor.commit();
    }

    public void setCroppingToolEnable(boolean value) {
        mPrefsEditor.putBoolean(mCroppingToolValue, value);
        mPrefsEditor.commit();
    }

    public boolean getCroppingToolEnable() {
        return mPrefs.getBoolean(mCroppingToolValue, false);
    }

    public void setCroppedImagePath(String absolutePath) {
        mPrefsEditor.putString(mCroppedImagePath, absolutePath);
        mPrefsEditor.commit();
    }

    public String getCroppedImagePath() {
        return mPrefs.getString(mCroppedImagePath, "");
    }
}