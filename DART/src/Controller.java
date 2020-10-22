import java.util.ArrayList;
import java.util.Collections;

public class Controller {
    private ArrayList<Employee> employeeArrayList;
    private ArrayList<RentHistoryItem> rentHistory;
    static ArrayList<Customer> customerList = new ArrayList<>();//creates ArrayList named 'customerList' containing Customers
    static ArrayList<Customer> upgradeRequests = new ArrayList<Customer>();
    private ArrayList<Rentable> itemsList;// adapt!
    static ArrayList<Rentable> songList = new ArrayList();
    static ArrayList<Rentable> gameList = new ArrayList<>();
    private static double totalRentProfit = 0;
    private static Manager manager = new Manager();

    public Controller(){
        this.employeeArrayList = new ArrayList<>();
        this.rentHistory = new ArrayList<>();
        this.itemsList = new ArrayList<>();

    }


    public void managerLoggedIn(){
        Manager manager = new Manager();
        char choice;
        String screens = "123456789";
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
            System.out.println("9. Return to Main Menu");
            choice = Tools.getChar("");
            Tools.validateChar(choice, screens);
            switch (choice) {
                case '1' -> {

                    String name = Tools.getString("Enter employee name: ");
                    String address = Tools.getString("Enter employee address: ");
                    int bYear = Tools.getInt("Enter employee birth year: ");
                    double gross = Tools.getDouble("Enter employee gross salary: ");

                    manager.registerEmployee(employeeArrayList, name, address, bYear, gross);
                }

                case '2' -> System.out.println(manager.viewAllEmployee(employeeArrayList));
                case '3' -> {
                    String empId = Tools.getString("Enter the ID of employee you want to remove: ");
                    Employee toRemove = manager.findEmployee(employeeArrayList, empId);
                if(manager.removeEmployee(employeeArrayList, toRemove))
                    System.out.println("Successfully removed!");
                else
                    System.out.println("Error removing employee.");
                }
                case '4' -> {
                    String empId = Tools.getString("Enter ID of employee to calculate net salary: ");
                    Employee toFind = manager.findEmployee(employeeArrayList, empId);
                    System.out.println("Employee net salary is: " + manager.calcNetSalary(toFind));
                }
                case '5' -> {
                }//System.out.println("Employee bonus is: " + Controller.bonus(Tools.getString("Enter ID of employee to see what bonus employee is eligible to: ")));
                case '6' -> {
                }//System.out.println(Controller.viewRentFrequency());
                case '7' -> {
                    // if(Controller.mostProfitable() != null) System.out.println("Most profitable item: " + Controller.mostProfitable());
                }
                case '8' ->{} //System.out.println("Most valued customer: " + controller.mostProfitableCustomer());
                case '9' -> {
                }
            }
        }while(choice != 9);
    }






        //TODO
    /*
    * move the methods from controller to the right business class
    * remove all statics
    * inside the controller, don't forget to decalre the lists in the class then initialize them in the controller's constructor
    * for the sub-menu, don't forget the do-while loops that break when choice!=()
    * call methods though object!!
    * private attributes EVERYWHERE, why? encapsulation!
    * membership via inheritance
    * adapt to the itemsList
    * close scanner
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
    public String viewRentFrequency() {
        String rentFreq = "";
        for (Rentable game : gameList) {
            if(game.getRentFrequency() > 0 ){
                rentFreq = rentFreq.concat(game.getTitle() + " : " + game.getRentFrequency()) + System.lineSeparator();
            }
        }
        for (Rentable song : songList) {
            if(song.getRentFrequency() > 0 ){
                rentFreq = rentFreq.concat(song.getTitle() + " : " + song.getRentFrequency());
            }
        }
        return rentFreq;
    }

    public Customer mostProfitableCustomer() {
        return manager.mostProfitableCustomer();
    }



    public String showItems(){
        String itemStr = "";
        for (Rentable game : gameList) {
            itemStr = itemStr.concat(game.toString() + System.lineSeparator());
        }
        for (Rentable song  : songList) {
            itemStr = itemStr.concat(song.toString() + System.lineSeparator());
        }
        return itemStr;
    }

    public static Customer registerCustomer(String name) throws Exception{
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

    public static Rentable registerItem(int type, String title, String genreArtist, double rent, int releaseYear) throws Exception {
        Rentable item;
        if(type == 1){
            item = new Game(title, genreArtist, rent, releaseYear);
            gameList.add(item);
        } else {
            item = new Song(title, genreArtist, rent, releaseYear);
            songList.add(item);
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

    public static boolean removeItem(int item, String ID) throws Exception{
        return Employee.removeItem(findItem(item, ID), item);
    }

    public static Rentable findItem(int item, String ID){
        ArrayList<Rentable> array;
        int i;
        if(item == 1){
            array = gameList;
        } else {
            array = songList;
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
    }

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

    public static Customer getCustomer(String ID){
        for (Customer customer : customerList) {
            if (customer.getID().equals(ID)) {
                return customer;
            }
        }
        return null;
    }

    public static String viewAllSongs(){
        String songStr = "";
        for ( Rentable song : songList) {
            songStr = songStr.concat(song.toString() + System.lineSeparator());
        }
        return songStr;
    }

    public static String viewSongByYear(int year){
        String songStr = "";
        for (Rentable song : songList) {
            if(song.getYear() == year) {
                songStr = songStr.concat(song.toString() + System.lineSeparator());
            }
        }
        return songStr;
    }

    private static String viewAllGames(){
        String gameStr = "";
        for (Rentable game : gameList) {
            gameStr = gameStr.concat(game.toString() + System.lineSeparator());
        }
        return gameStr;
    }

    public static String viewGamesByGenre(String genre){
        String gameStr = "";
        for (Rentable game : gameList) {
            if(game.getGenre().equals(genre)) {
                gameStr = gameStr.concat(game.toString() + System.lineSeparator());
            }
        }
        return gameStr;
    }
    //CHANGED TO TAKE NO INPUTS AND PRINT NO OUTPUTS.
    //TAKES NECESSARY INFO AS PARAMETERS FROM SCREENS-CLASS(MAIN) AND RETURNS RESULTS
    public static String showItems(int itemType, int selectionSorting, String optionalGenreOrYear){
        String itemStr = "";
        if (itemType == 1) {
            ArrayList<Rentable> array = gameList;
            if(selectionSorting == 1) {
                return viewAllGames();
            } else if (selectionSorting == 2) {
                return viewGamesByGenre(optionalGenreOrYear);
            } else if (selectionSorting == 3){
                array.sort(new RatingsComparator());
                Collections.reverse(array);
                for (Rentable game: array) {
                    itemStr = itemStr.concat(game.toString() + System.lineSeparator());
                }
                return itemStr;
            } else if (selectionSorting == 4){
                array.sort(new YearComparator());
                Collections.reverse(array);
                for (Rentable game: array) {
                    itemStr = itemStr.concat(game.toString() + System.lineSeparator());
                }
                return itemStr;
            }

        } else {
            ArrayList<Rentable> array = songList;
            if(selectionSorting == 1) {
                return viewAllSongs();
            } else if (selectionSorting == 2){
                try {
                    int year = Integer.parseInt(optionalGenreOrYear);
                    return viewSongByYear(year);
                } catch (Exception exception){
                    return ("Year entered in wrong format (should be YYYY), aborting rent process");
                }
            } else if (selectionSorting == 3){
                array.sort(new RatingsComparator());
                Collections.reverse(array);
                for ( Rentable song: array) {
                    itemStr = itemStr.concat(song.toString() + System.lineSeparator());
                }
                return itemStr;
            } else if (selectionSorting == 4){
                array.sort(new YearComparator()); // was Collections.sort(array, new YearComparator());
                Collections.reverse(array);
                for ( Rentable song: array) {
                    itemStr = itemStr.concat(song.toString() + System.lineSeparator());
                }
                return itemStr;
            }
        }
        return "Could not find any items";
    }

    public static boolean rentItem(int itemType, String itemID, String rentDate, Customer customer) {
        ArrayList<Rentable> array = gameList;
        if(itemType == 1){
            array = gameList;
        } else if (itemType == 2){
            array = songList;
        }
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getID().equals(itemID)) {
                return customer.rentItem(array.get(i), rentDate);
            }
        }
        return false;
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
    }

    public static boolean sendMessage(String message, String recipientID, Customer sender){
        Customer recipient = getCustomer(recipientID);
        if (recipient == null){
            return false;
        } else {
            recipient.addMessage(new Message(message, sender.getID()));
            return true;
        }
    }

    public static String viewInbox(Customer customer){
        return customer.viewInbox();
    }

    public static String viewUnread(Customer customer){
        return customer.viewUnread();
    }

    public static void removeMessage(int index, Customer customer){
        if(index <= customer.getInboxSize()) {
            customer.removeMessage(index);
        }
    }


    public static void requestUpgrade(Customer customer){
        upgradeRequests.add(customer);
    }

}
