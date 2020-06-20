package com.latam.birthday.test.exceptions;

public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = -2969518002634364652L;

    /**
     * Instantiates a new busines logic
     */
    public BusinessException() {
        super();
    }

    /**
     * Instantiates a new busines logic
     *
     * @param message the message
     * @param cause the cause
     * @param enableSuppression the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public BusinessException(String message, Exception cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Instantiates a new busines logic
     *
     * @param message the message
     * @param cause the cause
     */
    public BusinessException(String message, Exception cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new already exist exception.
     *
     * @param message the message
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * Instantiates a new busines logic
     *
     * @param cause the cause
     */
    public BusinessException(Exception cause) {
        super(cause);
    }
}

