package com.mercadolibre.fuegodequasar.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MessageUtils {
    public static Map<Integer, String> differentWordsAtSamePosition(List<List<String>> messages) {
        Map<Integer, Map<Integer, String>> positionPerWord = new HashMap<>();
        messages.forEach(message -> positionPerWord.put(messages.indexOf(message), listToMap(message)));
        return checkWordMissmatch(positionPerWord);
    }

    public static Map<Integer, String> checkWordMissmatch(Map<Integer, Map<Integer, String>> positionPerWord) {
        Map<Integer, String> returnMap = new HashMap<>();
        AtomicInteger index = new AtomicInteger();
        for (Map.Entry<Integer, Map<Integer, String>> messages : positionPerWord.entrySet()) {
            for (Map.Entry<Integer, String> actualMessage : messages.getValue().entrySet()) {
                Integer actualMessageKey = messages.getKey();
                for(Integer i = messages.getKey(); i < positionPerWord.size(); i++ ){
                    if (!actualMessage.getValue().equals("") && (!positionPerWord.get(i).get(actualMessage.getKey()).equals(""))) {
                        if (!actualMessage.getValue().equals(positionPerWord.get(i).get(actualMessage.getKey()))) {
                            returnMap.put(index.getAndIncrement(), "Mensaje "+ actualMessageKey + " Posicion ".concat(actualMessage.getKey().toString() + ": "+ actualMessage.getValue() + ", Mensaje "+i +" : "+ positionPerWord.get(i).get(actualMessage.getKey())));
                        }
                    }
                }
            }
        }
        return returnMap;
    }

    public static Map<Integer, String> listToMap(List<String> message) {
        Map<Integer, String> map = new HashMap<>();
        AtomicInteger index = new AtomicInteger();
        message.forEach(s -> map.put(index.incrementAndGet(), s));
        return map;
    }

    public static Boolean messagesSameLength(List<List<String>> messages) {
        int length = messages.get(0).size();
        List<List<String>> notEqualMessages = messages.stream().filter(list -> list.size() != length).collect(Collectors.toList());
        return (notEqualMessages.size() >= 1) ? Boolean.FALSE : Boolean.TRUE;
    }
}
