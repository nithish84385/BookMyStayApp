import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class RoomInventory {
    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    void reduceAvailability(String roomType) {
        inventory.put(roomType, getAvailability(roomType) - 1);
    }
}

class BookingService {

    private Queue<Reservation> queue;
    private HashMap<String, Set<String>> allocatedRooms;

    BookingService() {
        queue = new LinkedList<>();
        allocatedRooms = new HashMap<>();
    }

    void addRequest(Reservation r) {
        queue.add(r);
    }

    void processBookings(RoomInventory inventory) {

        System.out.println("Processing Bookings");
        System.out.println("===================");

        while (!queue.isEmpty()) {

            Reservation r = queue.poll();
            String type = r.roomType;

            if (inventory.getAvailability(type) > 0) {

                String roomId = type.substring(0, 2).toUpperCase() + (int)(Math.random() * 100);

                allocatedRooms.putIfAbsent(type, new HashSet<>());

                Set<String> assigned = allocatedRooms.get(type);

                while (assigned.contains(roomId)) {
                    roomId = type.substring(0, 2).toUpperCase() + (int)(Math.random() * 100);
                }

                assigned.add(roomId);
                inventory.reduceAvailability(type);

                System.out.println("Booking Confirmed: " + r.guestName + " -> " + roomId);

            } else {
                System.out.println("Booking Failed (No Availability): " + r.guestName);
            }
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService();

        service.addRequest(new Reservation("Alice", "Single Room"));
        service.addRequest(new Reservation("Bob", "Single Room"));
        service.addRequest(new Reservation("Charlie", "Single Room"));

        service.addRequest(new Reservation("David", "Suite Room"));

        service.processBookings(inventory);
    }
}