package com.telecom.gestionservice.util.response.error;

public class BadRequestException extends RuntimeException{

    private static final String DESCRIPCION = "Bad Request Exception (400)";

    public BadRequestException(String message) {
        super(DESCRIPCION + ": " + message);
    }
}
