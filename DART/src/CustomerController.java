import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// We created this class because we got feedback on the last milestone that we are supposed to have a controller.
// We decided that we would have one controller per object class
// (customer, employee, game) instead of having one controller for the entire program
//
// In here we have the addGame & removeGame originating from the Customer class
// We also have the viewAllRented method, which is a method like the printGame existing in milestone 1,
// but it works for all rentables (games & songs)
//
// In order to implement epic feature 7 we modified the class CustomerController by adding the methods:
// - rentItem, which is a modification of the rentGame, created in the last milestone
// - returnItem, which is a modification of the returnGame, created in the last milestone
// - addSong & removeSong, they are just like the addGame & removeGame methods
//
// In order to implement epic feature 8 we modified the class CustomerController by adding the methods:
// - calcRent, which uses the calcDays methods used in the last milestone to give the customer a discount based on their membership
// - requestUpgrade, which lets the customer request an upgrade to a better membership
//
// In order to implement epic feature 9 we modified the class CustomerController by adding the methods:
// - sendMessage, which will send a message to another customer
// - removeMessage, which will give the user the ability to remove messages
//
// In order to implement epic feature 10 we modified the class CustomerController by adding the methods:
// - searchGames, currently not working, but its intended use is so that the user can search for items based on an input i.e. the action genre
// - we modified the method returnItem so that the customer is prompted with an option to give a rating & a review

public class CustomerController {


    public static ArrayList<Rentable> customerGames = new ArrayList<>(); //creates ArrayList named 'customerGames' containing Games
    public static ArrayList<Rentable> customerSongs = new ArrayList<>();

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
            case "platinum" -> discount = memDiscount[3];
            case "gold" -> discount = memDiscount[2];
            case "silver" -> discount = memDiscount[1];
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


    // Milestone 2: Changed gameList and songList to an ArrayList of rentable
    // and with a few changes to this method allowed us to reuse this method for both  games and song albums

    public static void rentItem(String item, String ID){ // method to rent a game, by adding a game to a customers list
        if(!EmployeeController.customerExists(ID)) {
            System.out.println("Customer does not exist!");
            Screens.customerScreen(ID);
        }
        int customerIndex = EmployeeController.findCustomer(ID);
        int selectionSorting;
        if(!EmployeeController.customerList.get(customerIndex).libraryFull()) {
            ArrayList<Rentable> array;
            System.out.println("Current library: ");
            if (item.equals("Game")) {
                array = GameController.gameList;
                selectionSorting = Tools.getInt("1. Show all games" + System.lineSeparator() + "2. Sort by genre" + System.lineSeparator() + "3. Sort by ratings" + System.lineSeparator() + "4. Sort by year");
                if(selectionSorting == 1)
                {
                    GameController.customerViewAllGames();
                } else if (selectionSorting == 2) {
                    GameController.viewGamesByGenre(Tools.getString("Enter genre: "));
                } else if (selectionSorting == 3){
                    Collections.sort(array, new RatingsComparator());
                    Collections.reverse(array);
                    for ( Rentable game: array) {
                        System.out.println(game.toString());
                    }
                } else if (selectionSorting == 4){
                    Collections.sort(array, new YearComparator());
                    Collections.reverse(array);
                    for ( Rentable game: array) {
                        System.out.println(game.toString());
                    }
                }

            } else {
                array = SongController.songList;
                selectionSorting = Tools.getInt("1. Show all songs" + System.lineSeparator() + "2. Search by year" + System.lineSeparator() + "3. Sort by ratings" + System.lineSeparator() + "4. Sort by year");
                if(selectionSorting == 1)
                {
                    SongController.customerViewAllSongs();
                } else if (selectionSorting == 2){
                    SongController.viewSongByYear(Tools.getInt("Enter year: (YYYY)"));
                } else if (selectionSorting == 3){
                    Collections.sort(array, new RatingsComparator());
                    Collections.reverse(array);
                    for ( Rentable song: array) {
                        System.out.println(song.toString());
                    }
                } else if (selectionSorting == 4){
                    Collections.sort(array, new YearComparator());
                    Collections.reverse(array);
                    for ( Rentable song: array) {
                        System.out.println(song.toString());
                    }
                }

            }

            String rentID = Tools.getString("What do you want to rent?"); //Declares a String variable, which is used to remove customers
            for (int i = 0; i < array.size(); i++) {
                if (array.get(i).getID().equals(rentID)) {
                    if (array.get(i).getStatus()) { // if the game is available
                        if (item.equals("Game")) {
                            addGame(array.get(i));
                        }
                        if (item.equals("Song")) {
                            addSong(array.get(i));
                        }
                        array.get(i).setStatus(false); // makes the game no longer available from the stores game library
                        try {
                            array.get(i).setRentDate(Tools.getString("What is the rent date? (YYYY-MM-DD)"));
                        } catch (Exception e) {
                            System.out.println("Wrong format, assuming rent date is today.");
                            array.get(i).setAutomaticRentDate();
                        }
                        for (int j = 0; j < EmployeeController.customerList.size(); j++) {
                            if (EmployeeController.customerList.get(j).getID().equals(ID)) {
                                EmployeeController.customerList.get(j).addToLibrary(array.get(i));
                                System.out.println("Successfully rented");
                                j = EmployeeController.customerList.size();
                            }
                        }
                        i = array.size();
                    } else {
                        System.out.println("Game is not available");
                    }
                }
            }
        } else {
            System.out.println("Your library is full");
        }
        Screens.customerScreen(ID);
    }

