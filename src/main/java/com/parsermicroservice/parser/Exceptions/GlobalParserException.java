package com.parsermicroservice.parser.Exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalParserException extends ResponseEntityExceptionHandler{
    private static final Logger myLogger = LoggerFactory.getLogger(ParserException.class);

    @ExceptionHandler(ParserException.class)
    public ResponseEntity<Object> globalParserException(ParserException parserException, WebRequest request){
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("Date/Time:", LocalDateTime.now());
        errorBody.put("Message:", parserException.getStackTrace());

        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

}
