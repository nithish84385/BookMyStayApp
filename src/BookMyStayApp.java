import java.util.HashMap;

abstract class Room {
    String type;
    double price;

    Room(String type, double price) {
        this.type = type;
        this.price = price;
    }

    void display() {
        System.out.println("Room Type: " + type);
        System.out.println("Price: $" + price);
    }
}

class SingleRoom extends Room {
    SingleRoom() {
        super("Single Room", 50);
    }
}

class DoubleRoom extends Room {
    DoubleRoom() {
        super("Double Room", 90);
    }
}

class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite Room", 180);
    }
}

class RoomInventory {
    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 0);
        inventory.put("Suite Room", 2);
    }

    int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

class SearchService {

    void search(RoomInventory inventory, Room[] rooms) {

        System.out.println("Available Rooms");
        System.out.println("================");

        for (Room room : rooms) {
            int available = inventory.getAvailability(room.type);

            if (available > 0) {
                room.display();
                System.out.println("Available: " + available);
                System.out.println("--------------------");
            }
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        SearchService service = new SearchService();
        service.search(inventory, rooms);
    }
}