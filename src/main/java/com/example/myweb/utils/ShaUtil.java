package com.example.myweb.utils;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class ShaUtil {
    //將密碼以SHA-256演算加密
    public static String getSHA256(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            return new BigInteger(1, digest).toString(16).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("無法建立 SHA-256 雜湊實例：" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
