public class Screens {
    private static double totalProfit;

    // Based on the feedback from the last milestone we moved the screenChoice method to here
    // The only changes in this class is the addition of new methods that are used in the different epic features

    public static void addTotalProfit(double profit){
        totalProfit = totalProfit + profit;
    }

    public static void screenChoice(char x) throws Exception {
        switch (x) {
            case 'm','M' -> {
                if (Tools.password("admin1234")) managerScreen();
            }
            case 'e','E' -> {
                if (Tools.password("password123")) employeeScreen();
            }
            case 'c','C' -> preCustomerScreen();
            case 'x','X' -> DartMain.exitProgram();
        }
    }

    public static void mainMenu(){ //Wall of text function
        System.out.println("Main Menu:");
        System.out.println("Welcome to DART, your good old game rental system. The competition has no steam to keep up!");
        System.out.println("Please specify your role by entering one of the options given:");
        System.out.println("1. Enter “M” for Manager");
        System.out.println("2. Enter “E” for Employee");
        System.out.println("3. Enter “C” for Customer");
        System.out.println("4. Enter “X” to exit system ");
    }

    public static void preCustomerScreen() throws Exception {
        Customer customer = Controller.getCustomer(Tools.getString("Enter ID to log in"));
        if (customer == null){
            System.out.println("ID does not exist");
            mainMenu();
        } else {
            customerScreen(customer);
        }
    }

    public static void customerScreen(Customer customer) throws Exception {
        char choice;
        String screens = "1234567";
        System.out.println("Customer Screen - Type one of the options below:");
        System.out.println("1. Rent an item");
        System.out.println("2. Return an item");
        System.out.println("3. Send message");
        System.out.println("4. View unread messages");
        System.out.println("5. Remove a message");
        System.out.println("6. Request membership upgrade");
        System.out.println("7. Return to Main Menu");
        choice = Tools.getChar("");
        Tools.validateChar(choice, screens);
        switch(choice) {
            case '1' -> {
                int sorting;
                int rentItem = Tools.getInt("1. Game" + System.lineSeparator() + "2. Song Album");
                if (rentItem == 1) {
                    sorting = Tools.getInt("1. Show all games" + System.lineSeparator() + "2. Search by genre" + System.lineSeparator() + "3. Sort by ratings" + System.lineSeparator() + "4. Sort by year");
                } else {
                    sorting = Tools.getInt("1. Show all songs" + System.lineSeparator() + "2. Search by year" + System.lineSeparator() + "3. Sort by ratings" + System.lineSeparator() + "4. Sort by year");
                }
                if (sorting == 2 && rentItem == 1){ //Shows games, searched by genre
                    System.out.println(Controller.showItems(rentItem, sorting, Tools.getString("Enter genre: ")));
                } else if (sorting == 2 && rentItem == 2){ //Shows songs, searched by year
                    System.out.println(Controller.showItems(rentItem, sorting, Tools.getString("Enter year:")));
                } else { //Shows selected item, sorted by selected sorting
                    System.out.println(Controller.showItems(rentItem, sorting, ""));
                }

                if (Controller.rentItem(rentItem, Tools.getString("Enter ID of item to rent"), Tools.getString("What is the rent date? (YYYY-MM-DD)"), customer)){
                    System.out.println("Successfully rented");
                } else {
                    System.out.println("Item could not be rented");
                }
                customerScreen(customer);
            }

            case '2' -> {
                System.out.println("Current library" + System.lineSeparator() + customer.viewRented());
                RentHistoryItem results = Controller.returnItem(Tools.getString("Enter ID of item to return"), Tools.getString("Leave a review? (leave blank otherwise): "),
                        Tools.getInt("Rating?: (1-5) 0 to skip"), Tools.getString("What is the return date? (YYYY-MM-DD)"), customer);
                if (results == null){
                    System.out.println("Unable to return item");
                } else {
                    System.out.println("Successfully returned");
                    System.out.println(results);
                }
                customerScreen(customer);
            }

            case '3' -> {
                Controller.sendMessage(Tools.getString("Enter Message: "), Tools.getString("Enter id of recipient: "), customer);
                customerScreen(customer);
            }
            case '4' -> {
                System.out.println(Controller.viewUnread(customer));
                customerScreen(customer);
            }
            case '5' -> {
                System.out.println(Controller.viewInbox(customer));
                Controller.removeMessage(Tools.getInt("Enter index of message to remove: ") - 1, customer);
                customerScreen(customer);
            }
            case '6' -> {
                Controller.requestUpgrade(customer);
                customerScreen(customer);
            }
            case '7' -> {
            }
        }
    }

