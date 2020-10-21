import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

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

    public static long calcDays(int i, ArrayList<Rentable> array){ // a method that returns days between game was rented and returned
        if(ChronoUnit.DAYS.between(array.get(i).getRentDate(), array.get(i).getReturnDate()) <= 0) {
            throw new EarlyDateException("Invalid operation. Upon returning an item, the number of days rented must be positive.");
        } else {
            return ChronoUnit.DAYS.between(array.get(i).getRentDate(), array.get(i).getReturnDate());
        }
    }

    public static double calcRent(int i, ArrayList<Rentable> array, String ID){
        final double[] memDiscount = new double[]{1, 0.90, 0.85, 0.75};
        double discount = 1;
        for (int j = 0; j < Employee.customerList.size(); j++) {
            if (Employee.customerList.get(j).getID().equals(ID)) {
                switch(Employee.customerList.get(j).getMembership()) {
                    case "platinum" -> discount = memDiscount[3];
                    case "gold" -> discount = memDiscount[2];
                    case "silver" -> discount = memDiscount[1];
                    default -> discount = memDiscount[0];
                }
            }
        }
        return array.get(i).getDailyRent() * calcDays(i, array) * discount;
    }

    // Chronounit: https://stackoverflow.com/questions/27005861/calculate-days-between-two-dates-in-java-8



    public static void requestUpgrade(String ID) throws Exception {
        String upgID = Tools.getString("Enter your id: "); //Declares a String variable, which is used to remove customers
        Employee.upgradeRequestsID.add(upgID);
        Screens.customerScreen(ID);
    }


    // Milestone 2: Changed gameList and songList to an ArrayList of rentable
    // and with a few changes to this method allowed us to reuse this method for both  games and song albums
    // Fixed to that sorting is possible
    public static void rentItem(String item, String ID) throws Exception { // method to rent a game, by adding a game to a customers list
        if(!Employee.customerExists(ID)) {
            System.out.println("Customer does not exist!");
            Screens.customerScreen(ID);
        }
        int customerIndex = Employee.findCustomer(ID);
        int selectionSorting;
        if(!Employee.customerList.get(customerIndex).libraryFull()) {
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
                        for (int j = 0; j < Employee.customerList.size(); j++) {
                            if (Employee.customerList.get(j).getID().equals(ID)) {
                                Employee.customerList.get(j).addToLibrary(array.get(i));
                                array.get(i).addRentFrequency();
                                System.out.println("Successfully rented");
                                j = Employee.customerList.size();
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

    //Changed so when returning creates a new RentHistoryItem object and adds to the arraylist in manager controller
    public static void returnItem(String item, String ID) throws Exception { // a method to return games to the store
        if(!Employee.customerExists(ID)) {
            System.out.println("Customer does not exist!");
            Screens.customerScreen(ID);
        }
        boolean leftReview = false;
        int rating = 0;
        String review = "";
        int customerIndex = Employee.findCustomer(ID);
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
                    } catch (Exception exception) {
                        System.out.println("Wrong format, assuming return date is today.");
                        array.get(i).setAutomaticReturnDate();
                    }

                    try {
                        rent = calcRent(customerIndex, array, ID);
                    } catch (Exception exception){
                        System.out.println(exception);
                        System.out.println("Return process was aborted, returning to menu");
                        Screens.customerScreen(ID);
                    }

                    Employee.customerList.get(customerIndex).addCredit();
                    if(Tools.getString("Successfully returned, would you like to leave a review?(y/n)").equals("y")){
                        rating = Tools.getInt("Enter rating 1-5");
                        array.get(i).addRating(rating);
                        review = Tools.getString("Write a short review");
                        array.get(i).addReview(review);
                        leftReview = true;
                    }

                    Employee.customerList.get(customerIndex).removeFromLibrary(array.get(i));
                    if(item.equals("Game")) {removeGame(array.get(i));}
                    if(item.equals("Song")) {removeSong(array.get(i));}
                    }

                    // sets the variable 'rent' to the daily rent multiplied by the amount of days
                    if (Employee.customerList.get(customerIndex).getCredit() >= CREDIT_COST){
                        Employee.customerList.get(customerIndex).removeCredit(CREDIT_COST);
                        System.out.println("5 credits have been deducted from your account to pay for this item");
                        rent = 0;
                    } else {
                        System.out.println("The cost for renting the song for " + calcDays(i, array) + " days is: " + rent);
                    }
                    if(leftReview) {
                        Controller.rentHistory.add(new RentHistoryItem(rent, ID, (int) calcDays(i, array), rentID, rating, review));
                    } else {
                        Controller.rentHistory.add(new RentHistoryItem(rent, ID, (int) calcDays(i, array), rentID));
                    }
                    Employee.customerList.get(customerIndex).addSpent(rent);
                    array.get(i).addProfit(rent);
                    Screens.addTotalProfit(rent);
                    Screens.customerScreen(ID);
                }
            if(i == array.size()){
                System.out.println("Does not exist in your library");
            }
            }
        Screens.customerScreen(ID);
    }


    public static void sendMessage(String sender) throws Exception {
        Employee.viewAllCustomer(false);
        String recipient = Tools.getString("Who do you want to send a message to?");

        if(Employee.customerExists(recipient)){
            String message = Tools.getString("Enter message: ");

            Message message1 = new Message(message, sender);

            Employee.customerList.get(Employee.findCustomer(recipient)).addMessage(message1);

            System.out.println("Message sent!");
        } else {
            System.out.println("User does not exist");
        }
        Screens.customerScreen(sender);
    }

    public static void removeMessage(String ID){
        int customerIndex = Employee.findCustomer(ID);

        Employee.customerList.get(customerIndex).viewInbox();
        int removeIndex = Tools.getInt("Which message do you want to remove? ") - 1;
        try {
            Employee.customerList.get(customerIndex).removeMessage(removeIndex);
            System.out.println("Message has been deleted");
        } catch(Exception e){
            System.out.println("Message does not exist");
        }
    }

}
