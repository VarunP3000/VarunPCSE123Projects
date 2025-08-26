public class ClockTimeClient {
    public static void main(String[] args) {
        ClockTime midnight = new ClockTime(12, 00, "am");
        ClockTime noon = new ClockTime(12, 00, "pm");
        System.out.println("midnight compared to noon: " + midnight.compareTo(noon));
    }
}
