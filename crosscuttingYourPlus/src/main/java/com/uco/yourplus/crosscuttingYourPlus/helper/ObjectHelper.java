package com.uco.yourplus.crosscuttingYourPlus.helper;

public class ObjectHelper {
    private ObjectHelper(){
        super();
    }

    public static <T> boolean isNull(T value){
        return value == null;
    }

    public static <T> T getDefaultIfNull(T value, T defaultValue){
        return (isNull(value))?defaultValue : value;
    }
}
