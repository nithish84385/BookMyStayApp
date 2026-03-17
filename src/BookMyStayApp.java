import java.util.*;

class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 2);
    }

    synchronized boolean bookRoom(String roomType, String guest) {

        int available = inventory.getOrDefault(roomType, 0);

        if (available > 0) {
            inventory.put(roomType, available - 1);
            System.out.println(guest + " booked successfully. Remaining: " + (available - 1));
            return true;
        } else {
            System.out.println(guest + " booking failed (No availability)");
            return false;
        }
    }
}

class BookingTask implements Runnable {

    private RoomInventory inventory;
    private String guestName;

    BookingTask(RoomInventory inventory, String guestName) {
        this.inventory = inventory;
        this.guestName = guestName;
    }

    public void run() {
        inventory.bookRoom("Single Room", guestName);
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Thread t1 = new Thread(new BookingTask(inventory, "Alice"));
        Thread t2 = new Thread(new BookingTask(inventory, "Bob"));
        Thread t3 = new Thread(new BookingTask(inventory, "Charlie"));

        t1.start();
        t2.start();
        t3.start();
    }
}