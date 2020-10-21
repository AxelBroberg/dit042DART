
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

// Changes because of the feedback we got on the last milestone:
// Moved the ArrayList employeeArrayList to the EmployeeController
// Moved the methods autoRegisterGame, fillGames, registerCustomer, removeCustomer, viewAllCustomer to the EmployeeController
//
// We also added a toString, which we didnt have in the last milestone
//
// We did some modifications to the Constructor so that it doesn't only take a name as a parameter


public class Employee {

    private String ID;
    private int birthyear;
    private String address;
    private double grossSalary;
    private double netSalary;
    private String name;

    public Employee(String name, String address, int birthyear, double grossSalary) throws Exception{
        if (name.isEmpty() || name.equals(" ") ){
            throw new NameEmptyException("Empty name is not allowed.");
        } else {
            this.name = name;
        }
        if (grossSalary < 0) {
            throw new NegativeSalaryException("Salary cannot be less than zero.");
        } else {
            this.grossSalary = grossSalary;
        }
        this.address = address;
        this.birthyear = birthyear;
        this.ID = Tools.randomizeID();
    }

    public void setBirthyear(int birthyear){ this.birthyear = birthyear; }
    public int getBirthyear(){ return birthyear; }

    public void setAddress(String address){ this.address = address; }
    public String getAddress(){ return address; }

    public void setGrossSalary(double grossSalary){ this.grossSalary = grossSalary; }
    public double getGrossSalary(){ return grossSalary; }

    public void setNetSalary(double netSalary){ this.netSalary = netSalary; }
    public double getNetSalary(){ return netSalary; }

    public void setID(){ ID = Tools.randomizeID(); }
    public String getID(){ return ID; }

    public String toString(){
        String s = getID() + " " + this.name + " - " + getBirthyear() + " (" + (Year.now().getValue() - getBirthyear()) + " ): " + "Gross salary: " + getGrossSalary() + " SEK";
        if(getNetSalary()!=0) s = s + " Net salary: " + getNetSalary() + " SEK";
        s = s + System.lineSeparator();
        return s;
    }

    public static void registerCustomer(Customer customer) throws Exception {
        Controller.customerList.add(customer);
    }

    public static void viewAllCustomer(boolean showScreen) throws Exception {
        for (Customer customer : customerList) {
            System.out.println(customer.toString());
        }
        if(showScreen) {
            Screens.employeeScreen();
        }
    } // for each Customer in the 'customerList', it will execute printCustomer

    public static void viewAllUpgRequest() throws Exception {
        for (Customer customer : customerList) {
            if(upgradeRequestsID.contains(customer.getID())){
                System.out.println(customer.toString());
            }
        }
        Screens.employeeScreen();
    }


    public static void upgradeCustomer() throws Exception { // method that removes customers
        boolean upgraded = false;
        int i;
        String upgID = Tools.getString("Enter the ID of the customer you want to upgrade: ");
        for ( i = 0; i < customerList.size(); i++ ) {
            if (customerList.get(i).getID().equals(upgID)) {
                customerList.get(i).upgradeMembership();
                System.out.println("Successfully upgraded!");
                upgraded = true;
                //Removed the id from the request list
                for ( int j = 0; j < upgradeRequestsID.size(); j++ ) {
                    if (upgradeRequestsID.get(j).equals(upgID)) {
                        upgradeRequestsID.remove(j);
                        j = upgradeRequestsID.size();
                    }

                    i = customerList.size(); // sets variable 'i' to the same size as customerList, which will make the for loop not loop again
                }
            }
            if (!upgraded) System.out.println("Customer with id " + upgID + " not found.");
            Screens.employeeScreen();
        }
    }


    public static void removeCustomer() throws Exception { // method that removes customers
        Scanner input = new Scanner(System.in);
        boolean removed = false; // declares a variable that will decide if the following for loop will continue looping
        int i; // initializes a variable 'i' so it can be used outside of the for loop
        System.out.println("Games: ");
        // prints all of the customers, so it will be easier for the user to see which customer to remove
        for (Customer employee : customerList) {
            System.out.println(employee.toString());
        }
        System.out.print("Enter the ID of the customer you want to remove: ");

        String removeID = input.nextLine(); // declares a String variable, which is used to remove customers
        for(i = 0; i < customerList.size(); i++){ // for loop that loops the same amount of times as the amount of objects in the customerList
            if(customerList.get(i).getID().equals(removeID)){ // if the customer 'ID' in the customerList is the same as 'removeID' inputted by the user earlier
                customerList.remove(i); // removes user from the customerList
                System.out.println("Successfully removed!");
                removed = true;
                i = customerList.size(); // sets variable 'i' to the same size as customerList, which will make the for loop not loop again
            }
        }
        if(!removed) System.out.println("Customer with id " + i + " not found.");
        Screens.employeeScreen();
    }
    /*public static void registerGame(){

        Game g = new Game(Tools.getString("Enter game title: "), Tools.getString("Enter game genre: "), Tools.getDouble("Enter daily rent: ")); // OBJECT CREATION


        System.out.print("You have added game: ");
        System.out.println(g.toString());
        GameController.gameList.add(g);
        Screens.employeeScreen();
    }*/

