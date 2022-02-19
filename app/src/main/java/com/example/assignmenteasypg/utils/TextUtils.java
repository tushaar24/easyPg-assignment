package com.example.assignmenteasypg.utils;

public class TextUtils {
    public static boolean checkCredentials(String emailId, String password){
        return !emailId.isEmpty() && !password.isEmpty();
    }

    public static boolean checkCredentials(String emailId, String password, String confirmPassword){
        return !emailId.isEmpty() && !password.isEmpty() && password.equals(confirmPassword) && password.length() >= 6;
    }
}
