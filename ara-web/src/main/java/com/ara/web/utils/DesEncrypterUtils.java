/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.ara.web.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-5-30 下午4:32
 */
public class DesEncrypterUtils {

    private static final Logger logger = Logger.getLogger(DesEncrypterUtils.class);

    private static SecretKey secretKey;

    private static final byte[] DEFUALT_SALT = {(byte) 0xB9, (byte) 0xAB, (byte) 0xD8, (byte) 0x42, (byte) 0x66, (byte) 0x45, (byte) 0xF3, (byte) 0x13};

    private static final String PEB_WITH_MD5_AND_DES = "PBEWithMD5AndDES";

    private static final int ITERATION_COUNT = 17;

    private static final String secret = "ara";

    static {
        KeySpec keySpec = new PBEKeySpec(secret.toCharArray(), DEFUALT_SALT, ITERATION_COUNT);
        try {
            secretKey = SecretKeyFactory.getInstance(PEB_WITH_MD5_AND_DES).generateSecret(keySpec);
        } catch (Exception e) {
            logger.error("DesEncrypterUtils init exception", e);
        }
    }

    public static String encrypt(String str) {
        try {
            SecretKeySpec key = getSecretKeySpec();
            Cipher ecipher = Cipher.getInstance(key.getAlgorithm());
            PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(DEFUALT_SALT, ITERATION_COUNT);
            ecipher.init(Cipher.ENCRYPT_MODE, key, pbeParameterSpec);
            byte[] utf8 = str.getBytes("utf-8");
            byte[] enc = ecipher.doFinal(utf8);
            return Base64.encodeBase64String(enc);
        } catch (Exception e) {
            logger.error("encrypt exception", e);
        }
        return null;
    }

    public static String decrypt(String str) {
        try {
            SecretKeySpec key = getSecretKeySpec();
            Cipher dcipher = Cipher.getInstance(key.getAlgorithm());
            PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(DEFUALT_SALT, ITERATION_COUNT);
            dcipher.init(Cipher.DECRYPT_MODE, key, pbeParameterSpec);
            byte[] dec = Base64.decodeBase64(str);
            byte[] utf8 = dcipher.doFinal(dec);
            return new String(utf8, "utf-8");
        } catch (Exception e) {
            logger.error("decrypt exception", e);
        }
        return null;
    }

    private static SecretKeySpec getSecretKeySpec() {
        return new SecretKeySpec(secretKey.getEncoded(), secretKey.getAlgorithm());
    }

}
