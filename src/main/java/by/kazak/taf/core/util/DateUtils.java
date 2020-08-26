package by.kazak.taf.core.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now().withNano(0);
    }
    
    public static String getCurrentDateTimeAsString() {
        return getCurrentDateTime().format(DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss"));
    }
}
