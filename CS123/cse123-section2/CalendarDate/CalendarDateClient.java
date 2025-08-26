public class CalendarDateClient {
    public static void main(String[] args) {
        CalendarDate earlyDate = new CalendarDate(2000, 9, 13);
        // squidward went into the future on this date
        CalendarDate lateDate = new CalendarDate(2017, 3, 06);
        System.out.println("earlyDate vs. lateDate:");
        System.out.println(earlyDate.compareTo(lateDate));
    }
}
