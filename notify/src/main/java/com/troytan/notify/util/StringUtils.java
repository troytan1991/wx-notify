package com.troytan.notify.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class StringUtils {

    private static String CHARSET = "utf-8";

    public static String base64Encode(String src) {
        if (src == null) {
            return null;
        }
        try {
            return Base64.getEncoder().encodeToString(src.getBytes(CHARSET));
        } catch (UnsupportedEncodingException e) {
            return src;
        }
    }

    public static String base64Decode(String src) {
        if (src == null) {
            return null;
        }
        try {
            return new String(Base64.getDecoder().decode(src), CHARSET);
        } catch (UnsupportedEncodingException e) {
            return src;
        }
    }

    public static void main(String[] args) {
        String name = base64Encode("troytan");
        System.out.println(name);
        System.out.println(base64Decode(name));
    }
}
