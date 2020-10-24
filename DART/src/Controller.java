import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private ArrayList<Employee> employeeArrayList;
    private ArrayList<RentHistoryItem> rentHistory;
    private ArrayList<Customer> customerList;//creates ArrayList named 'customerList' containing Customers
    private ArrayList<Customer> upgradeRequests;
    private ArrayList<Rentable> itemsList;// adapt!
    /*static ArrayList<Rentable> songList = new ArrayList();
    static ArrayList<Rentable> gameList = new ArrayList<>();*/
    private static double totalRentProfit = 0;
    //private static Manager manager = new Manager();
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
                // if (Tools.password("admin1234")) managerScreen();
                boolean verified = Tools.password("admin1234");
                if(verified){
                    managerLoggedIn();
                }
            }
            case 'e','E' -> {
                //if (Tools.password("password123")) employeeScreen();
                boolean verified = Tools.password("password123");
                if(verified){
                    String name = Tools.getString("Enter employee name: ", input);
                    for (Employee employee : employeeArrayList)
                        if(employee.getName().equals(name))
                            employeeLoggedIn(employee);
                }
            }
            case 'c','C' -> {
                //preCustomerScreen();
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
                    if (sorting == 2 && rentItem == 1) { //Shows games, searched by genre
                        System.out.println(customer.showItems(itemsList, rentItem, sorting, Tools.getString("Enter genre: ", input)));
                    } else if (sorting == 2 && rentItem == 2) { //Shows songs, searched by year
                        System.out.println(customer.showItems(itemsList, rentItem, sorting, Tools.getString("Enter year:", input)));
                    } else { //Shows selected item, sorted by selected sorting
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
                    // customerScreen(customer);
                }
                case '4' -> {
                    System.out.println(customer.viewUnread(customer));
                    // customerScreen(customer);
                }
                case '5' -> {
                    System.out.println(customer.viewInbox(customer));
                    customer.removeMessage(Tools.getInt("Enter index of message to remove: ") - 1, customer);
                    // customerScreen(customer);
                }
                case '6' -> {
                    customer.requestUpgrade(upgradeRequests, customer);
                    // customerScreen(customer);
                }
                case '7' -> {
                    System.out.println(customer.getStrMembership());
                }
                case '8' -> {
                }
            }
        }while(choice != '8');
    }




        //TODO
    /*
    * move the methods from controller to the right business class
    * remove all statics
    * inside the controller, don't forget to decalre the lists in the class then initialize them in the controller's constructor
    * for the sub-menu, don't forget the do-while loops that break when choice!=()
    * call methods though object!!
    * private attributes EVERYWHERE, why? encapsulation!
    * adapt to the itemsList
    * close scanner
    * membership via inheritance



    * double-check with the feedback!
    * */
















    /*
    public double calcNetSalary(String ID) {
        double netSalary = manager.calcNetSalary(findEmployee(ID));
        return netSalary;
    }

    public int bonus(String ID) {
        return manager.bonus(findEmployee(ID));
    }

    public Rentable mostProfitable() {
        return manager.mostProfitable();
    }
*/








    /*public static Customer registerCustomer(String name) throws Exception{
        Customer customer = new Customer(name);
        return Employee.registerCustomer(customer);
    }

    public static boolean removeCustomer(String ID) throws Exception {
        return Employee.removeCustomer(findCustomer(ID));
    }

    public static String viewAllCustomer() {
        String cusStr = "";
        for (Customer customer : customerList) {
            cusStr = cusStr.concat(customer.toString() + System.lineSeparator());
        }
        return cusStr;
    }

    public static String viewAllUpgRequest() {
        String upgReqStr = "";
        for (Customer customer : customerList) {
            if(upgradeRequests.contains(customer.getID())){
                upgReqStr = upgReqStr.concat(customer.toString() + System.lineSeparator());
            }
        }
        return upgReqStr;
    }

    public static boolean upgradeCustomer(String ID) throws Exception{
        if(Employee.upgradeCustomer(findCustomer(ID))){
            upgradeRequests.remove(findCustomer(ID));
            return true;
        }
        return false;
    }

    public Rentable registerItem(int type, String title, String genreArtist, double rent, int releaseYear) throws Exception {
        Rentable item;
        if(type == 1){
            item = new Game(title, genreArtist, rent, releaseYear);
            itemsList.add(item);
        } else {
            item = new Song(title, genreArtist, rent, releaseYear);
            itemsList.add(item);
        }
        return item;
    }

    public static Customer findCustomer(String ID){
        for (int i = 0; i < Controller.customerList.size(); i++)
            if (Controller.customerList.get(i).getID().equals(ID))
                return Controller.customerList.get(i);
        System.out.println("Customer with id " + ID + " not found.");
        return null;
    }

    public boolean removeItem(int item, String ID) throws Exception{
        return Employee.removeItem(findItem(item, ID), item);
    }

    public Rentable findItem(int item, String ID){
        ArrayList<Rentable> array;
        int i;
        if(item == 1){
            array = itemsList;
        } else {
            array = itemsList;
        }

        for(i = 0; i < array.size(); i++){
            if(array.get(i).getID().equals(ID)){
                return array.get(i);
            }
        }
        return null;
    }

    public static void addCustomer(Customer c){ // <- FOR TESTING
        customerList.add(c);
    }*/

    //--------------------------------------------------------------------------------------
    /*
    Main, UI or "Screens" (Get all input and print all output)
        |
        V
    Controller (Interpret user request and pass on, interpret results and pass to user)
        |
        V
    Business Classes (Actually perform operations, rentGame etc.)

    Changes after feedback from milestone 2:
    1. All methods from CustomerController have been moved here, to Controller, and modified
    to work as controller methods between customerScreen() and mainly the Customer class
    2. Customer class updated to have all methods it is responsible for,
    returnItem(), rentItem() etc.
    3. songList and gameList moved to the Controller
    4. calcRent() method unused, Customer class handles these calculations now
    5. customerScreen() passes the actual customer object around, rather than just the id
    6. totalRentProfit fixed and now actually accumulates total rent profit
    7. CustomerController, SongController, GameController is now empty and unused, all
    purposes they had have been moved to Controller and no longer need to exist
     */
    //---------------------------- METHODS RELATED TO CUSTOMER -----------------------------



    /*public String viewAllSongs(){
        String songStr = "";
        for ( Rentable song : itemsList) {
            songStr = songStr.concat(song.toString() + System.lineSeparator());
        }
        return songStr;
    }

    private String viewAllGames(){
        String gameStr = "";
        for (Rentable game : itemsList) {
            gameStr = gameStr.concat(game.toString() + System.lineSeparator());
        }
        return gameStr;
    }

    public RentHistoryItem returnItem(String returnID, String review, int rating, String returnDate, Customer customer){
        RentHistoryItem returnResults = customer.returnItem(returnID, review, rating, returnDate);
        if (returnResults == null){
            return null;
        } else {
            rentHistory.add(returnResults);
            totalRentProfit = totalRentProfit + returnResults.getRentExpense();
            return returnResults;
        }
    }*/




}
