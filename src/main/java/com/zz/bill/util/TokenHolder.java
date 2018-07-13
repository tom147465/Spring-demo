package com.zz.bill.util;

import java.util.HashMap;
import java.util.Map;

public class TokenHolder {

    private static final Map<String, Integer> TOKEN_MAP = new HashMap<>();

    public static Integer getUserIdByToken(String token){
        return TOKEN_MAP.get(token);
    }

    public static void setTokenForUserId(String token, Integer userId){
        TOKEN_MAP.put(token,userId);
    }
    public static String getToken(String account){
        return "token " + account;
    }
}
