package com.uco.yourplus.crosscuttingYourPlus.helper;

import java.util.Objects;

import static com.uco.yourplus.crosscuttingYourPlus.helper.ObjectHelper.getDefaultIfNull;

public class StringHelper {

    public static final String EMPTY = "";

    private StringHelper() {
        super();
    }

    public static String getDefaultString(String value, String defaultValue) {
        return getDefaultIfNull(value, defaultValue);
    }

    public static String getDefaultString(String value) {
        return getDefaultIfNull(value, EMPTY);
    }

    public static String applyTrim(String value) {
        return getDefaultString(value).trim();
    }

    public static boolean isEmpty(String value) {
        return Objects.equals(value, EMPTY);
    }

    public static boolean isOnlyWordsAndSpace(String value) {
        return value.matches("[a-zA-Z ]+");
    }

    public static boolean isOnlyWords(String value){
        return value.matches("^[a-zA-Z]+$");
    }

    public static boolean verifyCelNumber(String number) {
        return number.matches("\\+\\d{12}");
    }

    public static boolean verifyEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
}
