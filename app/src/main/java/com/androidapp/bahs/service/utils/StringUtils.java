package com.androidapp.bahs.service.utils;
import android.util.Base64;
import java.util.UUID;
import java.util.regex.Pattern;


public class StringUtils {

    public String getEncodedString(String message) {
        String enCodedStr = message;
        try {
            byte[] data = message.getBytes("UTF-8");
            enCodedStr = Base64.encodeToString(data, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return enCodedStr;
    }

    public String getDeCodeString(String message) {
        String decodedString = message;
        try {
            byte[] msgdata = Base64.decode(message, Base64.DEFAULT);
            decodedString = new String(msgdata, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodedString;
    }

    public String getRandomImageName() {

        StringBuffer sb = new StringBuffer();
        sb.append(UUID.randomUUID().toString());
        sb.append("_a_");
        sb.append(System.currentTimeMillis());
        sb.append(".png");
        return sb.toString();
    }

    public String getHeightFromTextview(String height) {
        if (!checkStringEmpty(height)) {
            StringBuilder sheight = new StringBuilder(height);
            sheight.replace(sheight.indexOf("f") - 1, sheight.indexOf("t") + 2, ".");
            sheight.replace(sheight.indexOf("i") - 1, sheight.indexOf("n") + 1, "");
            return sheight.toString();
        }
        return "";
    }

    public String getWeightFromTextview(String height) {
        if (!checkStringEmpty(height)) {
            StringBuilder sheight = new StringBuilder(height);
            sheight.replace(sheight.indexOf("l"), sheight.indexOf("s") + 1, "");
            return sheight.toString().trim();

        }
        return "";
    }

    public String getAgeFromTextview(String height) {
        if (!checkStringEmpty(height)) {
            StringBuilder sheight = new StringBuilder(height);
            sheight.replace(sheight.indexOf("y"), sheight.indexOf("s") + 1, "");
            return sheight.toString().trim();
        }
        return "";
    }

    public boolean isUsernameValid(String username) {
        Pattern emailPattern = Pattern.compile("([a-z0-9_-]{3,15})");
        Boolean validation = emailPattern.matcher(username).matches();
        return validation;
    }

    public boolean isEmailValid(String email) {
        Pattern emailPattern = Pattern.compile(".+@.+\\.[a-z]+");
        Boolean validation = emailPattern.matcher(email).matches();
        return validation;
    }

    public boolean isPasswordValid(String email) {
        Pattern passPattern = Pattern.compile("((?=.*[a-z])(?=.*\\d).{6,16})");
        Boolean validation = passPattern.matcher(email).matches();
        return validation;
    }

    public String FirstLetterOnly_UpperCase(String word) {
        String result = "";
        if (word.length() > 0) {
            char c = word.charAt(0);
            result = Character.toUpperCase(c) + "";
        }
        if (!result.equals("M") && !result.equals("F"))
            result = "N";

        return result;
    }

    public String FirstLetterInUpperCase(String word) {

        String result = "";
        if (word.length() > 0) {
            char c = word.charAt(0);
            String splitedString = word.substring(1, word.length());
            result = Character.toUpperCase(c) + splitedString;
        }
        return result;
    }

    public boolean checkStringEmpty(String word) {
        if (word != null && word.trim().length() > 0) {
            return false;
        }
        return true;
    }

    public int getIntFromString(String st) {
        return Integer.parseInt(st);
    }

    public String getStringTextAccordingCount(String text, int count) {
        String finalText = text;
        if (count > 1) {
            finalText = text + "s";
        }
        return finalText;
    }


    public boolean checkGenderValue(String gender) {
        if (!checkStringEmpty(gender)) {
            if (!gender.equals("N") && !gender.equals("None")) {
                return true;
            }
        }
        return false;
    }

    public boolean getJoiningOutMailListFromValue(String mailListVal) {
        if (mailListVal.equalsIgnoreCase("No")) {
            return false;
        }
        return true;
    }
}