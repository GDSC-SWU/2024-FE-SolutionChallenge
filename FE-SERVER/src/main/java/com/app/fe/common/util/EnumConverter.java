package com.app.fe.common.util;

import com.app.fe.common.model.EnumCodeType;

import java.util.HashMap;
import java.util.Map;

public class EnumConverter {

    public static Map<String, String> convertEnumsToMap(Class<? extends Enum<?>> enumClass) {
        Map<String, String> enumMap = new HashMap<>();

        Enum<?>[] enumConstants = enumClass.getEnumConstants();
        if (enumConstants != null) {
            for (Enum<?> e : enumConstants) {
                if (e instanceof EnumCodeType) {
                    enumMap.put(e.name(), ((EnumCodeType) e).getValue());
                }
            }
        }
        return enumMap;
    }
}
