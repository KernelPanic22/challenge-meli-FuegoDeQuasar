package com.mercadolibre.fuegodequasar.service;

import java.util.List;

public interface MessageService {
     String decodeMessages(List<List<String>> messages) throws Exception;
}
