package com.mercadolibre.fuegodequasar.exception;

public class RedisException extends Exception {

    private static final long serialVersionUID = 1L;

    public RedisException(String msg) {
        super(msg);
    }
}