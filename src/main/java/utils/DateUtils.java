package utils;

import java.time.LocalDate;

public class DateUtils {

    public static int getCurrentDate(){
        return LocalDate.now().getYear();
    }
}
