import java.util.*;

class Reservation {
    String guestName;
    String roomType;
    String reservationId;

    Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void display() {
        System.out.println(reservationId + " | " + guestName + " | " + roomType);
    }
}

class BookingHistory {

    private List<Reservation> history;

    BookingHistory() {
        history = new ArrayList<>();
    }

    void addReservation(Reservation r) {
        history.add(r);
    }

    List<Reservation> getAllReservations() {
        return history;
    }
}

class BookingReportService {

    void displayAllBookings(List<Reservation> reservations) {

        System.out.println("Booking History");
        System.out.println("================");

        for (Reservation r : reservations) {
            r.display();
        }
    }

    void generateSummary(List<Reservation> reservations) {

        Map<String, Integer> summary = new HashMap<>();

        for (Reservation r : reservations) {
            summary.put(r.roomType, summary.getOrDefault(r.roomType, 0) + 1);
        }

        System.out.println("\nBooking Summary");
        System.out.println("================");

        for (Map.Entry<String, Integer> entry : summary.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        history.addReservation(new Reservation("RES-1", "Alice", "Single Room"));
        history.addReservation(new Reservation("RES-2", "Bob", "Double Room"));
        history.addReservation(new Reservation("RES-3", "Charlie", "Single Room"));

        BookingReportService reportService = new BookingReportService();

        reportService.displayAllBookings(history.getAllReservations());
        reportService.generateSummary(history.getAllReservations());
    }
}