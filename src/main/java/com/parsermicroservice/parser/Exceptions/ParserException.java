package com.parsermicroservice.parser.Exceptions;

import com.parsermicroservice.parser.Parser.Parser;

public class ParserException extends Exception {
    public ParserException(String errorMessage){
        super(errorMessage);
    }
}
