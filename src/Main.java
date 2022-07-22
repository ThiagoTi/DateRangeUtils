import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        LocalDate date1 = LocalDate.of(2000, 6, 20);
        LocalDate date2 = LocalDate.of(2022, 7, 19);

        List<LocalDate> dates;

        System.out.println("DateRangeUtils.splitDates");
        dates = DateRangeUtils.splitDates(date1, date2, ChronoUnit.MONTHS, 2);
        printDates(dates);
        System.out.println("**");

        System.out.println("DateRangeUtils.splitDatesStream");
        dates = DateRangeUtils.splitDatesStream(date1, date2, ChronoUnit.MONTHS, 2);
        printDates(dates);
        System.out.println("**");

        System.out.println("DateRangeUtils.splitDates");
        dates = DateRangeUtils.splitDates(date1, date2, Period.ofMonths(2));
        printDates(dates);
        System.out.println("**");
    }

    private static void printDates(final List<LocalDate> dates) {
        for (int i = 0; i < dates.size() - 1; i++) {
            System.out.println(dates.get(i).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " - " + dates.get(i + 1)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
    }
}
