package com.mercadolibre.fuegodequasar.exception;

public class MessageException extends Exception {
    
    private static final long serialVersionUID = 1L;

    public MessageException(String msg) {
        super(msg);
    }
}