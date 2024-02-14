package it.syncroweb.es_03_spring_swagger_database.utils;

import java.lang.reflect.Field;

public class Utils {

    public static Object getFieldByName(Object object, String fieldName){
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        }catch (NoSuchFieldException |IllegalAccessException e){
            return null;
        }
    }
}
