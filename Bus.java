import java.util.*;

public class Bus {
    private int busNo;
    private boolean ac;
    private int capacity;
    private String route;
    private double distance; // in kilometers
    private double baseFare; // per km rate

    public Bus(int busNo, boolean ac, int capacity, String route, double distance, double baseFare) {
        this.busNo = busNo;
        this.ac = ac;
        this.capacity = capacity;
        this.route = route;
        this.distance = distance;
        this.baseFare = baseFare;
    }

    public int getBusNo() {
        return busNo;
    }

    public boolean isAc() {
        return ac;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getRoute() {
        return route;
    }

    public double getDistance() {
        return distance;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public void reduceCapacity() {
        this.capacity--;
    }

    public void setCapacity(int newCapacity) {
        this.capacity = newCapacity;
    }

    public void displayBusInfo() {
        System.out.println("Bus No: " + busNo + 
                           " | AC: " + (ac ? "Yes" : "No") +
                           " | Seats: " + capacity +
                           " | Route: " + route +
                           " | Distance: " + distance + " km" +
                           " | Fare/km: ₹" + baseFare);
    }
}
