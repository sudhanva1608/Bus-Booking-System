import java.text.*;
import java.util.*;

public class BusDemo {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        Bus[] buses = new Bus[10];       // array of buses
        Booking[] bookings = new Booking[50];  // array of bookings

        int busCount = 0;
        int bookingCount = 0;

        // Default buses
        buses[busCount++] = new Bus(1, true, 2, "Bangalore → Mysore", 150, 5);
        buses[busCount++] = new Bus(2, false, 3, "Bangalore → Chennai", 330, 4);
        buses[busCount++] = new Bus(3, true, 1, "Mangalore → Bangalore", 350, 6);

        int mainOption;

        do {
            System.out.println("\n====== 🚌 BUS BOOKING SYSTEM ======");
            System.out.println("1. Book Ticket");
            System.out.println("2. View Available Buses");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            mainOption = sc.nextInt();

            switch (mainOption) {
                case 1:
                    bookingCount = handleBooking(buses, bookings, busCount, bookingCount);
                    break;
                case 2:
                    showBuses(buses, busCount);
                    break;
                case 3:
                    busCount = adminPanel(buses, busCount);
                    break;
                case 4:
                    System.out.println("\nThank you for using the Bus Booking System!");
                    break;
                default:
                    System.out.println("❌ Invalid Option. Try again!");
            }

        } while (mainOption != 4);
    }

    // BOOKING SECTION
    public static int handleBooking(Bus[] buses, Booking[] bookings, int busCount, int bookingCount) throws Exception {
        showBuses(buses, busCount);

        System.out.print("\nEnter Passenger Name: ");
        String name = sc.next();

        System.out.print("Enter Bus No to book: ");
        int busNo = sc.nextInt();

        System.out.print("Enter Date (dd-MM-yyyy): ");
        String dateInput = sc.next();
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateInput);

        // FIND BUS
        Bus selectedBus = null;
        for (int i = 0; i < busCount; i++) {
            if (buses[i].getBusNo() == busNo) {
                selectedBus = buses[i];
                break;
            }
        }

        if (selectedBus == null) {
            System.out.println("❌ Bus not found!");
            return bookingCount;
        }

        // CHECK AVAILABILITY
        int bookedSeats = 0;
        for (int i = 0; i < bookingCount; i++) {
            if (bookings[i].getBusNo() == busNo && bookings[i].getDate().equals(date)) {
                bookedSeats++;
            }
        }

        if (bookedSeats >= selectedBus.getCapacity()) {
            System.out.println("❌ Sorry! Bus is full.");
            return bookingCount;
        }

        // CONFIRM BOOKING
        Booking newBooking = new Booking(name, busNo, date);
        double fare = newBooking.calculateFare(selectedBus);
        newBooking.setFare(fare);

        bookings[bookingCount++] = newBooking;
        selectedBus.reduceCapacity();

        System.out.println("✅ Booking Confirmed!");
        System.out.println("💰 Fare Amount: ₹" + fare);

        return bookingCount;
    }

    // ADMIN SECTION
    public static int adminPanel(Bus[] buses, int busCount) {
        System.out.print("\nEnter Admin Username: ");
        String user = sc.next();
        System.out.print("Enter Password: ");
        String pass = sc.next();

        if (!user.equals("admin") || !pass.equals("1234")) {
            System.out.println("❌ Invalid login!");
            return busCount;
        }

        int option;
        do {
            System.out.println("\n====== 🔐 ADMIN PANEL ======");
            System.out.println("1. Add New Bus");
            System.out.println("2. Update Capacity");
            System.out.println("3. View Buses");
            System.out.println("4. Logout");
            System.out.print("Choose option: ");
            option = sc.nextInt();

            switch (option) {
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
                    System.out.println("🔓 Logged out.");
                    break;
                default:
                    System.out.println("❌ Invalid option!");
            }
        } while (option != 4);

        return busCount;
    }

    public static int addBus(Bus[] buses, int busCount) {
        System.out.print("Enter Bus No: ");
        int no = sc.nextInt();
        System.out.print("AC Bus? (true/false): ");
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
        System.out.println("✅ Bus Added Successfully!");
        return busCount;
    }

    public static void updateCapacity(Bus[] buses, int busCount) {
        System.out.print("Enter Bus No: ");
        int no = sc.nextInt();

        for (int i = 0; i < busCount; i++) {
            if (buses[i].getBusNo() == no) {
                System.out.print("Enter new capacity: ");
                int cap = sc.nextInt();
                buses[i].setCapacity(cap);
                System.out.println("✅ Capacity updated!");
                return;
            }
        }
        System.out.println("❌ Bus not found!");
    }

    public static void showBuses(Bus[] buses, int busCount) {
        System.out.println("\n------ AVAILABLE BUSES ------");
        for (int i = 0; i < busCount; i++) {
            buses[i].displayBusInfo();
        }
        System.out.println("------------------------------");
    }
}


