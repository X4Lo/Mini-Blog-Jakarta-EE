package com.blog.tools;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Tools {
    private static final Logger logger = LogManager.getLogger(Tools.class);

    public Tools() throws InstantiationException {
        throw new InstantiationException("YOU CANNOT INSTANTIATE THIS CLASS, use getInstance()");
    }


    public static void log(Level level, String message) {
        logger.log(level, message);
    }


    public static String toMd5(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] inputBytes = value.getBytes();
            byte[] hashBytes = md.digest(inputBytes);
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            log(Level.ERROR, e.getMessage());
            return null;
        }
    }

}

