package com.example.springboot8.utils;

import java.nio.charset.StandardCharsets;

public class SecretUtils {

    /**
     * 加密
     * @param param
     * @return
     */
    public static String encrypt(String param){
        String result = "";

        try {
            byte[] data = param.getBytes(StandardCharsets.UTF_8);
            byte[] resHash = hash(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] hash(byte[] data){
        String result = "";

        return null;
    }
}
