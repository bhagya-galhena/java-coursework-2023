import java.io.*;
import java.util.*;

public class Task_One implements Serializable {
    // create 2D array for View all Queues
    private final Customer[][] cashiers = {{null, null, null, null, null}, {null, null, null}, {null, null}};
    private int burger_stock = 50;

    // Foodies Fave Food Center Main Menu
    private void main_menu() {
        System.out.println("-------------------------------------------------------------------------" +
                "-------------------------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\tFoodies Fave Food Center");
        System.out.println("-------------------------------------------------------------------------" +
                "-------------------------------------------------------------------------------------");
        System.out.println("------------------ Main Menu ------------------ ");
        System.out.println("100 or VFQ: View all Queues.");
        System.out.println("101 or VEQ: View all Empty Queues.");
        System.out.println("102 or ACQ: Add customer to a Queue.");
        System.out.println("103 or RCQ: Remove a customer from a Queue. (From a specific location)");
        System.out.println("104 or PCQ: Remove a served customer.");
        System.out.println("105 or VCS: View Customers Sorted in alphabetical order (Do not use library sort routine)");
        System.out.println("106 or SPD: Store Program Data into file.");
        System.out.println("107 or LPD: Load Program Data from file");
        System.out.println("108 or STK: View Remaining burgers Stock.");
        System.out.println("109 or AFS: Add burgers to Stock");
        System.out.println("999 or EXT: Exit the Program.");

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPlease enter Your Main Menu Code: ");
        String menu_code = scanner.nextLine();

        if (menu_code.equals("100") || menu_code.equalsIgnoreCase("VFQ")) {
            view_all_queues();
        } else if (menu_code.equals("101") || menu_code.equalsIgnoreCase("VEQ")) {
            view_all_empty_queue();
        } else if (menu_code.equals("102") || menu_code.equalsIgnoreCase("ACQ")) {
            add_customer_to_a_queue();
            main_menu();
        } else if (menu_code.equals("103") || menu_code.equalsIgnoreCase("RCQ")) {
            remove_customer_fromQueue();
        } else if (menu_code.equals("104") || menu_code.equalsIgnoreCase("PCQ")) {
            remove_a_servedCustomer();
        } else if (menu_code.equals("105") || menu_code.equalsIgnoreCase("VCS")) {
            view_customers_sorted_in_alphabetical_order();
//            System.out.println("View Customers Sorted in alphabetical order (Do not use library sort routine)");

        } else if (menu_code.equals("106") || menu_code.equalsIgnoreCase("SPD")) {
            store_program_data();
        } else if (menu_code.equals("107") || menu_code.equalsIgnoreCase("LPD")) {
            load_programme_data();
        } else if (menu_code.equals("108") || menu_code.equalsIgnoreCase("STK")) {
            view_remaining_burgers_stock();
        } else if (menu_code.equals("109") || menu_code.equalsIgnoreCase("AFS")) {
            add_burgers_to_stock();
        } else if (menu_code.equals("999") || menu_code.equalsIgnoreCase("EXT")) {
            System.exit(0);
        } else {
            System.out.println("Invalid code!.Please reenter your code.");
            main_menu();
        }
    }

