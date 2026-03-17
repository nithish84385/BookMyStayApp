abstract class Room {
    String type;
    int beds;
    int size;
    double price;

    Room(String type, int beds, int size, double price) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    void display(int availability) {
        System.out.println("Room Type: " + type);
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price: $" + price);
        System.out.println("Available: " + availability);
        System.out.println("------------------------");
    }
}

class SingleRoom extends Room {
    SingleRoom() {
        super("Single Room", 1, 200, 50);
    }
}

class DoubleRoom extends Room {
    DoubleRoom() {
        super("Double Room", 2, 350, 90);
    }
}

class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite Room", 3, 600, 180);
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        int singleAvailability = 5;
        int doubleAvailability = 3;
        int suiteAvailability = 2;

        System.out.println("Room Details and Availability");
        System.out.println("=============================");

        single.display(singleAvailability);
        doubleRoom.display(doubleAvailability);
        suite.display(suiteAvailability);
    }
}