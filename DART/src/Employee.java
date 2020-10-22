
import java.time.Year;

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

    public static Customer registerCustomer(Customer customer){
        Controller.customerList.add(customer);
        return customer;
    }

    public static boolean removeCustomer(Customer customer){ // method that removes customers
        return Controller.customerList.remove(customer);
    }

    public static boolean upgradeCustomer(Customer customer){ // method that removes customers
        return customer.upgradeMembership();
    }

    public static void autoRegisterGame(String title, String genre, double price, int year) throws Exception { // Method for adding games, used for testing purposes
        Game g = new Game(title, genre, price, year);
        System.out.print("You have added game: ");
        System.out.println(g.toString());
        Controller.gameList.add(g);

    }

    public static void autoRegisterSong(String title, String artist, double price, int year) throws Exception { // Method for adding games, used for testing purposes
        Song s = new Song(title, artist, price, year);
        System.out.print("You have added game: ");
        System.out.println(s.toString());
        Controller.songList.add(s);

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
        //Screens.employeeScreen();
    }

    public static boolean removeItem(Rentable item, int type){
        if(type == 1){
            return Controller.gameList.remove(item);
        } else
            return Controller.songList.remove(item);
    }
}