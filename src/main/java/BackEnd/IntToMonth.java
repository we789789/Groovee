package BackEnd;

import java.time.LocalDate;

public class IntToMonth {

    static String month;

    public IntToMonth(int intMonth) {

        month = switch (intMonth) {
            case 1 -> "January";
            case 2 -> "February";
            case 3 -> "March";
            case 4 -> "April";
            case 5 -> "May";
            case 6 -> "June";
            case 7 -> "July";
            case 8 -> "August";
            case 9 -> "September";
            case 10 -> "October";
            case 11 -> "November";
            case 12 -> "December";
            default -> "";
        };

    }
    public static String getMonth() {
        return month;
    }
}
