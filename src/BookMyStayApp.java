import java.util.LinkedList;
import java.util.Queue;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void display() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
    }
}

class BookingRequestQueue {
    private Queue<Reservation> queue;

    BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Request added for " + reservation.guestName);
    }

    void displayQueue() {
        System.out.println("Current Booking Queue");
        System.out.println("=====================");

        for (Reservation r : queue) {
            r.display();
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Double Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite Room"));

        System.out.println();
        bookingQueue.displayQueue();
    }
}