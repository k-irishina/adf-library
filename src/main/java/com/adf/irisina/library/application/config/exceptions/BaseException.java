package com.adf.irisina.library.application.config.exceptions;

/**
 * Basic exception for application
 */
public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 8989317653934241392L;

    BaseException(String message) {
        super(message);
    }
}
