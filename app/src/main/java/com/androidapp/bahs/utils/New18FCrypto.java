package com.androidapp.bahs.utils;

import android.util.Base64;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class New18FCrypto {
    private static Cipher enCipher = null;
    private static Cipher deCipher = null;
    private static byte baOurKey[] = null;
    private static IvParameterSpec ourIPS = null;
    private static SecretKey secretKey = null;

    public New18FCrypto() {
    }



    private static boolean init() {
        boolean bRet = true;
        try {
            secretKey = new SecretKeySpec(baOurKey, "DESede");
            enCipher = Cipher.getInstance("DESede/CBC/NoPadding");
            deCipher = Cipher.getInstance("DESede/CBC/NoPadding");
        } catch (Exception ex) {
            bRet = false;
        }
        return bRet;
    }

    public static byte[] encrypt(byte plainText[]) {
        if (secretKey == null && !init()) {
            return null;
        }
        byte byteCipherText[] = (byte[]) null;
        try {
            enCipher.init(1, secretKey, ourIPS);
            byteCipherText = enCipher.doFinal(plainText);
        } catch (InvalidKeyException invalidKey) {
            System.err.println(" Invalid Key " + invalidKey);
        } catch (IllegalBlockSizeException illegalBlockSize) {
            System.err.println(" Illegal Bolck Size " + illegalBlockSize);
        } catch (InvalidAlgorithmParameterException illegalBlockSize) {
            System.err.println(" Invalid Algorithm Parameter " + illegalBlockSize);
        } catch (BadPaddingException illegalBlockSize) {
            System.err.println(" Bad Padding " + illegalBlockSize);
        }
        return byteCipherText;
    }

    public static byte[] decrypt(byte cipherText[]) {
        if (secretKey == null && !init()) {
            return null;
        }
        byte byteClearText[] = (byte[]) null;
        try {
            deCipher.init(2, secretKey, ourIPS);
            byteClearText = deCipher.doFinal(cipherText);
        } catch (InvalidKeyException invalidKey) {
            System.err.println(" Invalid Key " + invalidKey);
        } catch (IllegalBlockSizeException illegalBlockSize) {
            System.err.println(" Illegal Bolck Size " + illegalBlockSize);
        } catch (InvalidAlgorithmParameterException illegalBlockSize) {
            System.err.println(" Invalid Algorithm Parameter " + illegalBlockSize);
        } catch (BadPaddingException illegalBlockSize) {
            System.err.println(" Bad Padding " + illegalBlockSize);
        }
        return byteClearText;
    }


    public static String prefEncrypt(String input) {
        // Simple encryption, not very strong!
        int flags = Base64.NO_WRAP | Base64.URL_SAFE;
        return Base64.encodeToString(input.getBytes(), flags);
    }

    public static String prefDecrypt(String input) {
        int flags = Base64.NO_WRAP | Base64.URL_SAFE;
        try {
            return new String(Base64.decode(input.getBytes(), flags));
        }catch (Exception e){
            return "";
        }
    }

}

