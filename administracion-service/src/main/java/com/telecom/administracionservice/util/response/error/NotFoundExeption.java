package com.telecom.administracionservice.util.response.error;

public class NotFoundExeption extends RuntimeException{

    private static final String DESCRIPCION = "Not Found Exception (404)";

    public NotFoundExeption(String message) {
        super(DESCRIPCION + ": " + message);
    }
}
