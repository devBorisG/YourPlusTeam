package com.uco.yourplus.crosscuttingYourPlus.exceptions;

import com.uco.yourplus.crosscuttingYourPlus.exceptions.enumeration.LayerException;

import java.io.Serial;

import static com.uco.yourplus.crosscuttingYourPlus.helper.ObjectHelper.getDefaultIfNull;
import static com.uco.yourplus.crosscuttingYourPlus.helper.StringHelper.isEmpty;

public class YourPlusCustomException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    private String userMessage;
    private LayerException layerException;

    protected YourPlusCustomException(final Throwable rootException, final String technicalMessage, final String userMessage, final LayerException layerException){
        super(technicalMessage, getDefaultIfNull(rootException, new Exception()));
        setUserMessage(userMessage);
        setLayerException(layerException);
    }

    public String getUserMessage() {
        return userMessage;
    }

    private void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public LayerException getLayerException() {
        return layerException;
    }

    private void setLayerException(LayerException layerException) {
        this.layerException = layerException;
    }

    public final boolean isTechnicalException(){
        return isEmpty(getUserMessage());
    }
}