    public static void registerItem(String item) throws Exception {
        Rentable x;
        try {
            if (item.equals("game")) {
                x = new Game(
                        Tools.getString("Enter game title: "),
                        Tools.getString("Enter game genre: "),
                        Tools.getDouble("Enter daily rent: "),
                        Tools.getInt("Enter release year: ")); // OBJECT CREATION
                GameController.gameList.add(x);
                System.out.print("You have added game: ");
                System.out.println(x.toString());
            } else {
                x = new Song(
                        Tools.getString("Enter title: "),
                        Tools.getString("Enter artist: "),
                        Tools.getDouble("Enter daily rent: "),
                        Tools.getInt("Enter release year: ")); // OBJECT CREATION
                SongController.songList.add(x);
                System.out.print("You have added song: ");
                System.out.println(x.toString());
            }
        } catch (Exception exception){
            System.out.println(exception);
            registerItem(item);
        }

        Screens.employeeScreen();
    }
    public static void autoRegisterGame(String title, String genre, double price, int year) throws Exception { // Method for adding games, used for testing purposes
        Game g = new Game(title, genre, price, year);
        System.out.print("You have added game: ");
        System.out.println(g.toString());
        GameController.gameList.add(g);

    }

    public static void autoRegisterSong(String title, String artist, double price, int year) throws Exception { // Method for adding games, used for testing purposes
        Song s = new Song(title, artist, price, year);
        System.out.print("You have added game: ");
        System.out.println(s.toString());
        SongController.songList.add(s);

    }

    public static void fillGames() throws Exception { // Method for adding games, used for testing purposes
        String[] gameName = {"The Last of us Part 2", "The Witcher 3 Wild Hunt", "Red Dead Redemption 2"};
        String[] genre = {"action", "comedy", "family"};
        double[] price = {12, 13, 14};

        String[] songName = {"Song A", "Song B", "Song C"};
        String[] artist = {"Artist A", "Artist B", "Artist C"};
        double[] priceS = {12, 13, 14};
        int[] year = {1970, 2030, 2000};


        for(int i = 0; i < gameName.length; i++){
            autoRegisterGame(gameName[i], genre[i], price[i], year[i]);
            autoRegisterSong(songName[i], artist[i], priceS[i], year[i]);
        }
        Screens.employeeScreen();
    }

    public static void removeItem(String item) throws Exception {
        boolean removed = false;
        int i;
        Object itemO;
        ArrayList<Rentable> array = new ArrayList<>();
        if(item.equals("game")){
            array = GameController.gameList;
            System.out.println("Games: ");
            for( Rentable game : array ) {
                System.out.println(game.toString());
            }
        } else {
            array = SongController.songList;
            System.out.println("Songs: ");
            for( Rentable song : array ) {
                System.out.println(song.toString());
            }
        }
        String removeID = Tools.getString("Enter the ID of the item you want to remove: ");
        for(i = 0; i < array.size(); i++){
            if(array.get(i).getID().equals(removeID)){
                array.remove(i);
                System.out.println("Successfully removed!");
                removed = true;
                i = array.size();
            }
        }
        if(!removed){
            System.out.println("Item with id " + i + " not found.");
        }
        Screens.employeeScreen();
    }

    public static int findCustomer(String ID){
        int j = 0;
        for(j = 0; j < Employee.customerList.size(); j++){
            if(Employee.customerList.get(j).getID().equals(ID)) {
                break;
            }
        }
        return j;
    }

    public static boolean customerExists(String ID){
        for(int j = 0; j < Employee.customerList.size(); j++) {
            if (Employee.customerList.get(j).getID().equals(ID)) {
                return true;
            }
        }
        return false;
    }
}