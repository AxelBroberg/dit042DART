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
        String ID = Tools.getString("Please enter your ID");
        for(int i = 0; i < Employee.customerList.size(); i++){
            if(Employee.customerList.get(i).getID().equals(ID)){
                customerScreen(ID);
            }
            if(i == Employee.customerList.size()){
                System.out.println("Invalid ID. Sending you back to the main menu");
                mainMenu();
            }
        }
    }

    public static void customerScreen(String ID) throws Exception {
        char choice;
        String screens = "123456789";
        System.out.println("Customer Screen - Type one of the options below:");
        System.out.println("1. Rent a game");
        System.out.println("2. Return a game");
        System.out.println("3. Rent a song");
        System.out.println("4. Return a song");
        System.out.println("5. Send message");
        System.out.println("6. View unread messages");
        System.out.println("7. Remove a message");
        System.out.println("8. Request membership upgrade");
        System.out.println("9. Return to Main Menu");
        choice = Tools.getChar("");
        Tools.validateChar(choice, screens);
        switch(choice) {
            case '1' -> CustomerController.rentItem("Game", ID);
            case '2' -> CustomerController.returnItem("Game", ID);
            case '3' -> CustomerController.rentItem("Song", ID);
            case '4' -> CustomerController.returnItem("Song", ID);
            case '5' -> CustomerController.sendMessage(ID);
            case '6' -> {
                Employee.customerList.get(Employee.findCustomer(ID)).viewUnread();
                customerScreen(ID);
            }
            case '7' -> CustomerController.removeMessage(ID);
            case '8' -> CustomerController.requestUpgrade(ID);
            case '9' -> {
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
            case '1' -> {
                System.out.println(regEmployee());
            }
            case '2' -> System.out.println(Controller.viewAllEmployee());
            case '3' -> {
                Controller.removeEmployee(Tools.getString("Enter the ID of employee you want to remove: "));
                System.out.println("Successfully removed!");
            }
            case '4' -> Manager.calcNetSalary();
            case '5' -> Manager.bonus();
            case '6' -> Manager.viewRentFrequency();
            case '7' -> Manager.mostProfitable();
            case '8' -> Manager.mostProfitableCustomer();
            case '9' -> {
            }
        }
    }

    public static Employee regEmployee() throws Exception {
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
        System.out.println("6. View all games");
        System.out.println("7. View all customers");
        System.out.println("8. Fill games + songs");
        System.out.println("9. View upgrade requests");
        System.out.println("a. Upgrade member");
        System.out.println("0. Return to Main Menu");
        choice = Tools.getChar("");
        Tools.validateChar(choice, screens);
        switch (choice) {
            case '1' -> Employee.registerItem("game");
            case '2' -> Employee.removeItem("game");
            case '3' -> Employee.registerCustomer();
            case '4' -> Employee.removeCustomer();
            case '5' -> {System.out.println("Total profit is: " + totalProfit); employeeScreen();}
            case '6' -> GameController.empViewAllGames();
            case '7' -> Employee.viewAllCustomer(true);
            case '8' -> Employee.fillGames();
            case '9' -> Employee.viewAllUpgRequest();
            case 'a' -> Employee.upgradeCustomer();
            case '0' -> {
            }
        }
    }
}


