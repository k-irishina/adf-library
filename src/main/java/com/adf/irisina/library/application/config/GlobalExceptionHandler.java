package com.adf.irisina.library.application.config;

import com.adf.irisina.library.application.config.exceptions.BaseException;
import com.adf.irisina.library.application.config.exceptions.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class, ValidationException.class, BaseException.class})
    @ResponseBody
    public Response handleBaseException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return Response.generateError(request, HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseBody
    public Response handleNoElementException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return Response.generateError(request, HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
