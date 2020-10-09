import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerController {


    public static ArrayList<Rentable> customerGames = new ArrayList(); //creates ArrayList named 'customerGames' containing Games
    public static ArrayList<Rentable> customerSongs = new ArrayList();

    public static void addGame(Rentable game){ customerGames.add(game); } // a method that adds an object game to the ArrayList 'customerGames'
    public static void removeGame(Rentable game){ customerGames.remove(game); } // a method that removes an object game to the ArrayList 'customerGames'

    public static void addSong(Rentable song){ customerSongs.add(song); }
    public static void removeSong(Rentable song){ customerSongs.remove(song); }



    /*public static void viewAllRentedGames() {
        for (Game customerGame : customerGames) {
            System.out.println(customerGame.toString());
        }
    } // method that prints all the customers games | for each Games in the customerGames list, it will print the game*/

    public static void viewAllRented(ArrayList<Rentable> inventory) {
        for (var customerRented : inventory) {
            System.out.println(customerRented.toString());
        }
    }

    public static long calcDays(int i){ // a method that returns days between game was rented and returned
        return ChronoUnit.DAYS.between(GameController.gameList.get(i).getRentDate(), GameController.gameList.get(i).getReturnDate());
    }

    public static double calcRent(int i){
        final double[] memDiscount = new double[]{1, 0.90, 0.85, 0.75};
        double discount;
        switch(EmployeeController.customerList.get(i).getMembership()) {
            case "silver" -> discount = memDiscount[1];
            case "gold" -> discount = memDiscount[2];
            case "platinum" -> discount = memDiscount[3];
            default -> discount = memDiscount[0];
        }
        return GameController.gameList.get(i).getDailyRent() * calcDays(i) * discount;
    }

    // Chronounit: https://stackoverflow.com/questions/27005861/calculate-days-between-two-dates-in-java-8

    /*public static void rentGame(String ID){ // method to rent a game, by adding a game to a customers list
        System.out.println("Current game library: ");
        GameController.customerViewAllGames(); // executes viewAllGames from class Games

        String rentID = Tools.getString("What game do you want to rent? "); //Declares a String variable, which is used to remove customers
        for( int i = 0; i < GameController.gameList.size(); i++){
            if(GameController.gameList.get(i).getID().equals(rentID)){
                if(GameController.gameList.get(i).getStatus()) { // if the game is available
                    addGame(GameController.gameList.get(i));
                    GameController.gameList.get(i).setStatus(false); // makes the game no longer available from the stores game library
                    try {
                        GameController.gameList.get(i).setRentDate(Tools.getString("What is the rent date? (YYYY-MM-DD)"));
                    } catch (Exception e) {
                        System.out.println("Wrong format, assuming rent date is today.");
                        GameController.gameList.get(i).setAutomaticRentDate();
                    }
                    for(int j = 0; j < EmployeeController.customerList.size(); j++){
                        if(EmployeeController.customerList.get(j).getID().equals(ID)) {
                            EmployeeController.customerList.get(j).addToLibrary(GameController.gameList.get(i));
                            System.out.println("Successfully rented");
                            j = EmployeeController.customerList.size();
                        }
                    }
                    i = GameController.gameList.size();
                } else {
                    System.out.println("Game is not available");
                }
            }
        }
        Screens.customerScreen(ID);
    }

    public static double returnGame(String ID){ // a method to return games to the store
        double rent; //initializes a variable local to the method
        System.out.println("Current game library: ");
        viewAllRented(customerGames); // prints all the games rented by the customer
        String rentID = Tools.getString("What game do you want to return? ");
        for( int i = 0; i < GameController.gameList.size(); i++){
            if(GameController.gameList.get(i).getID().equals(rentID)){
                if(!GameController.gameList.get(i).getStatus()) {
                    removeGame(GameController.gameList.get(i));
                    GameController.gameList.get(i).setStatus(true);
                    try {
                        GameController.gameList.get(i).setReturnDate(Tools.getString("What is the return date? (YYYY-MM-DD)"));
                    } catch (Exception e) {
                        System.out.println("Wrong format, assuming return date is today.");
                        GameController.gameList.get(i).setAutomaticReturnDate();
                    }
                    for(int j = 0; j < EmployeeController.customerList.size(); j++){
                        if(EmployeeController.customerList.get(j).getID().equals(ID)) {
                            EmployeeController.customerList.get(j).removeFromLibrary(GameController.gameList.get(i));
                            System.out.println("Successfully returned");
                            j = EmployeeController.customerList.size();
                        }
                    }
                    rent = calcRent(i); // sets the variable 'rent' to the daily rent multiplied by the amount of days
                    System.out.println("The cost for renting the game for " + calcDays(i) + " days is: " + rent);
                    Screens.customerScreen(ID);
                    return rent; // returns the rent cost
                } else System.out.println("Game is not available");
            }
        }
        Screens.customerScreen(ID);
        return 0;
    }

    public static void rentSong(String ID){ // method to rent a game, by adding a game to a customers list
        System.out.println("Current song library: ");
        SongController.customerViewAllSongs(); // executes viewAllGames from class Games

        String rentID = Tools.getString("What song do you want to rent? "); //Declares a String variable, which is used to remove customers
        for( int i = 0; i < SongController.songList.size(); i++){
            if(SongController.songList.get(i).getID().equals(rentID)){
                if(SongController.songList.get(i).getStatus()) { // if the game is available
                    addSong(SongController.songList.get(i));
                    SongController.songList.get(i).setStatus(false); // makes the game no longer available from the stores game library



                    try {
                        SongController.songList.get(i).setRentDate(Tools.getString("What is the rent date? (YYYY-MM-DD)"));
                    } catch (Exception e) {
                        System.out.println("Wrong format, assuming rent date is today.");
                        SongController.songList.get(i).setAutomaticRentDate();
                    }

                    for(int j = 0; j < EmployeeController.customerList.size(); j++){
                        if(EmployeeController.customerList.get(j).getID().equals(ID)) {
                            EmployeeController.customerList.get(j).addToLibrary(SongController.songList.get(i));
                            System.out.println("Successfully rented");
                            j = EmployeeController.customerList.size();
                        }
                    }
                    i = SongController.songList.size();
                } else {
                    System.out.println("Song is not available");
                }
            }
        }
        Screens.customerScreen(ID);
    }

    public static double returnSong(String ID){ // a method to return games to the store
        double rent; //initializes a variable local to the method
        System.out.println("Current song library: ");
        viewAllRented(customerSongs); // prints all the games rented by the customer
        String rentID = Tools.getString("What song do you want to return? ");
        for( int i = 0; i < SongController.songList.size(); i++){
            if(SongController.songList.get(i).getID().equals(rentID)){
                if(!SongController.songList.get(i).getStatus()) {
                    removeSong(SongController.songList.get(i));
                    SongController.songList.get(i).setStatus(true);
                    try {
                        SongController.songList.get(i).setReturnDate(Tools.getString("What is the return date? (YYYY-MM-DD)"));
                    } catch (Exception e) {
                        System.out.println("Wrong format, assuming return date is today.");
                        SongController.songList.get(i).setAutomaticReturnDate();
                    }
                    for(int j = 0; j < EmployeeController.customerList.size(); j++){
                        if(EmployeeController.customerList.get(j).getID().equals(ID)) {
                            EmployeeController.customerList.get(j).removeFromLibrary(SongController.songList.get(i));
                            System.out.println("Successfully rented");
                            j = EmployeeController.customerList.size();
                        }
                    }
                    rent = SongController.songList.get(i).getDailyRent() * calcDays(i); // sets the variable 'rent' to the daily rent multiplied by the amount of days
                    System.out.println("The cost for renting the song for " + calcDays(i) + " days is: " + rent);
                    Screens.customerScreen(ID);
                    return rent; // returns the rent cost
                } else System.out.println("Song is not available");
            }
        }
        Screens.customerScreen(ID);
        return 0;
    }*/

    public static void requestUpgrade(String ID){
        String upgID = Tools.getString("Enter your id: "); //Declares a String variable, which is used to remove customers
        EmployeeController.upgradeRequestsID.add(upgID);
        Screens.customerScreen(ID);
    }



public static void rentItem(String item, String ID){ // method to rent a game, by adding a game to a customers list
        ArrayList<Rentable> array;
        System.out.println("Current library: ");
        if (item.equals("Game")) {
            GameController.customerViewAllGames();
            array = GameController.gameList;
        } else {
            SongController.customerViewAllSongs();
            array = SongController.songList;
        }

        String rentID = Tools.getString("What do you want to rent?"); //Declares a String variable, which is used to remove customers
        for( int i = 0; i < array.size(); i++){
            if(array.get(i).getID().equals(rentID)){
                if(array.get(i).getStatus()) { // if the game is available
                    if(item.equals("Game")) {addGame(array.get(i));}
                    if(item.equals("Song")) {addSong(array.get(i));}
                    array.get(i).setStatus(false); // makes the game no longer available from the stores game library
                    try {
                        array.get(i).setRentDate(Tools.getString("What is the rent date? (YYYY-MM-DD)"));
                    } catch (Exception e) {
                        System.out.println("Wrong format, assuming rent date is today.");
                        array.get(i).setAutomaticRentDate();
                    }

                    System.out.println("Successfully rented");
                    i = array.size();
                } else {
                    System.out.println("Game is not available");
                }
            }
        }
        Screens.customerScreen(ID);
    }

    public static double returnItem(String item, String ID){ // a method to return games to the store
        ArrayList<Rentable> array;
        System.out.println("Current library: ");
        if (item.equals("Game")) {
            viewAllRented(customerGames);
            array = GameController.gameList;
        } else {
            viewAllRented(customerSongs);
            array = SongController.songList;
        }

        double rent; //initializes a variable local to the method
        String rentID = Tools.getString("What song do you want to return? ");
        for( int i = 0; i < array.size(); i++){
            if(array.get(i).getID().equals(rentID)){
                if(!array.get(i).getStatus()) {
                    removeSong(array.get(i));
                    array.get(i).setStatus(true);
                    try {
                        array.get(i).setReturnDate(Tools.getString("What is the return date? (YYYY-MM-DD)"));
                    } catch (Exception e) {
                        System.out.println("Wrong format, assuming return date is today.");
                        array.get(i).setAutomaticReturnDate();
                    }
                    for(int j = 0; j < EmployeeController.customerList.size(); j++){
                        if(EmployeeController.customerList.get(j).getID().equals(ID)) {
                            EmployeeController.customerList.get(j).removeFromLibrary(array.get(i));
                            System.out.println("Successfully rented");
                            j = EmployeeController.customerList.size();
                        }
                    }
                    rent = array.get(i).getDailyRent() * calcDays(i); // sets the variable 'rent' to the daily rent multiplied by the amount of days
                    System.out.println("The cost for renting the song for " + calcDays(i) + " days is: " + rent);
                    Screens.customerScreen(ID);
                    return rent; // returns the rent cost
                } else System.out.println("Song is not available");
            }
        }
        Screens.customerScreen(ID);
        return 0;
    }

}
