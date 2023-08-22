package com.uco.yourplus.crosscuttingYourPlus.helper;

import java.util.UUID;

public class UUIDHelper {

    private static final String DEFAULT_UUID_AS_STRING = "00000000-0000-0000-0000-00000000000";
    private static final UUID DEFAULT_UUID = UUID.fromString(DEFAULT_UUID_AS_STRING);

    private UUIDHelper(){
        super();
    }

    public static UUID getDefaultUUID(final UUID value){
        return ObjectHelper.getDefaultIfNull(value, DEFAULT_UUID);
    }

    public static UUID getNewUUID(){
        UUID uuid;

        do{
            uuid = UUID.randomUUID();
        } while (isDefaultUUID(uuid));
        return uuid;
    }

    public static boolean isDefaultUUID(final UUID value){
        return DEFAULT_UUID.equals(value);
    }
}
