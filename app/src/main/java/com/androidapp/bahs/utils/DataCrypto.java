package com.androidapp.bahs.utils;

import android.util.Base64;

import com.androidapp.bahs.utils.constantvariable.AppMessages;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DataCrypto {
    private static Cipher enCipher = null;
    private static Cipher deCipher = null;
    private static byte baOurKey[] = null;
    private static IvParameterSpec ourIPS = null;
    private static SecretKey secretKey = null;
    private static String strKey = AppMessages.CrytoKey.KEY;


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
    public static String decryptStringData(String strCardNumber) {
        String decryptedStringData = null;
        try {
            if (strKey != null) {
                setKey(strKey.getBytes());
                byte iv[] = {1, 35, 69, 103, -119, -85, -51, -17};
                setIV(iv);
                decryptedStringData = convertBack(byteToHex(decrypt(hexToByte(strCardNumber))));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return decryptedStringData;
    }

    public static String encryptStringData(String strCardNumber) {
        String encryptedStringData = null;
        try {
            if (strKey != null) {
                setKey(strKey.getBytes());
                byte iv[] = {1, 35, 69, 103, -119, -85, -51, -17};
                setIV(iv);
                encryptedStringData = byteToHex(encrypt(hexToByte(convertSafe(strCardNumber))));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return encryptedStringData;
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
    public static void setKey(byte baKey[]) {
        baOurKey = baKey;
    }

    public static void setIV(byte baIV[]) {
        ourIPS = new IvParameterSpec(baIV);
    }

    private static String byteToHex(byte in[]) {
        byte ch = 0;
        if (in == null || in.length <= 0) {
            return null;
        }
        String pseudo[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        StringBuffer out = new StringBuffer(in.length * 2);
        for (int i = 0; i < in.length; i++) {
            ch = (byte) (in[i] & 0xf0);
            ch >>>= 4;
            ch &= 0xf;
            out.append(pseudo[ch]);
            ch = (byte) (in[i] & 0xf);
            out.append(pseudo[ch]);
        }

        String rslt = new String(out);
        return rslt;
    }

    private static byte[] hexToByte(String creditCardData) {
         byte creditCardByte[] = creditCardData.getBytes();
        byte creditCardByteOut[] = new byte[8];
        int tempCnt = 0;
        for (int cnt = 0; cnt < creditCardData.length(); cnt += 2) {
            creditCardByte[cnt] = (byte) (creditCardByte[cnt] - (creditCardByte[cnt] >= 58 ? 55 : 48));
            creditCardByte[cnt + 1] = (byte) (creditCardByte[cnt + 1] - (creditCardByte[cnt + 1] >= 58 ? 55 : 48));
            creditCardByteOut[tempCnt++] = (byte) ((creditCardByte[cnt] << 4) + creditCardByte[cnt + 1]);
        }

        return creditCardByteOut;
    }
    public static String convertSafe(String Data) {
        if (Data.length() < 16) {
            Data = Data + "BBBBBBBBBBBBBBBBBBBBB";
        }
        Data = Data.substring(0, 16);
        return Data.replace('0', 'A');
    }

    public static String convertBack(String in) {
        return in.replace('A', '0').replace('B', ' ').trim();
    }

}

