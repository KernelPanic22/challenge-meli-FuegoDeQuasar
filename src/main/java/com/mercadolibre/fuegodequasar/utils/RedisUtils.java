package com.mercadolibre.fuegodequasar.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.type.SerializationException;

import java.nio.charset.Charset;

public class RedisUtils {
        public static final String EMPTY_JSON = "{}";

        public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

        public RedisUtils(){}

        /**
         * java-object as json-string
         * @param object
         * @return
         */
        public static String seriazileAsString(Object object){
            if (object== null) {
                return EMPTY_JSON;
            }
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.writeValueAsString(object);
            } catch (Exception ex) {
                throw new SerializationException("Could not write JSON: " + ex.getMessage(), ex);
            }
        }

        /**
         * json-string to java-object
         * @param str
         * @return
         */
        public <T> T deserializeAsObject(String str,Class<T> clazz){
            if(str == null || clazz == null){
                return null;
            }
            try{
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(str, clazz);
            }catch (Exception ex) {
                throw new SerializationException("Could not write JSON: " + ex.getMessage(), ex);
            }
        }

    }


