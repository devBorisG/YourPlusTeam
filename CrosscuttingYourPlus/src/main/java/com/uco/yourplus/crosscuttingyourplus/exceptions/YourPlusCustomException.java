package com.uco.yourplus.crosscuttingyourplus.exceptions;

import com.uco.yourplus.crosscuttingyourplus.exceptions.enumeration.LayerException;

import java.io.Serial;

import static com.uco.yourplus.crosscuttingyourplus.helper.ObjectHelper.getDefaultIfNull;
import static com.uco.yourplus.crosscuttingyourplus.helper.StringHelper.isEmpty;

public class YourPlusCustomException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    private final String userMessage;
    private final LayerException layerException;

    protected YourPlusCustomException(final Throwable rootException, final String technicalMessage, final String userMessage, final LayerException layerException){
        super(technicalMessage, getDefaultIfNull(rootException, new Exception()));
        this.userMessage = userMessage;
        this.layerException = layerException;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public LayerException getLayerException() {
        return layerException;
    }

    public final boolean isTechnicalException(){
        return isEmpty(getUserMessage());
    }
}
