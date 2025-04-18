package com.lms.demofx.Utils;

public class ValidationUtils {
    public static boolean validateEmail(String email){
        String emailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        return email.matches(emailRegex);
    }
}
