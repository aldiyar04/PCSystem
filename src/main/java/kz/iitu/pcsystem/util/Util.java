package kz.iitu.pcsystem.util;

import java.util.Map;

public class Util {
    public static void mapBooleanField(Map<String, String> map, String fieldName) {
        String field = map.get(fieldName);
        if (field != null) {
            if (field.equals("Нет")) map.put(fieldName, "false");
            else map.put(fieldName, "true");
        }
    }
}
