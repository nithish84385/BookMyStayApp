import java.io.*;
import java.util.*;

class RoomInventory implements Serializable {

    private Map<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
    }

    Map<String, Integer> getInventory() {
        return inventory;
    }
}

class PersistenceService {

    private static final String FILE_NAME = "data.ser";

    void save(RoomInventory inventory) {

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(inventory);
            System.out.println("State saved successfully.");
        } catch (Exception e) {
            System.out.println("Error saving data.");
        }
    }

    RoomInventory load() {

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            System.out.println("State loaded successfully.");
            return (RoomInventory) in.readObject();
        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
            return new RoomInventory();
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        PersistenceService service = new PersistenceService();

        RoomInventory inventory = service.load();

        System.out.println("Current Inventory: " + inventory.getInventory());

        service.save(inventory);
    }
}