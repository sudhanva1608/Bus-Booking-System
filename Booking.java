import java.text.*;
import java.util.*;

public class Booking {
    private String passengerName;
    private int busNo;
    private Date date;
    private double fareAmount;

    public Booking(String passengerName, int busNo, Date date) {
        this.passengerName = passengerName;
        this.busNo = busNo;
        this.date = date;
    }

    public boolean isAvailable(ArrayList<Booking> bookings, ArrayList<Bus> buses) {
        int capacity = 0;
        for (Bus b : buses) {
            if (b.getBusNo() == busNo)
                capacity = b.getCapacity();
        }

        int booked = 0;
        for (Booking b : bookings) {
            if (b.busNo == busNo && b.date.equals(date))
                booked++;
        }

        return booked < capacity;
    }

    public double calculateFare(Bus bus) {
        double fare = bus.getDistance() * bus.getBaseFare();
        if (bus.isAc()) {
            fare *= 1.2; // 20% extra for AC
        }
        return fare;
    }

    public void confirmBooking(ArrayList<Booking> bookings, ArrayList<Bus> buses) {
        for (Bus b : buses) {
            if (b.getBusNo() == busNo) {
                b.reduceCapacity();
                fareAmount = calculateFare(b);
                break;
            }
        }
        bookings.add(this);
        System.out.println("✅ Booking Confirmed for " + passengerName + " on Bus " + busNo + " for " + date);
        System.out.println("💰 Fare Amount: ₹" + fareAmount);
    }
}

