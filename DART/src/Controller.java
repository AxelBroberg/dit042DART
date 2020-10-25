import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private ArrayList<Employee> employeeArrayList;
    private ArrayList<RentHistoryItem> rentHistory;
    private ArrayList<Customer> customerList;
    private ArrayList<Customer> upgradeRequests;
    private ArrayList<Rentable> itemsList;
    private static double totalRentProfit = 0;
    Scanner input = new Scanner(System.in);

    public Controller(){
        this.employeeArrayList = new ArrayList<>();
        this.rentHistory = new ArrayList<>();
        this.itemsList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.upgradeRequests = new ArrayList<>();
    }

    public void mainMenu() throws Exception {
        char choice = Tools.getChar("Main Menu: \n " +
                        "Welcome to DART, your good old game rental system. The competition has no steam to keep up! \n" +
                        "Please specify your role by entering one of the options given: \n" +
                        "1. Enter “M” for Manager \n" +
                        "2. Enter “E” for Employee \n" +
                        "3. Enter “C” for Customer \n" +
                        "4. Enter “X” to exit system \n");
        if(Tools.validateChar(choice, "MECXmecx"))
        switch (choice) {
            case 'm','M' -> {
                boolean verified = Tools.password("admin1234");
                if(verified){
                    managerLoggedIn();
                }
            }
            case 'e','E' -> {
                boolean verified = Tools.password("password123");
                if(verified){
                    String name = Tools.getString("Enter employee name: ", input);
                    for (Employee employee : employeeArrayList)
                        if(employee.getName().equals(name))
                            employeeLoggedIn(employee);
                }
            }
            case 'c','C' -> {
                String ID = Tools.getString("Enter customer ID", input);
                for (Customer customer : customerList)
                    if (customer.getID().equals(ID))
                        if(Tools.getString("Enter password: ", input).equals(customer.getPassword()))
                            customerLoggedIn(customer);
            }
            case 'x','X' -> { input.close();
                Tools.exitProgram(); }
        }
    }

    public void managerLoggedIn() throws IOException {
        Manager manager = new Manager();
        char choice;
        String screens = "1234567890a";
        do {
            System.out.println("Manager Screen - Type one of the options below:");
            System.out.println("1. Add an employee");
            System.out.println("2. View all employees");
            System.out.println("3. Remove an employee");
            System.out.println("4. Calculate net salary of employee");
            System.out.println("5. Calculate and give bonus to employee");
            System.out.println("6. View rent frequency");
            System.out.println("7. Most profitable item");
            System.out.println("8. Most valued customer");
            System.out.println("9. Load from file");
            System.out.println("a. Save rent history");
            System.out.println("0. Return to Main Menu");

            choice = Tools.getChar("");
            if(Tools.validateChar(choice, screens))
            switch (choice) {
                case '1' -> {
                    String name = Tools.getString("Enter employee name: ", input);
                    String address = Tools.getString("Enter employee address: ", input);
                    int bYear = Tools.getInt("Enter employee birth year: ");
                    double gross = Tools.getDouble("Enter employee gross salary: ");
                    manager.registerEmployee(employeeArrayList, name, address, bYear, gross, input);
                }
                case '2' -> System.out.println(manager.viewAllEmployee(employeeArrayList));
                case '3' -> {
                    String empId = Tools.getString("Enter the ID of employee you want to remove: ", input);
                    Employee toRemove = manager.findEmployee(employeeArrayList, empId);
                if(manager.removeEmployee(employeeArrayList, toRemove))
                    System.out.println("Successfully removed!");
                else
                    System.out.println("Error removing employee.");
                }
                case '4' -> {
                    String empId = Tools.getString("Enter ID of employee to calculate net salary: ", input);
                    Employee toFind = manager.findEmployee(employeeArrayList, empId);
                    System.out.println("Employee net salary is: " + manager.calcNetSalary(toFind));
                }
                case '5' -> {
                    String empId = Tools.getString("Enter ID of employee to get their bonus", input);
                    Employee toFind = manager.findEmployee(employeeArrayList, empId);
                    System.out.println("Employee bonus is: " + manager.bonus(toFind));
                }
                case '6' -> {
                    System.out.println(manager.viewRentFrequency(itemsList));
                }
                case '7' -> {
                    if(manager.mostProfitable(itemsList) != null) System.out.println("Most profitable item: " + manager.mostProfitable(itemsList));
                }
                case '8' ->{
                    if(manager.mostProfitable(itemsList) != null) System.out.println("Most valued customer: " + manager.mostProfitableCustomer(customerList));
                }
                case '9' -> {
                    manager.readFile(employeeArrayList, customerList, itemsList, input);
                }
                case 'a' -> {
                    manager.writeFile(rentHistory);
                }
                case '0' -> {
                }
            }
        }while(choice != '0');
    }

    public void employeeLoggedIn(Employee employee) throws Exception {
        char choice;
        String screens = "1234567890a";
        do {
        System.out.println("Employee Screen - Type one of the options below:");
        System.out.println("1. Register a game");
        System.out.println("2. Remove a game");
        System.out.println("3. Register a customer");
        System.out.println("4. Remove a customer");
        System.out.println("5. Show total rent profit");
        System.out.println("6. View all games & songs");
        System.out.println("7. View all customers");
        System.out.println("8. Fill games + songs");
        System.out.println("9. View upgrade requests");
        System.out.println("a. Upgrade member");
        System.out.println("0. Return to Main Menu");
        choice = Tools.getChar("");
        if(Tools.validateChar(choice, screens))
            switch (choice) {
                case '1' -> {
                    int type = Tools.getInt("What would you like to register? \n 1. Game \n 2. Song");
                    if (type == 1) {
                        System.out.println("Added game: " +
                                employee.registerItem(itemsList,
                                        type, Tools.getString("Enter game title: ", input),
                                        Tools.getString("Enter game genre: ", input),
                                        Tools.getDouble("Enter game rent cost: "),
                                        Tools.getInt("Enter game release year: ")));
                    } else {
                        System.out.println("Added song: " +
                                employee.registerItem(itemsList,
                                        type, Tools.getString("Enter song title: ", input),
                                        Tools.getString("Enter artist: ", input),
                                        Tools.getDouble("Enter song rent cost: "),
                                        Tools.getInt("Enter song release year: ")));
                    }

                }
                case '2' -> {
                    if (employee.removeItem(itemsList, employee.findItem(itemsList, Tools.getString("Please enter the ID of this item: ", input))))
                        System.out.println("Successfully removed!");
                    else
                        System.out.println("Error removing item.");
                }
                case '3' -> System.out.println("Successfully registered customer: " + employee.registerCustomer(customerList, Tools.getString("Enter the customer's name: ", input), Tools.getString("Enter password for customer: ", input), input));
                case '4' -> {
                    if (employee.removeCustomer(customerList, employee.getCustomer(customerList, Tools.getString("Enter the ID of the customer you want to remove: ", input))))
                        System.out.println("Successfully removed!");
                    else
                        System.out.println("Error removing customer.");
                }
                case '5' -> {
                    System.out.println("Total profit is: " + totalRentProfit);
                }
                case '6' -> System.out.println(employee.showItems(itemsList));
                case '7' -> System.out.println(employee.viewAllCustomer(customerList));
                case '8' -> employee.fillGames(itemsList);
                case '9' -> System.out.println(employee.viewAllUpgRequest(upgradeRequests)); //DOESNT WORK
                case 'a' -> {
                    if (employee.upgradeCustomer(customerList, employee.getCustomer(customerList, Tools.getString("Enter the ID of the customer you want to upgrade: ", input)))) {
                        System.out.println("Successfully upgraded!");
                    } else
                        System.out.println("Error upgrading customer.");
                }
                case '0' -> {
                }
            }
        }while(choice != '0');
    }

    public void customerLoggedIn(Customer customer){
        char choice;
        do {
            String screens = "12345678";
            System.out.println("Customer Screen - Type one of the options below:");
            System.out.println("1. Rent an item");
            System.out.println("2. Return an item");
            System.out.println("3. Send message");
            System.out.println("4. View unread messages");
            System.out.println("5. Remove a message");
            System.out.println("6. Request membership upgrade");
            System.out.println("7. View current membership");
            System.out.println("8. Return to Main Menu");
            choice = Tools.getChar("");
            if(Tools.validateChar(choice, screens))
            switch (choice) {
                case '1' -> {
                    int sorting;
                    int rentItem = Tools.getInt("1. Game" + System.lineSeparator() + "2. Song Album");
                    if (rentItem == 1) {
                        sorting = Tools.getInt("1. Show all games" + System.lineSeparator() + "2. Search by genre" + System.lineSeparator() + "3. Sort by ratings" + System.lineSeparator() + "4. Sort by year");
                    } else {
                        sorting = Tools.getInt("1. Show all songs" + System.lineSeparator() + "2. Search by year" + System.lineSeparator() + "3. Sort by ratings" + System.lineSeparator() + "4. Sort by year");
                    }
                    if (sorting == 2 && rentItem == 1) {
                        System.out.println(customer.showItems(itemsList, rentItem, sorting, Tools.getString("Enter genre: ", input)));
                    } else if (sorting == 2 && rentItem == 2) {
                        System.out.println(customer.showItems(itemsList, rentItem, sorting, Tools.getString("Enter year:", input)));
                    } else {
                        System.out.println(customer.showItems(itemsList, rentItem, sorting, ""));
                    }

                    if (customer.rentItem(customer.findItem(itemsList, rentItem, Tools.getString("Enter ID of item to rent", input)), Tools.getString("What is the rent date? (YYYY-MM-DD)", input))) {
                        System.out.println("Successfully rented");
                    } else {
                        System.out.println("Item could not be rented");
                    }

                }

                case '2' -> {
                    System.out.println("Current library" + System.lineSeparator() + customer.viewRented());
                    RentHistoryItem results = customer.returnItem(
                            Tools.getString("Enter ID of item to return", input),
                            Tools.getString("Leave a review? (leave blank otherwise): ", input),
                            Tools.getInt("Rating?: (1-5) 0 to skip"),
                            Tools.getString("What is the return date? (YYYY-MM-DD)", input));
                    if (results == null) {
                        System.out.println("Unable to return item");
                    } else {
                        System.out.println("Successfully returned");
                        totalRentProfit = totalRentProfit + results.getRentExpense();
                        rentHistory.add(results);
                        System.out.println(results.toString());
                    }
                }

                case '3' -> {
                    customer.sendMessage(customerList, Tools.getString("Enter Message: ", input), Tools.getString("Enter id of recipient: ", input), customer);
                }
                case '4' -> {
                    System.out.println(customer.viewUnread(customer));
                }
                case '5' -> {
                    System.out.println(customer.viewInbox(customer));
                    customer.removeMessage(Tools.getInt("Enter index of message to remove: ") - 1, customer);
                }
                case '6' -> {
                    customer.requestUpgrade(upgradeRequests, customer);
                }
                case '7' -> {
                    System.out.println(customer.getStrMembership());
                }
                case '8' -> {
                }
            }
        }while(choice != '8');
    }
}
