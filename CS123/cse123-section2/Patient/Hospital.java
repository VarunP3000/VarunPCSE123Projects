import java.util.*;

public class Hospital {
    public static void main(String[] args) {
        Queue<Patient> patients = new LinkedList<>();
        populateQueue(patients);
        while (!patients.isEmpty()) {
            System.out.println("Now seeing: " + patients.remove().getName());
        }
    }

    public static void populateQueue(Queue<Patient> patients) {
        patients.add(new Patient("Sasha", "papercut"));
        patients.add(new Patient("Trevor", "bigger papercut"));
    }
}
