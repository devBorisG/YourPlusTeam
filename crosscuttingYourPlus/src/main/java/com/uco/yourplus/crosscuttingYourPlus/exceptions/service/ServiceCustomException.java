package com.uco.yourplus.crosscuttingYourPlus.exceptions.service;

import com.uco.yourplus.crosscuttingYourPlus.exceptions.YourPlusCustomException;
import com.uco.yourplus.crosscuttingYourPlus.exceptions.enumeration.LayerException;

import java.io.Serial;

import static com.uco.yourplus.crosscuttingYourPlus.helper.StringHelper.EMPTY;

public class ServiceCustomException extends YourPlusCustomException {
    @Serial
    private static final long serialVersionUID = -7581138972968503560L;

    private ServiceCustomException(Exception rootException, String technicalMessage, String userMessage) {
        super(rootException, technicalMessage, userMessage, LayerException.SERVICE);
    }

    public static YourPlusCustomException create(final String userMesssage, final String technicalMessage) {
        return new ServiceCustomException(new Exception(), technicalMessage, userMesssage);
    }

    public static YourPlusCustomException create(final Exception rootException, final String userMessage,
                                                 final String technicalMessage) {
        return new ServiceCustomException(rootException, technicalMessage, userMessage);
    }

    public static YourPlusCustomException createTechnicalException(final String technicalMessage) {
        return new ServiceCustomException(new Exception(), technicalMessage, EMPTY);
    }

    public static YourPlusCustomException createTechnicalException(final Exception rootException, final String technicalMessage){
        return new ServiceCustomException(rootException, technicalMessage, EMPTY);
    }

    public static YourPlusCustomException createUserException(final String userMessage){
        return new ServiceCustomException(null, userMessage, userMessage);
    }

    public static YourPlusCustomException createBusinessException(final String businessMessage, final Exception rootException){
        return new ServiceCustomException(rootException, businessMessage, EMPTY);
    }

    public static YourPlusCustomException wrapException(final String message, final YourPlusCustomException exception){
        if(exception.isTechnicalException()){
            return ServiceCustomException.createBusinessException(message, exception);
        }
        return exception;
    }
}