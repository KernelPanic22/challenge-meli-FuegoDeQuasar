package com.mercadolibre.fuegodequasar.utils;

import com.mercadolibre.fuegodequasar.service.impl.MessageServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MessageUtils {
    /**
     * @param messages Una lista de listas de mensajes.
     * @return Retorna un mapa con las palabras que son distintas en la misma posicion entre mensajes.
     * @see MessageServiceImpl#decodeMessages(List) 
     */
    public static Map<Integer, String> differentWordsAtSamePosition(List<List<String>> messages) {
        Map<Integer, Map<Integer, String>> positionPerWord = new HashMap<>();
        messages.forEach(message -> positionPerWord.put(messages.indexOf(message), listToMap(message)));
        return checkWordMismatch(positionPerWord);
    }

    /**
     * @param positionPerWord Un mapa con los key como posisiciones y las palabras como valores.
     * @return Retorna un mapa con las palabras que son distintas en la misma posicion entre mensajes.
     * @see MessageUtils#differentWordsAtSamePosition(List)
     */
    public static Map<Integer, String> checkWordMismatch(Map<Integer, Map<Integer, String>> positionPerWord) {
        Map<Integer, String> returnMap = new HashMap<>();
        AtomicInteger index = new AtomicInteger();
        for (Map.Entry<Integer, Map<Integer, String>> messages : positionPerWord.entrySet()) {
            for (Map.Entry<Integer, String> actualMessage : messages.getValue().entrySet()) {
                Integer actualMessageKey = messages.getKey();
                for(Integer i = messages.getKey(); i < positionPerWord.size(); i++ ){
                    if (!actualMessage.getValue().equals("") && (!positionPerWord.get(i).get(actualMessage.getKey()).equals(""))) {
                        if (!actualMessage.getValue().equals(positionPerWord.get(i).get(actualMessage.getKey()))) {
                            returnMap.put(index.getAndIncrement(), "Mensaje "+ actualMessageKey + " Posicion ".concat(actualMessage.getKey().toString()
                                    + ": "+ actualMessage.getValue()
                                    + ", Mensaje "+i +" : "
                                    + positionPerWord.get(i).get(actualMessage.getKey())));
                        }
                    }
                }
            }
        }
        return returnMap;
    }

    /**
     * @param list Una lista de tipo List<String>
     * @return Retorna un mapa de tipo Map<Integer,String>
     * @see MessageUtils#differentWordsAtSamePosition(List)
     */
    public static Map<Integer, String> listToMap(List<String> list) {
        Map<Integer, String> map = new HashMap<>();
        AtomicInteger index = new AtomicInteger();
        list.forEach(s -> map.put(index.incrementAndGet(), s));
        return map;
    }

    /**
     * @param messages Una lista de listas de mensajes.
     * @return Retorna Boolean.TRUE si los mensajes tienen el mismo largo Boolean.FALSE caso contrario.
     * @see MessageServiceImpl#decodeMessages(List)
     */
    public static Boolean messagesSameLength(List<List<String>> messages) {
        int length = messages.get(0).size();
        List<List<String>> notEqualMessages = messages.stream().filter(list -> list.size() != length).collect(Collectors.toList());
        return (notEqualMessages.size() >= 1) ? Boolean.FALSE : Boolean.TRUE;
    }
}
