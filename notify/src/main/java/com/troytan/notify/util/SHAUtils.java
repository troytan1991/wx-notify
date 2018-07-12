package com.troytan.notify.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAUtils {

    /**
     * SHA1加密字符串
     *
     * @author troytan
     * @date 2018年5月17日
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String getSha1(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update(data.getBytes());
        return bytes2Hex(md.digest());
    }

    /**
     * 字节数组转化为16进制字符
     *
     * @author troytan
     * @date 2018年5月17日
     * @param bytes
     * @return
     */
    private static String bytes2Hex(byte[] bytes) {
        char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
}
