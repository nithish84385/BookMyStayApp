import java.util.*;

class Reservation {
    String reservationId;
    String roomType;
    String roomId;

    Reservation(String reservationId, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.roomType = roomType;
        this.roomId = roomId;
    }
}

class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    void increaseAvailability(String roomType) {
        inventory.put(roomType, inventory.getOrDefault(roomType, 0) + 1);
    }

    void display() {
        System.out.println("Inventory:");
        for (Map.Entry<String, Integer> e : inventory.entrySet()) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }
    }
}

class BookingHistory {

    private Map<String, Reservation> bookings = new HashMap<>();

    void add(Reservation r) {
        bookings.put(r.reservationId, r);
    }

    Reservation get(String id) {
        return bookings.get(id);
    }

    void remove(String id) {
        bookings.remove(id);
    }
}

class CancellationService {

    private Stack<String> rollbackStack = new Stack<>();

    void cancel(String reservationId, BookingHistory history, RoomInventory inventory) {

        Reservation r = history.get(reservationId);

        if (r == null) {
            System.out.println("Cancellation Failed: Invalid reservation ID");
            return;
        }

        rollbackStack.push(r.roomId);

        inventory.increaseAvailability(r.roomType);

        history.remove(reservationId);

        System.out.println("Cancelled: " + reservationId + " | Room Released: " + rollbackStack.pop());
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingHistory history = new BookingHistory();

        history.add(new Reservation("RES-1", "Single Room", "SR101"));
        history.add(new Reservation("RES-2", "Double Room", "DR201"));

        CancellationService service = new CancellationService();

        service.cancel("RES-1", history, inventory);
        service.cancel("RES-5", history, inventory);

        inventory.display();
    }
}