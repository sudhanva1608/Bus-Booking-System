public class Bus {
    // Encapsulation: private data members
    private int busNo;
    private boolean ac;
    private int capacity;      // available seats
    private String route;
    private double distance;   // in km
    private double baseFare;   // fare per km

    // Constructor
    public Bus(int busNo, boolean ac, int capacity, String route, double distance, double baseFare) {
        this.busNo = busNo;
        this.ac = ac;
        this.capacity = capacity;
        this.route = route;
        this.distance = distance;
        this.baseFare = baseFare;
    }

    // Getters and Setters
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

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // Business logic: booking seat
    public boolean bookSeat() {
        if (capacity > 0) {
            capacity--;
            return true;
        }
        return false;
    }

    // Fare calculation (can be reused by child class)
    public double calculateFare() {
        double fare = distance * baseFare;
        if (ac) {
            fare = fare * 1.2; // 20% extra for AC
        }
        return fare;
    }

    // Display bus details
    public void displayBusInfo() {
        System.out.println("Bus No: " + busNo
                + " | AC: " + (ac ? "Yes" : "No")
                + " | Seats Available: " + capacity
                + " | Route: " + route
                + " | Distance: " + distance + " km"
                + " | Base Fare/km: ₹" + baseFare);
    }
}
