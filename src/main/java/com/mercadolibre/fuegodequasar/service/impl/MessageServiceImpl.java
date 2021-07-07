package com.mercadolibre.fuegodequasar.service.impl;

import com.mercadolibre.fuegodequasar.service.MessageService;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static com.mercadolibre.fuegodequasar.utils.MessageUtils.differentWordsAtSamePosition;
import static com.mercadolibre.fuegodequasar.utils.MessageUtils.messagesSameLength;

@Service
public class MessageServiceImpl implements MessageService {
    public String decodeMessages(List<List<String>> messages) throws Exception {
        if (!messagesSameLength(messages))
            throw new Exception("Distinta cantidad de mensajes");

        Map<Integer,String> mismatchingWordsMap = differentWordsAtSamePosition(messages);
        if (!mismatchingWordsMap.isEmpty())
            throw new Exception("Los mensajes contienen palabras distintas en la misma posicion" + mismatchingWordsMap.values());

        List<String> uniqueWords = messages.stream()//Todas las palabras unicas contenidas en los mensajes excepto las vacias
                .flatMap(List::stream) //bajo todos los streams de mensajes a un unico stream
                .filter(word -> !word.equals("")) //chequeo que no sean vacias
                .distinct()
                .collect(Collectors.toList());

        List<Integer> messagePositions = uniqueWords.stream()//Las posiciones donde se deben encontrar las palabras para poder decodificar el mensaje
                .map(s -> messages.stream()
                        .map(strings -> strings
                                .lastIndexOf(s))
                ).flatMap(Stream::distinct)
                .filter(integer -> integer != -1) //-1 significa que no encontro la palabra dentro del mensaje actual
                .collect(Collectors.toList());

        Map<Integer, String> messageDecoded = new HashMap<>();//Se ordena el mensaje en un mapa para decodificarlo
        messagePositions.forEach(integer -> messageDecoded
                .put(integer, uniqueWords.get(messagePositions.indexOf(integer)))
        );
        return messageDecoded.values().toString();//Devuelve el mensaje como una lista de Strings
    }
}
