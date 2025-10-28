import java.text.*;
import java.util.*;

public class BusDemo {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        ArrayList<Bus> buses = new ArrayList<>();
        ArrayList<Booking> bookings = new ArrayList<>();

        buses.add(new Bus(1, true, 2, "Bangalore → Mysore", 150, 5));
        buses.add(new Bus(2, false, 3, "Bangalore → Chennai", 330, 4));
        buses.add(new Bus(3, true, 1, "Mangalore → Bangalore", 350, 6));

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
                    handleBooking(buses, bookings);
                    break;
                case 2:
                    showBuses(buses);
                    break;
                case 3:
                    adminPanel(buses);
                    break;
                case 4:
                    System.out.println("\nThank you for using the Bus Booking System!");
                    break;
                default:
                    System.out.println("❌ Invalid Option. Try again!");
            }

        } while (mainOption != 4);
    }

    // -------------------------
    // USER: BOOKING SECTION
    // -------------------------
    public static void handleBooking(ArrayList<Bus> buses, ArrayList<Booking> bookings) throws Exception {
        System.out.println("\nAvailable Buses:");
        showBuses(buses);

        System.out.print("\nEnter Passenger Name: ");
        String name = sc.next();

        System.out.print("Enter Bus No to book: ");
        int busNo = sc.nextInt();

        System.out.print("Enter Date (dd-MM-yyyy): ");
        String dateInput = sc.next();
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateInput);

        Booking booking = new Booking(name, busNo, date);

        if (booking.isAvailable(bookings, buses)) {
            booking.confirmBooking(bookings, buses);
        } else {
            System.out.println("❌ Sorry! Bus is full or not available on this date.");
        }
    }

    // -------------------------
    // ADMIN SECTION
    // -------------------------
    public static void adminPanel(ArrayList<Bus> buses) {
        System.out.print("\nEnter Admin Username: ");
        String user = sc.next();
        System.out.print("Enter Password: ");
        String pass = sc.next();

        if (user.equals("admin") && pass.equals("1234")) {
            int adminOpt;
            do {
                System.out.println("\n====== 🔐 ADMIN PANEL ======");
                System.out.println("1. Add New Bus");
                System.out.println("2. Update Bus Capacity");
                System.out.println("3. View All Buses");
                System.out.println("4. Logout");
                System.out.print("Choose an option: ");
                adminOpt = sc.nextInt();

                switch (adminOpt) {
                    case 1:
                        addBus(buses);
                        break;
                    case 2:
                        updateCapacity(buses);
                        break;
                    case 3:
                        showBuses(buses);
                        break;
                    case 4:
                        System.out.println("🔓 Logged out successfully!");
                        break;
                    default:
                        System.out.println("❌ Invalid option!");
                }

            } while (adminOpt != 4);
        } else {
            System.out.println("❌ Incorrect login credentials!");
        }
    }

    public static void addBus(ArrayList<Bus> buses) {
        System.out.print("Enter Bus No: ");
        int no = sc.nextInt();
        System.out.print("AC Bus? (true/false): ");
        boolean ac = sc.nextBoolean();
        System.out.print("Enter Capacity: ");
        int cap = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Route: ");
        String route = sc.nextLine();
        System.out.print("Enter Distance (in km): ");
        double dist = sc.nextDouble();
        System.out.print("Enter Base Fare per km: ");
        double fare = sc.nextDouble();

        buses.add(new Bus(no, ac, cap, route, dist, fare));
        System.out.println("✅ Bus Added Successfully!");
    }

    public static void updateCapacity(ArrayList<Bus> buses) {
        System.out.print("Enter Bus No to update: ");
        int no = sc.nextInt();
        for (Bus b : buses) {
            if (b.getBusNo() == no) {
                System.out.print("Enter new capacity: ");
                int cap = sc.nextInt();
                b.setCapacity(cap);
                System.out.println("✅ Capacity updated!");
                return;
            }
        }
        System.out.println("❌ Bus not found!");
    }

    public static void showBuses(ArrayList<Bus> buses) {
        System.out.println("\n------ AVAILABLE BUSES ------");
        for (Bus b : buses) {
            b.displayBusInfo();
        }
        System.out.println("------------------------------");
    }
}

