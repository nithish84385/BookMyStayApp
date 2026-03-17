import java.util.HashMap;

class InvalidBookingException extends Exception {
    InvalidBookingException(String message) {
        super(message);
    }
}

class RoomInventory {

    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 0);
    }

    void validateRoom(String roomType) throws InvalidBookingException {

        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        if (inventory.get(roomType) <= 0) {
            throw new InvalidBookingException("No availability for: " + roomType);
        }
    }

    void bookRoom(String roomType) throws InvalidBookingException {

        validateRoom(roomType);

        int current = inventory.get(roomType);

        if (current - 1 < 0) {
            throw new InvalidBookingException("Invalid inventory state for: " + roomType);
        }

        inventory.put(roomType, current - 1);

        System.out.println("Booking successful for " + roomType);
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        try {
            inventory.bookRoom("Single Room");   // valid
            inventory.bookRoom("Suite Room");    // no availability
            inventory.bookRoom("Deluxe Room");   // invalid type
        } catch (InvalidBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("System continues running safely.");
    }
}