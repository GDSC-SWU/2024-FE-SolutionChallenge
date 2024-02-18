package com.app.fe.common.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final String yyyyMM = "yyyyMM";

    public static String getDate_yyyyMM() {
        return LocalDate.now()
                .format(DateTimeFormatter.ofPattern(yyyyMM));
    }
}
