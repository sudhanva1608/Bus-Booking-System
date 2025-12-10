import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class BusDemo {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Bus[] buses = new Bus[10];
        Booking[] bookings = new Booking[50];
        int busCount = 0, bookingCount = 0;

        buses[busCount++] = new Bus(1, true, 2, "Bangalore → Mysore", 150, 5);
        buses[busCount++] = new Bus(2, false, 3, "Bangalore → Chennai", 330, 4);
        buses[busCount++] = new Bus(3, true, 1, "Mangalore → Bangalore", 350, 6);

        int choice;
        do {
            System.out.println("\n====== BUS BOOKING SYSTEM ======");
            System.out.println("1. View Buses");
            System.out.println("2. Book Ticket");
            System.out.println("3. View Bookings");
            System.out.println("4. Admin Panel");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            if (choice == 1) showBuses(buses, busCount);
            else if (choice == 2) bookingCount = handleBooking(buses, bookings, busCount, bookingCount);
            else if (choice == 3) showBookings(bookings, bookingCount);
            else if (choice == 4) busCount = adminPanel(buses, busCount);
            else if (choice == 5) System.out.println("Thank you for using Bus Booking System!");
            else System.out.println("Invalid choice.");
        } while (choice != 5);

        sc.close();
    }

    static void showBuses(Bus[] buses, int busCount) {
        if (busCount == 0) {
            System.out.println("No buses available.");
            return;
        }
        System.out.println("\n------ AVAILABLE BUSES ------");
        for (int i = 0; i < busCount; i++) buses[i].displayBusInfo();
        System.out.println("-----------------------------");
    }

    static int handleBooking(Bus[] buses, Booking[] bookings, int busCount, int bookingCount) throws Exception {
        if (busCount == 0) {
            System.out.println("No buses to book.");
            return bookingCount;
        }
        showBuses(buses, busCount);
        System.out.print("\nEnter Passenger Name: ");
        String name = sc.next();
        System.out.print("Enter Bus No: ");
        int busNo = sc.nextInt();
        Bus selectedBus = findBus(buses, busCount, busNo);
        if (selectedBus == null) {
            System.out.println("Bus not found.");
            return bookingCount;
        }
        if (selectedBus.getCapacity() <= 0) {
            System.out.println("No seats available.");
            return bookingCount;
        }
        System.out.print("Enter Journey Date (dd-MM-yyyy): ");
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(sc.next());
        if (!selectedBus.bookSeat()) {
            System.out.println("Seat booking failed.");
            return bookingCount;
        }
        Booking booking = new Booking(name, date, selectedBus);
        bookings[bookingCount++] = booking;
        System.out.println("Booking Successful!");
        booking.displayBookingDetails();
        return bookingCount;
    }

    static void showBookings(Booking[] bookings, int bookingCount) {
        if (bookingCount == 0) {
            System.out.println("No bookings yet.");
            return;
        }
        System.out.println("\n====== ALL BOOKINGS ======");
        for (int i = 0; i < bookingCount; i++) bookings[i].displayBookingDetails();
    }

    static int adminPanel(Bus[] buses, int busCount) {
        System.out.print("\nAdmin username: ");
        String user = sc.next();
        System.out.print("Admin password: ");
        String pass = sc.next();
        if (!user.equals("admin") || !pass.equals("1234")) {
            System.out.println("Invalid credentials.");
            return busCount;
        }
        int c;
        do {
            System.out.println("\n====== ADMIN PANEL ======");
            System.out.println("1. Add Bus");
            System.out.println("2. Update Capacity");
            System.out.println("3. View Buses");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");
            c = sc.nextInt();
            if (c == 1) busCount = addBus(buses, busCount);
            else if (c == 2) updateCapacity(buses, busCount);
            else if (c == 3) showBuses(buses, busCount);
            else if (c == 4) System.out.println("Logged out.");
            else System.out.println("Invalid choice.");
        } while (c != 4);
        return busCount;
    }

    static int addBus(Bus[] buses, int busCount) {
        if (busCount >= buses.length) {
            System.out.println("Bus limit reached.");
            return busCount;
        }
        System.out.print("Enter Bus No: ");
        int no = sc.nextInt();
        System.out.print("Is AC (true/false): ");
        boolean ac = sc.nextBoolean();
        System.out.print("Enter Capacity: ");
        int cap = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Route: ");
        String route = sc.nextLine();
        System.out.print("Enter Distance (km): ");
        double dist = sc.nextDouble();
        System.out.print("Enter Base Fare/km: ");
        double fare = sc.nextDouble();
        buses[busCount++] = new Bus(no, ac, cap, route, dist, fare);
        System.out.println("Bus added.");
        return busCount;
    }

    static void updateCapacity(Bus[] buses, int busCount) {
        System.out.print("Enter Bus No: ");
        int no = sc.nextInt();
        Bus bus = findBus(buses, busCount, no);
        if (bus == null) {
            System.out.println("Bus not found.");
            return;
        }
        System.out.print("Enter new capacity: ");
        bus.setCapacity(sc.nextInt());
        System.out.println("Capacity updated.");
    }

    static Bus findBus(Bus[] buses, int busCount, int busNo) {
        for (int i = 0; i < busCount; i++)
            if (buses[i].getBusNo() == busNo) return buses[i];
        return null;
    }
}
