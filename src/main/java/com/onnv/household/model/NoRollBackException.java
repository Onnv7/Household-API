package com.onnv.household.model;

public class NoRollBackException extends RuntimeException{
    public NoRollBackException(String message) {
        super(message);
    }
}
