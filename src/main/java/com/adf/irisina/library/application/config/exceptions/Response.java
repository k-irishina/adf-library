package com.adf.irisina.library.application.config.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;

@Data
public class Response implements Serializable {

    private static final long serialVersionUID = -4611063868984506818L;

    private final Date timestamp = new Date();
    private String status;
    private String error;
    private String message;
    private String path;

    public static Response generateError(HttpServletRequest request, HttpStatus status, String message) {
        Response error = new Response();
        error.setStatus(String.valueOf(status.value()));
        error.setError(status.getReasonPhrase());
        error.setMessage(message);
        error.setPath(request.getContextPath() + request.getServletPath());

        return error;
    }
}
