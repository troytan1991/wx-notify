package com.troytan.notify.util;

import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class AESUtils {

    private static Cipher cipher; // 加解密核心实例

    /**
     * aes解密
     *
     * @author troytan
     * @date 2018年7月9日
     * @param encryptedData 加密数据
     * @param iv 解密向量
     * @param sessionKey 解密key
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptedData, String iv, String sessionKey) throws Exception {

        Decoder decoder = Base64.getDecoder();
        byte[] keyBytes = decoder.decode(sessionKey);
        byte[] contentBytes = decoder.decode(encryptedData);
        byte[] ivBytes = decoder.decode(iv);

        Key keySpec = new SecretKeySpec(keyBytes, "AES");
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(ivBytes));
        if (cipher == null) {
            Security.addProvider(new BouncyCastleProvider());
            cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
        }
        cipher.init(Cipher.DECRYPT_MODE, keySpec, params);
        return new String(cipher.doFinal(contentBytes), "utf-8");
    }

}
