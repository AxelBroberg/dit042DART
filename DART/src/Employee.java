
import java.time.Year;
import java.util.ArrayList;

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
    private final String name;

    public Employee(String name, String address, int birthyear, double grossSalary){
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

    public String getName() { return name; }

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

    /*public static Customer registerCustomer(Customer customer){
        Controller.customerList.add(customer);
        return customer;
    }

    public static boolean removeCustomer(Customer customer){ // method that removes customers
        return Controller.customerList.remove(customer);
    }*/

    public void autoRegisterGame(ArrayList<Rentable> itemsList, String title, String genre, double price, int year) throws Exception { // Method for adding games, used for testing purposes
        Game g = new Game(title, genre, price, year);
        System.out.print("You have added game: ");
        System.out.println(g.toString());
        itemsList.add(g);

    }

    public void autoRegisterSong(ArrayList<Rentable> itemsList, String title, String artist, double price, int year) throws Exception { // Method for adding games, used for testing purposes
        Song s = new Song(title, artist, price, year);
        System.out.print("You have added game: ");
        System.out.println(s.toString());
        itemsList.add(s);

    }

    public void fillGames(ArrayList<Rentable> itemsList) throws Exception { // Method for adding games, used for testing purposes
        String[] gameName = {"The Last of us Part 2", "The Witcher 3 Wild Hunt", "Red Dead Redemption 2"};
        String[] genre = {"action", "comedy", "family"};
        double[] price = {12, 13, 14};

        String[] songName = {"Song A", "Song B", "Song C"};
        String[] artist = {"Artist A", "Artist B", "Artist C"};
        double[] priceS = {12, 13, 14};
        int[] year = {1970, 2030, 2000};


        for(int i = 0; i < gameName.length; i++){
            autoRegisterGame(itemsList,
                    gameName[i],
                    genre[i],
                    price[i],
                    year[i]);
            autoRegisterSong(itemsList,
                    songName[i],
                    artist[i],
                    priceS[i],
                    year[i]);
        }
        //Screens.employeeScreen();
    }

    public boolean removeItem(ArrayList<Rentable> itemsList, Rentable item){
        return itemsList.remove(item);
    }

    public Customer registerCustomer(ArrayList<Customer> customerArrayList, String name){

        Customer customer = null;
        do{
            try{
                customer = new Customer(name);
            }catch (NameEmptyException e){
                name = Tools.getString("Enter the customer's name: ");
            }
        }while(customer == null);

        customerArrayList.add(customer);
        return customer;
    }

    public Rentable findItem(ArrayList<Rentable> itemsList, String ID){
        ArrayList<Rentable> array;
        int i;
        array = itemsList;

        for(i = 0; i < array.size(); i++){
            if(array.get(i).getID().equals(ID)){
                return array.get(i);
            }
        }
        return null;
    }

    public boolean removeCustomer(ArrayList<Customer> customerArrayList, Customer customer){ // method that removes customers
        return customerArrayList.remove(customer);
    }

    public boolean upgradeCustomer(Customer customer){ // method that removes customers
        return customer.upgradeMembership();
    }

    public Customer getCustomer(ArrayList<Customer> customerList, String ID){
        for (Customer customer : customerList) {
            if (customer.getID().equals(ID)) {
                return customer;
            }
        }
        return null;
    }

    public String showItems(ArrayList<Rentable> itemsList){
        String itemStr = "";
        for (Rentable game : itemsList) {
            itemStr = itemStr.concat(game.toString() + System.lineSeparator());
        }
        for (Rentable song  : itemsList) {
            itemStr = itemStr.concat(song.toString() + System.lineSeparator());
        }
        return itemStr;
    }

    public String viewAllCustomer(ArrayList<Customer> customerList) {
        String cusStr = "";
        for (Customer customer : customerList) {
            cusStr = cusStr.concat(customer.toString() + System.lineSeparator());
        }
        return cusStr;
    }

    public String viewAllUpgRequest(ArrayList<Customer> upgradeRequests) {
        String upgReqStr = "";
        for (Customer customer : upgradeRequests) {
            if(upgradeRequests.contains(customer.getID())){
                upgReqStr = upgReqStr.concat(customer.toString() + System.lineSeparator());
            }
        }
        return upgReqStr;
    }

   /* public static void fillGames() throws Exception { // Method for adding games, used for testing purposes
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
        //Screens.employeeScreen();
    }*/

    public Rentable registerItem(ArrayList<Rentable> itemsList, int type, String title, String genreArtist, double rent, int releaseYear) throws Exception {

        Rentable item;
        if(type == 1){
            item = new Game(title, genreArtist, rent, releaseYear);
        } else {
            item = new Song(title, genreArtist, rent, releaseYear);
        }
        itemsList.add(item);
        return item;
    }
}