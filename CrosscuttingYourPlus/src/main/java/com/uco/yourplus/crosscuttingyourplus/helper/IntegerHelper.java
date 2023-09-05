package com.uco.yourplus.crosscuttingyourplus.helper;

import static com.uco.yourplus.crosscuttingyourplus.helper.ObjectHelper.getDefaultIfNull;

public class IntegerHelper {
    public static final int ZERO = 0;

    public static int getDefaultInteger(int value){
        return getDefaultIfNull(value,ZERO);
    }
}
