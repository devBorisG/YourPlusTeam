package com.uco.yourplus.crosscuttingYourPlus.helper;

import static com.uco.yourplus.crosscuttingYourPlus.helper.ObjectHelper.getDefaultIfNull;

public class IntegerHelper {
    public static final int ZERO = 0;

    public static int getDefaultInteger(int value){
        return getDefaultIfNull(value,ZERO);
    }
}
