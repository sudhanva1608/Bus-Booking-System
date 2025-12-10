import java.util.Date;
import java.text.SimpleDateFormat;

public class Booking extends Bus {
    private String passengerName;
    private Date journeyDate;
    private double finalFare;

    // Constructor chaining with super()
    public Booking(String passengerName, Date journeyDate, Bus bus) {
        // Call parent constructor to copy bus details
        super(bus.getBusNo(), bus.isAc(), bus.getCapacity(), bus.getRoute(),
              bus.getDistance(), bus.getBaseFare());
        this.passengerName = passengerName;
        this.journeyDate = journeyDate;

        // Polymorphism: overridden method is called
        this.finalFare = calculateFare();
    }

    public String getPassengerName() {
        return passengerName;
    }

    public Date getJourneyDate() {
        return journeyDate;
    }

    public double getFinalFare() {
        return finalFare;
    }

    // Method Overriding (Polymorphism):
    // Adds small service charge on top of normal bus fare.
    @Override
    public double calculateFare() {
        double baseFare = super.calculateFare(); // call parent's logic
        double serviceCharge = 20.0;             // flat service charge
        return baseFare + serviceCharge;
    }

    public void displayBookingDetails() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("\n----- TICKET DETAILS -----");
        System.out.println("Passenger Name : " + passengerName);
        System.out.println("Bus No         : " + getBusNo());
        System.out.println("Route          : " + getRoute());
        System.out.println("Journey Date   : " + sdf.format(journeyDate));
        System.out.println("AC             : " + (isAc() ? "Yes" : "No"));
        System.out.println("Fare Paid      : ₹" + finalFare);
        System.out.println("--------------------------");
    }
}
