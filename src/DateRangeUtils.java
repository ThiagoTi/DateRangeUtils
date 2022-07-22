import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DateRangeUtils {

    // Java 8
    public static List<LocalDate> splitDates(LocalDate date1, final LocalDate date2, final ChronoUnit unit) {
        List<LocalDate> dates = new ArrayList<>();
        while (date1.isBefore(date2)) {
            dates.add(date1);
            date1 = date1.plus(1, unit);
        }
        dates.add(date2);

        return dates;
    }

    public static List<LocalDate> splitDates(LocalDate date1, final LocalDate date2, final ChronoUnit unit, final int amount) {
        List<LocalDate> dates = new ArrayList<>();
        while (date1.isBefore(date2)) {
            dates.add(date1);
            date1 = date1.plus(amount, unit);
        }
        dates.add(date2);

        return dates;
    }

    // Java 8 + Stream
    public static List<LocalDate> splitDatesStream(LocalDate date1, final LocalDate date2, final ChronoUnit unit) {
        return splitDatesStream(date1, date2, unit, 1);
    }

    public static List<LocalDate> splitDatesStream(LocalDate date1, final LocalDate date2, final ChronoUnit unit, final int amount) {
        assert amount > 0;
        long items = unit.between(date1, date2) / amount;
        List<LocalDate> dates = IntStream.iterate(0, i -> i + amount)
            .limit(items + 1)
            .mapToObj(item -> date1.plus(item, unit))
            .collect(Collectors.toList());
        dates.add(date2);

        return dates;
    }

    // Java 9
    public static List<LocalDate> splitDates(LocalDate date1, final LocalDate date2, final Period period) {
        List<LocalDate> dates = date1.datesUntil(date2, period).collect(Collectors.toList());
        dates.add(date2);

        return dates;
    }
}