    //
    public static void managerScreen() throws Exception {
        String screens = "123456789";
        System.out.println("Manager Screen - Type one of the options below:");
        System.out.println("1. Add an employee");
        System.out.println("2. View all employees");
        System.out.println("3. Remove an employee");
        System.out.println("4. Calculate net salary of employee");
        System.out.println("5. Calculate and give bonus to employee");
        System.out.println("6. View rent frequency");
        System.out.println("7. Most profitable item");
        System.out.println("8. Most valued customer");
        System.out.println("9. Return to Main Menu");
        char choice = Tools.getChar("");
        Tools.validateChar(choice, screens);
        switch (choice) {
            case '1' -> System.out.println(regEmployee());
            case '2' -> System.out.println(Controller.viewAllEmployee());
            case '3' -> {
                if(Controller.removeEmployee(Tools.getString("Enter the ID of employee you want to remove: ")))
                    System.out.println("Successfully removed!");
                else
                    System.out.println("Error removing employee.");
            }
            case '4' -> System.out.println("Employee net salary is: " + Controller.calcNetSalary(Tools.getString("Enter ID of employee to calculate net salary: ")));
            case '5' -> System.out.println("Employee bonus is: " + Controller.bonus(Tools.getString("Enter ID of employee to see what bonus employee is eligible to: ")));
            case '6' -> System.out.println(Controller.viewRentFrequency());
            case '7' -> {
                if(Controller.mostProfitable() != null) System.out.println("Most profitable item: " + Controller.mostProfitable());
            }
            case '8' -> System.out.println("Most valued customer: " + Controller.mostProfitableCustomer());
            case '9' -> {
            }
        }
    }

    public static Employee regEmployee() {
        Employee printContainer = null;
        try {
            printContainer = Controller.registerEmployee(
                    Tools.getString("Enter employee name: "),
                    Tools.getString("Enter employee address: "),
                    Tools.getInt("Enter employee birth year: "),
                    Tools.getDouble("Enter employee gross salary: "));
            System.out.println("Random ID <" + printContainer.getID() + "> was assigned.");
        } catch (Exception exception){
            System.out.println(exception);
            regEmployee();
        }
        System.out.println("Successfully added employee: ");
        return printContainer;
    }

    public static void employeeScreen() throws Exception {
        char choice;
        String screens = "1234567890a";
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
        Tools.validateChar(choice, screens);
        switch (choice) {
            case '1' -> {
                int type = Tools.getInt("What would you like to register? \n 1. Game \n 2. Song");
                if(type == 1){
                    System.out.println("Added game: " +
                        Controller.registerItem(type, Tools.getString("Enter game title: "),
                        Tools.getString("Enter game genre: "),
                        Tools.getDouble("Enter game rent cost: "),
                        Tools.getInt("Enter game release year: ")));
                } else {
                    System.out.println("Added song: " +
                        Controller.registerItem(type, Tools.getString("Enter song title: "),
                        Tools.getString("Enter artist: "),
                        Tools.getDouble("Enter song rent cost: "),
                        Tools.getInt("Enter song release year: ")));
                }

            }
            case '2' -> {
                if(Controller.removeItem(Tools.getInt("What item would you like to remove? \n 1. Game \n 2. Song"), Tools.getString("Please enter the ID of this item: ")))
                    System.out.println("Successfully removed!");
            }
            case '3' -> System.out.println("Successfully registered customer: " + Controller.registerCustomer(Tools.getString("Enter the employee's name: ")));
            case '4' -> {
                if(Controller.removeCustomer(Tools.getString("Enter the ID of the customer you want to remove: ")))
                    System.out.println("Successfully removed!");
                else
                    System.out.println("Error removing customer.");
            }
            case '5' -> {System.out.println("Total profit is: " + totalProfit); employeeScreen();}
            case '6' -> Controller.showItems();
            case '7' -> Controller.viewAllCustomer();
            case '8' -> Employee.fillGames();
            case '9' -> Controller.viewAllUpgRequest();
            case 'a' -> {
                if(Controller.upgradeCustomer(Tools.getString("Enter the ID of the customer you want to upgrade: "))){
                    System.out.println("Successfully ugpraded!");
                } else
                    System.out.println("Error upgrading customer.");
            }
            case '0' -> {
            }
        }
    }
}


