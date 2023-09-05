package com.uco.yourplus.crosscuttingyourplus.exceptions.repository;

import com.uco.yourplus.crosscuttingyourplus.exceptions.YourPlusCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.enumeration.LayerException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;

import java.io.Serial;

import static com.uco.yourplus.crosscuttingyourplus.helper.StringHelper.EMPTY;

public class RepositoryCustomException extends YourPlusCustomException {
    @Serial
    private static final long serialVersionUID = -7581138972968503560L;

    private RepositoryCustomException(Exception rootException, String technicalMessage, String userMessage) {
        super(rootException, technicalMessage, userMessage, LayerException.REPOSITORY);
    }

    public static YourPlusCustomException create(final String userMesssage, final String technicalMessage) {
        return new RepositoryCustomException(new Exception(), technicalMessage, userMesssage);
    }

    public static YourPlusCustomException create(final Exception rootException, final String userMessage,
                                                 final String technicalMessage) {
        return new RepositoryCustomException(rootException, technicalMessage, userMessage);
    }

    public static YourPlusCustomException createTechnicalException(final String technicalMessage) {
        return new RepositoryCustomException(new Exception(), technicalMessage, EMPTY);
    }

    public static YourPlusCustomException createTechnicalException(final Exception rootException, final String technicalMessage){
        return new RepositoryCustomException(rootException, technicalMessage, EMPTY);
    }

    public static YourPlusCustomException wrapException(final String message, final YourPlusCustomException exception){
        if(exception.isTechnicalException()){
            return ServiceCustomException.createBusinessException(message, exception);
        }
        return exception;
    }
}

