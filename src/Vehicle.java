import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Vehicle {

    private String make; /* Stores the name of the manufacturer. */
    private String model; /* Stores the name of the type of car. */
    private double mPG; /* Stores the average Miles Per Gallon. */

    /* Constructor - requires two string values for make and model and one double value for MPG. */
    public Vehicle(String make, String model, double mPG) {
        this.make = make;
        this.model = model;
        this.mPG = mPG;
    }

    /* Getter Methods */
    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public double getmPG() {
        return mPG;
    }

    /* Setter Methods */
    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setmPG(double mPG) {
        this.mPG = mPG;
    }

    /* toString method */
    @Override
    public String toString() {
        String s = make + " | " + model + " | " + mPG + " MPG";
        return s;
    }

    public static class rpgComparator implements Comparator<Vehicle> {

        @Override
        public int compare(Vehicle o1, Vehicle o2) {
            return Double.compare(o1.getmPG(), o2.getmPG());
        }
    }

    public static String printMenu() {

        /* Prints out the menu for the user to interact with. */

        String menu;
        menu = System.out.printf("**********Menu**********\n" +
                                 "* 1. Enter A Vehicle.  *\n" +
                                 "* 2. Print to txt      *\n" +
                                 "* 3. Exit.             *\n" +
                                 "************************\n" +
                                 "Please make a selection: ").toString();
        return menu;
    }

    public static void addVehicle(LinkedList<Vehicle> vehicleLinkedList) {

        /*
         *  Ask user to enter car make. Store string input in variable.
         *  Ask user to enter car model. Store string input in variable.
         *  Ask user to enter mileage.
         *      - If value entered is not a double. Display error message and ask for valid input.
         *  Generate Vehicle object, add to Linked List, print success message.
         */

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter car make: ");
        String make = scanner.next();


        System.out.println("Enter car model: ");
        String model = scanner.next();


        System.out.println("Enter Mileage: ");
        while (!scanner.hasNextDouble()) {

            System.out.print ("That was not a valid entry. Please enter a valid decimal value: ");
            scanner.next();
        }

        double mpg = scanner.nextDouble();
        Vehicle vehicle = new Vehicle(make, model, mpg);
        vehicleLinkedList.add(vehicle);
        System.out.println(vehicle.toString() + " Was added to the inventory!");

    }

    public static void printInventory(LinkedList<Vehicle> vehicleLinkedList) {

        /*
         *  This method takes a linked list of Vehicle objects as an argument.
         *  It sorts the list of Vehicle objects based off MPG in ascending order.
         *  It prints the resulting list to a .txt file named "inventory.txt".
         */

        Comparator<Vehicle> comparator = new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                return Double.compare(o1.getmPG(), o2.getmPG());
            }
        };

        vehicleLinkedList.sort(comparator);

        try {

            PrintWriter writer = new PrintWriter(new File("inventory.txt"));
            for (Vehicle vehicle : vehicleLinkedList) {
                writer.println(vehicle.toString());
            }
            System.out.println("The inventory was written to inventory.txt!");
            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        /*
         *  This program displays a menu with options for a user to select.
         *      - Option 1: The user adds a new vehicle to the inventory.
         *      - Option 2: Prints the sorted vehicle inventory to a .txt file.
         *      - Option 3: Exit program.
         */

        Scanner scanner = new Scanner(System.in);
        LinkedList<Vehicle> vehicleLinkedList = new LinkedList<Vehicle>();
        int userChoice = 0;

        while (userChoice != 3) {

            try {

                printMenu();
                userChoice = scanner.nextInt();

                if (userChoice == 1) addVehicle(vehicleLinkedList);

                else if (userChoice == 2) printInventory(vehicleLinkedList);

                else System.out.println("That is not a valid selection. Please enter a number from the menu.");

                } catch (InputMismatchException e) {

                    System.out.println("That is not a valid selection. Please enter a number from the menu.");
                    scanner.nextLine();
                }
        }
    }
}