    private void remove_a_servedCustomer() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the cashier number: ");
            int cashier_number = scanner.nextInt();
            Customer[] temp_cashier = cashiers[cashier_number - 1];
            if (burger_stock >= temp_cashier[0].getBurger_amount()) {
                burger_stock -= temp_cashier[0].getBurger_amount();
                for (int i = 0; i < temp_cashier.length; i++) {
                    if (i == temp_cashier.length - 1) {
                        temp_cashier[i] = null;
                    } else {
                        temp_cashier[i] = temp_cashier[i + 1];
                    }
                }
            } else {
                System.out.println("Stock is too low to serve");
            }
            // give warning low stock
            main_menu();
        } catch (InputMismatchException | ArrayIndexOutOfBoundsException inputMismatchException) {
            System.out.println("Invalid cashier number");
            remove_a_servedCustomer();
        }
    }

    private void remove_customer_fromQueue() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the cashier number of the Customer: ");
            int cashier_number = scanner.nextInt();
            Customer[] temp_cashier = cashiers[cashier_number - 1];
            System.out.print("Enter the position of the Customer: ");
            int position = scanner.nextInt();
            if (position > temp_cashier.length) {
                System.out.println("Invalid position");
                remove_customer_fromQueue();
            } else {
                if (null == temp_cashier[position - 1]) {
                    System.out.println("No customer found");
                } else {
                    temp_cashier[position - 1] = null;
                    System.out.println("Customer removal success");
                }
                main_menu();
            }
        } catch (InputMismatchException | ArrayIndexOutOfBoundsException inputMismatchException) {
            System.out.println("Invalid cashier number");
            remove_customer_fromQueue();
        }
    }

    // create method for View all Queues
    private void view_all_queues() {
        System.out.println("View all Queues.");

        System.out.println("* * * * * * * * * * * * * * * * * * ");
        System.out.println("* \t\t\t Cashiers \t\t\t  *");
        System.out.println("* * * * * * * * * * * * * * * * * * ");

        boolean max_customer = false;
        int customer_count = 0;
        while (!max_customer) {
            int done = 0;
            System.out.print("\t\t\t");
            for (Customer[] cashier : cashiers) {
                if (cashier.length > customer_count)
                    if (null == cashier[customer_count]) {
                        System.out.print("0" + "   ");
                    } else {
                        System.out.print("X" + "   ");
                    }
                else done++;
            }
            System.out.println();
            customer_count++;
            if (done == cashiers.length) max_customer = true;
        }
        System.out.println("X-Occupied O-Not Occupied");
        main_menu();
    }

    //create method for View all Empty Queues
    private void view_all_empty_queue() {
        for (int i = 0; i < cashiers.length; i++) {
            boolean is_empty = true;
            for (int j = 0; j < cashiers[i].length; j++) {
                if (null != cashiers[i][j]) {
                    is_empty = false;
                    break;
                }
            }
            if (is_empty) {
                System.out.println(i + 1 + " queue Is Empty");
            }
        }
        main_menu();
    }

    // create method for  Add customer to a Queue
    private void add_customer_to_a_queue() {
        for (int i = 0; i < cashiers.length; i++) {
            for (int j = 0; j < cashiers[i].length; j++) {
                if (null == cashiers[i][j]) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Please enter customer first name: ");
                    String first_name = scanner.nextLine();
                    System.out.print("Please enter customer second name: ");
                    String second_name = scanner.nextLine();
                    System.out.print("Please enter customer burger count: ");
                    int burger_count = scanner.nextInt();
                    Customer customer = new Customer(first_name, second_name, burger_count);
                    cashiers[i][j] = customer;
                    System.out.println("Customer adding success");
                    return;
                }
            }
        }
        System.out.println("All Queues are full");
    }

    // create method for View Customers Sorted in alphabetical order
    private void view_customers_sorted_in_alphabetical_order() {
        String[] names = new String[10];
        int count = 0;
        for (Customer[] cashier : cashiers) {
            for (int j = 0; j < cashier.length; j++) {
                if (cashier[j] != null) {
                    String currentName = cashier[j].getFirst_name() + " " + cashier[j].getLast_name();
                    int index = count;
                    while (index > 0 && currentName.compareTo(names[index - 1]) < 0) {
                        names[index] = names[index - 1];
                        index--;
                    }
                    names[index] = currentName;
                    count++;
                }
            }
        }
        for (int i = 0; i < count; i++) {
            System.out.println(names[i]);
        }
        main_menu();
    }

    // Store Program Data into file.
    private void store_program_data() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("coursework_data_file.txt"))) {
            for (int i = 0; i < cashiers.length; i++) {
                for (int j = 0; j < cashiers[i].length; j++) {
                    if (cashiers[i][j] != null) {
                        writer.write(
                                cashiers[i][j].getFirst_name() + "-"
                                        + cashiers[i][j].getLast_name() + "-"
                                        + cashiers[i][j].getBurger_amount() + "-"
                                        + i + "-" + j
                        );
                        writer.newLine();
                    }
                }
            }
            System.out.println("Array stored to file successfully.");
        } catch (IOException e) {
            System.out.println("Error storing array to file: " + e.getMessage());
        }
        main_menu();
    }

    // Load Program Data from file
    private void load_programme_data() {
        try (BufferedReader reader = new BufferedReader(new FileReader("coursework_data_file.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] line_data = line.split("-");
                cashiers[Integer.parseInt(line_data[3])][Integer.parseInt(line_data[4])] = new Customer(
                        line_data[0], line_data[1], Integer.parseInt(line_data[2])
                );
            }
            System.out.println("Array loading from file successfully.");
        } catch (IOException e) {
            System.out.println("Error loading array from file: " + e.getMessage());
        }
        main_menu();
    }

    // View Remaining burgers Stock.
    private void view_remaining_burgers_stock() {
        System.out.println("Remaining burger stock is: " + burger_stock);
        main_menu();
    }

    private void add_burgers_to_stock() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the burger amount: ");
        int burger_amount = scanner.nextInt();
        burger_stock += burger_amount;
        main_menu();
    }

    public static void main(String[] args) throws IOException {
        // coursework name and student name
        System.out.println("-------------------------------------------------------------------------" +
                "-------------------------------------------------------------------------------------");
        System.out.println("\t\t\t\t\t\t\t\t\t\t4COSC010C.3 Software Development II Course Work" + " " + "Bhagya Galhena.");
        // shop name
        System.out.println("\t\t\t\t\t\t\t\t\t\tFoodies Fave Food Center");
        // task one
        System.out.println("\t\t\t\t\t\t\t\t\t\tTask 1. Arrays version");
        System.out.println("-------------------------------------------------------------------------" +
                "-------------------------------------------------------------------------------------");
        Task_One object = new Task_One();
        object.main_menu();
    }
}
