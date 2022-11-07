package com.pink.register;

import java.math.BigInteger;
import java.util.Base64;
import java.util.UUID;

public class Utils {
    public static BigInteger getNextId(){

        String lUUID = String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));
        return new BigInteger(lUUID.trim());
    }

    public static String toBase64(String inString){
        return Base64.getEncoder().encodeToString(inString.getBytes());
    }
}
