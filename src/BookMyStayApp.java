import java.util.*;

class Service {
    String name;
    double price;

    Service(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class AddOnServiceManager {

    private Map<String, List<Service>> serviceMap = new HashMap<>();

    void addService(String reservationId, Service service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    void displayServices(String reservationId) {
        List<Service> services = serviceMap.getOrDefault(reservationId, new ArrayList<>());

        System.out.println("Services for Reservation ID: " + reservationId);

        for (Service s : services) {
            System.out.println(s.name + " - $" + s.price);
        }
    }

    double calculateTotalCost(String reservationId) {
        List<Service> services = serviceMap.getOrDefault(reservationId, new ArrayList<>());
        double total = 0;

        for (Service s : services) {
            total += s.price;
        }

        return total;
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "RES-101";

        manager.addService(reservationId, new Service("Breakfast", 10));
        manager.addService(reservationId, new Service("Airport Pickup", 25));
        manager.addService(reservationId, new Service("Extra Bed", 15));

        manager.displayServices(reservationId);

        System.out.println("Total Add-On Cost: $" + manager.calculateTotalCost(reservationId));
    }
}