    public static double returnItem(String item, String ID){ // a method to return games to the store
        if(!EmployeeController.customerExists(ID)) {
            System.out.println("Customer does not exist!");
            Screens.customerScreen(ID);
            return 0;
        }
        int customerIndex = EmployeeController.findCustomer(ID);
        final int CREDIT_COST = 5;
        ArrayList<Rentable> array;
        double rent = 0;
        System.out.println("Current library: ");
        if (item.equals("Game")) {
            viewAllRented(customerGames);
            array = GameController.gameList;

        } else {
            viewAllRented(customerSongs);
            array = SongController.songList;
        }

        String rentID = Tools.getString("What " + item.toLowerCase() + " do you want to return? ");
        for( int i = 0; i < array.size(); i++){
            if(array.get(i).getID().equals(rentID)){
                if(!array.get(i).getStatus()) {

                    array.get(i).setStatus(true);
                    try {
                        array.get(i).setReturnDate(Tools.getString("What is the return date? (YYYY-MM-DD)"));
                    } catch (Exception e) {
                        System.out.println("Wrong format, assuming return date is today.");
                        array.get(i).setAutomaticReturnDate();
                    }

                    EmployeeController.customerList.get(customerIndex).addCredit();
                    if(Tools.getString("Successfully returned, would you like to leave a review?(y/n)").equals("y")){
                        array.get(i).addRating(Tools.getInt("Enter rating 1-5"));
                        array.get(i).addReview(Tools.getString("Write a short review"));
                    }
                    EmployeeController.customerList.get(customerIndex).removeFromLibrary(array.get(i));
                    if(item.equals("Game")) {removeGame(array.get(i));}
                    if(item.equals("Song")) {removeSong(array.get(i));}
                    }

                    // sets the variable 'rent' to the daily rent multiplied by the amount of days
                    if (EmployeeController.customerList.get(customerIndex).getCredit() >= CREDIT_COST){
                        EmployeeController.customerList.get(customerIndex).removeCredit(CREDIT_COST);
                        System.out.println("5 credits have been deducted from your account to pay for this item");
                    } else {
                        rent = calcRent(customerIndex);
                        System.out.println("The cost for renting the song for " + calcDays(i) + " days is: " + rent);
                    }

                    Screens.customerScreen(ID);
                    return rent; // returns the rent cost
                }
            if(i == array.size()){
                System.out.println("Does not exist in your library");
            }
            }
        Screens.customerScreen(ID);
        return 0;
    }


    public static void sendMessage(String sender){
        EmployeeController.viewAllCustomer(false);
        String recipient = Tools.getString("Who do you want to send a message to?");

        if(EmployeeController.customerExists(recipient)){
            String message = Tools.getString("Enter message: ");

            Message message1 = new Message(message, sender);

            EmployeeController.customerList.get(EmployeeController.findCustomer(recipient)).addMessage(message1);

            System.out.println("Message sent!");
        } else {
            System.out.println("User does not exist");
        }
        Screens.customerScreen(sender);
    }

    public static void removeMessage(String ID){
        int customerIndex = EmployeeController.findCustomer(ID);

        EmployeeController.customerList.get(customerIndex).viewInbox();
        int removeIndex = Tools.getInt("Which message do you want to remove? ") - 1;
        try {
            EmployeeController.customerList.get(customerIndex).removeMessage(removeIndex);
            System.out.println("Message has been deleted");
        } catch(Exception e){
            System.out.println("Message does not exist");
        }
    }

    public static void searchGames(String ID){
        String genre = Tools.getString("What genre? ");

    }




}
