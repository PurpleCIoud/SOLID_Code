package net.ewari;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {
    private final int shift;
    private final Algo algorithm;

    Encryptor(Algo algorithm) {
        this.algorithm = algorithm;
        this.shift = 0;
    }

    Encryptor(Algo algorithm, int shift) {
        this.algorithm = algorithm;
        this.shift = shift;
    }

    // A method that calls other methods depending on what algorithm we are using
    public String encrypt(String input) {
        String result = null;
         switch (this.algorithm) {
            case SHA256 : {
                result = getSHA256(input);
                break;}
            case SHA512 : {
                result = getSHA512(input);
                break;}
            case CAESAR : {
                result = getCaesar(input, this.shift);
                break;}
            case MD5    : {
                result = getMD5(input);
                break;}
        }
        return result;
    }

    // A Caesar cipher that shifts all letters using bit shift
    private String getCaesar(String stringToCipher, Integer shift) {
        byte[] bytes = stringToCipher.getBytes();
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Character.toChars(aByte + shift.byteValue()));
        }
        return sb.toString();

    }

    //Industry standard SHA256 Algorithm without any slat added
    private String getSHA256(String stringToHash) {
        String genPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(stringToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            genPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return genPassword;
    }

    private String getSHA512(String stringToHash) {
        String genPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(stringToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            genPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return genPassword;
    }

    private String getMD5(String stringToHash) {
        String genPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(stringToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            genPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return genPassword;
    }
}
