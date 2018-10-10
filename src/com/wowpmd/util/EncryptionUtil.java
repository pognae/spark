package com.wowpmd.util;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class EncryptionUtil {
    static PooledPBEStringEncryptor encryptor = null;
    static {
        encryptor = new PooledPBEStringEncryptor();
        encryptor.setPoolSize(4);
        //  There are various approaches to pull this configuration via system level properties.
        encryptor.setPassword("password1234567890@");
        encryptor.setAlgorithm("PBEWITHMD5ANDDES");
    }

    public static String encrypt(String input) {
        return encryptor.encrypt(input);
    }

    public static String decrypt(String encryptedMessage) {
        return encryptor.decrypt(encryptedMessage);
    }

    public static void main(String[] args) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("test");

        String url = encryptor.encrypt("jdbc:mysql://127.0.0.1:3306/test");
        String username = encryptor.encrypt("root");
        String password = encryptor.encrypt("root");

        System.out.println("url=" + url);
        System.out.println("username=" + username);
        System.out.println("password=" + password);
    }
}
