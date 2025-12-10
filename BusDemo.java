import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class BusDemo {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        Bus[] buses = new Bus[10];          // array of buses
        Booking[] bookings = new Booking[50]; // array of bookings

        int busCount = 0;
        int bookingCount = 0;

        // default buses
        buses[busCount++] = new Bus(1, true, 2, "Bangalore → Mysore", 150, 5);
        buses[busCount++] = new Bus(2, false, 3, "Bangalore → Chennai", 330, 4);
        buses[busCount++] = new Bus(3, true, 1, "Mangalore → Bangalore", 350, 6);

        int choice;
        do {
            System.out.println("\n====== 🚌 BUS BOOKING SYSTEM ======");
            System.out.println("1. View Available Buses");
            System.out.println("2. Book Ticket");
            System.out.println("3. View All Bookings");
            System.out.println("4. Admin Panel");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    showBuses(buses, busCount);
                    break;
                case 2:
                    bookingCount = handleBooking(buses, bookings, busCount, bookingCount);
                    break;
                case 3:
                    showBookings(bookings, bookingCount);
                    break;
                case 4:
                    busCount = adminPanel(buses, busCount);
                    break;
                case 5:
                    System.out.println("Thank you for using Bus Booking System!");
                    break;
                default:
                    System.out.println("❌ Invalid choice, try again.");
            }

        } while (choice != 5);

        sc.close();
    }

    // ---------------- USER: View Buses ----------------
    public static void showBuses(Bus[] buses, int busCount) {
        System.out.println("\n------ AVAILABLE BUSES ------");
        for (int i = 0; i < busCount; i++) {
            buses[i].displayBusInfo();
        }
        System.out.println("-----------------------------");
    }

    // ---------------- USER: Book Ticket ----------------
    public static int handleBooking(Bus[] buses, Booking[] bookings,
                                    int busCount, int bookingCount) throws Exception {
        if (busCount == 0) {
            System.out.println("No buses available!");
            return bookingCount;
        }

        showBuses(buses, busCount);

        System.out.print("\nEnter Passenger Name: ");
        String name = sc.next();

        System.out.print("Enter Bus No to book: ");
        int busNo = sc.nextInt();

        Bus selectedBus = findBus(buses, busCount, busNo);
        if (selectedBus == null) {
            System.out.println("❌ Bus not found!");
            return bookingCount;
        }

        if (selectedBus.getCapacity() <= 0) {
            System.out.println("❌ Sorry! No seats available on this bus.");
            return bookingCount;
        }

        System.out.print("Enter Journey Date (dd-MM-yyyy): ");
        String dateStr = sc.next();
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);

        // Book seat on bus
        boolean seatBooked = selectedBus.bookSeat();
        if (!seatBooked) {
            System.out.println("❌ Seat booking failed, please try again.");
            return bookingCount;
        }

        // Create Booking object (Inheritance in action)
        Booking booking = new Booking(name, date, selectedBus);

        bookings[bookingCount++] = booking;

        System.out.println("✅ Booking Successful!");
        booking.displayBookingDetails();

        return bookingCount;
    }

    // --------------- USER: View All Bookings ---------------
    public static void showBookings(Booking[] bookings, int bookingCount) {
        if (bookingCount == 0) {
            System.out.println("\nNo bookings done yet.");
            return;
        }

        System.out.println("\n====== ALL BOOKINGS ======");
        for (int i = 0; i < bookingCount; i++) {
            bookings[i].displayBookingDetails();
        }
    }

    // --------------- ADMIN PANEL ----------------
    public static int adminPanel(Bus[] buses, int busCount) {
        System.out.print("\nEnter admin username: ");
        String user = sc.next();
        System.out.print("Enter admin password: ");
        String pass = sc.next();

        if (!user.equals("admin") || !pass.equals("1234")) {
            System.out.println("❌ Invalid admin credentials!");
            return busCount;
        }

        int opt;
        do {
            System.out.println("\n====== 🔐 ADMIN PANEL ======");
            System.out.println("1. Add New Bus");
            System.out.println("2. Update Bus Capacity");
            System.out.println("3. View Buses");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            opt = sc.nextInt();

            switch (opt) {
                case 1:
                    busCount = addBus(buses, busCount);
                    break;
                case 2:
                    updateCapacity(buses, busCount);
                    break;
                case 3:
                    showBuses(buses, busCount);
                    break;
                case 4:
                    System.out.println("Admin logged out.");
                    break;
                default:
                    System.out.println("❌ Invalid choice.");
            }

        } while (opt != 4);

        return busCount;
    }

    public static int addBus(Bus[] buses, int busCount) {
        if (busCount >= buses.length) {
            System.out.println("❌ Cannot add more buses. Limit reached.");
            return busCount;
        }

        System.out.print("Enter Bus No: ");
        int no = sc.nextInt();
        System.out.print("Is AC bus? (true/false): ");
        boolean ac = sc.nextBoolean();
        System.out.print("Enter Capacity: ");
        int cap = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter Route: ");
        String route = sc.nextLine();
        System.out.print("Enter Distance (km): ");
        double dist = sc.nextDouble();
        System.out.print("Enter Base Fare per km: ");
        double fare = sc.nextDouble();

        buses[busCount++] = new Bus(no, ac, cap, route, dist, fare);
        System.out.println("✅ Bus added successfully!");

        return busCount;
    }

    public static void updateCapacity(Bus[] buses, int busCount) {
        System.out.print("Enter Bus No to update: ");
        int no = sc.nextInt();

        Bus bus = findBus(buses, busCount, no);
        if (bus == null) {
            System.out.println("❌ Bus not found!");
            return;
        }

        System.out.print("Enter new capacity: ");
        int newCap = sc.nextInt();
        bus.setCapacity(newCap);
        System.out.println("✅ Capacity updated!");
    }

    // Utility: Find bus by number
    public static Bus findBus(Bus[] buses, int busCount, int busNo) {
        for (int i = 0; i < busCount; i++) {
            if (buses[i].getBusNo() == busNo) {
                return buses[i];
            }
        }
        return null;
    }
}
