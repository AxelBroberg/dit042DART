import java.util.ArrayList;
import java.util.Collections;

// ---------------------------------------Milestone 2-------------------------------------------
//
// Changes because of the feedback we got on the last milestone:
// Moved the ArrayLists customerGames & customerList to the CustomerController
// Moved the methods addGame & removeGame to the CustomerController
//
// We also added a toString, which we didnt have in the last milestone
//
// We did some modifications to the Constructor so that it doesn't only take a name as a parameter
//
// In order to implement Epic Feature 7 we modified the Customer class to have
// its own library ArrayList, which will store items, in this case games and songs
//
// In order to implement Epic Feature 8 we modified the Customer class by adding
// a membership String variable, which will hold what membership the Customer has, the default being "regular"
// In order for the membership to get upgraded we added an upgradeMembership method
// which will upgrade the membership to the next tier
// Added amountspent variable to implement Epic Feature 11 - Rent history
//
// ---------------------------------------Milestone 3-------------------------------------------
//
// Got FEEDBACK that customerLibrary (was called "library") should not be part of customer class, which seems
// odd since each individual customer object should have their own
// private and unique library with the games they are currently renting
// This is NOT a library of all games/songs on the platform, only the games/songs
// a certain specific customer currently is renting

public class Customer {

    private String ID;
    private String name;
    private int credit;
    private String password;

    private ArrayList<Rentable> customerLibrary = new ArrayList<>();
    private ArrayList<Message> inbox = new ArrayList<>();
    private double amountSpent;

    public ArrayList<Rentable> getCustomerLibrary() {
        return customerLibrary;
    }

    public String viewGamesByGenre(ArrayList<Rentable> itemsList, String genre){
        String gameStr = "";
        for (Rentable game : itemsList) {
            if(game instanceof Game) {
                if (game.getGenre().equals(genre)) {
                    gameStr = gameStr.concat(game.toString() + System.lineSeparator());
                }
            }
        }
        return gameStr;
    }

    public String viewSongByYear(ArrayList<Rentable> itemsList, int year){
        String songStr = "";
        for (Rentable song : itemsList) {
            if(song instanceof Song) {
                if (song.getYear() == year) {
                    songStr = songStr.concat(song.toString() + System.lineSeparator());
                }
            }
        }
        return songStr;
    }

    public String showItems(ArrayList<Rentable> itemsList, int itemType, int selectionSorting, String optionalGenreOrYear){
        String itemStr = "";
        if (itemType == 1) {
            ArrayList<Rentable> array = itemsList;
            if(selectionSorting == 1) {
                return showItemsOfType(itemsList, 1);
            } else if (selectionSorting == 2) {
                return viewGamesByGenre(itemsList, optionalGenreOrYear);
            } else if (selectionSorting == 3){
                array.sort(new RatingsComparator());
                Collections.reverse(array);
                for (Rentable game: array) {
                    if(game instanceof Game) {
                        itemStr = itemStr.concat(game.toString() + System.lineSeparator());
                    }
                }
                return itemStr;
            } else if (selectionSorting == 4){
                array.sort(new YearComparator());
                Collections.reverse(array);
                for (Rentable game: array) {
                    if(game instanceof Game) {
                        itemStr = itemStr.concat(game.toString() + System.lineSeparator());
                    }
                }
                return itemStr;
            }
        } else {
            ArrayList<Rentable> array = itemsList;
            if(selectionSorting == 1) {
                return showItemsOfType(itemsList, 2);
            } else if (selectionSorting == 2){
                try {
                    int year = Integer.parseInt(optionalGenreOrYear);
                    return viewSongByYear(itemsList, year);
                } catch (Exception exception){
                    return ("Year entered in wrong format (should be YYYY), aborting rent process");
                }
            } else if (selectionSorting == 3){
                array.sort(new RatingsComparator());
                Collections.reverse(array);
                for ( Rentable song: array) {
                    if(song instanceof Song) {
                        itemStr = itemStr.concat(song.toString() + System.lineSeparator());
                    }
                }
                return itemStr;
            } else if (selectionSorting == 4){
                array.sort(new YearComparator());
                Collections.reverse(array);
                for ( Rentable song: array) {
                    if(song instanceof Song) {
                        itemStr = itemStr.concat(song.toString() + System.lineSeparator());
                    }
                }
                return itemStr;
            }
        }
        return "Could not find any items";
    }

    public String showItemsOfType(ArrayList<Rentable> itemsList, int type){

        String itemStr = "";
        if(type == 1) {
            for (Rentable rentable : itemsList) {
                if (rentable instanceof Game) {
                    itemStr = itemStr.concat(rentable.toString() + System.lineSeparator());
                }
            }
        } else {
            for (Rentable rentable : itemsList) {
                if (rentable instanceof Song) {
                    itemStr = itemStr.concat(rentable.toString() + System.lineSeparator());
                }
            }
        }
        return itemStr;
    }

    Customer(String name, String password){
        this.name = name;
        this.ID = Tools.randomizeID();
        this.amountSpent = 0;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setCustomerLibrary(ArrayList<Rentable> items){
        this.customerLibrary = items;
    }

    public void setID(String ID){
        this.ID = ID;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setInbox(ArrayList<Message> inbox) {
        this.inbox = inbox;
    }

    public void setAmountSpent(double amountSpent) {
        this.amountSpent = amountSpent;
    }

    public ArrayList<Message> getInbox() {
        return inbox;
    }

    public String getID(){ return ID; }

    public double getAmountSpent() {
        return amountSpent;
    }

    public void addSpent(double spent){
        this.amountSpent = this.amountSpent + spent;
    }

    public int getCredit() { return credit; }

    public String getName() {
        return name;
    }

    public String getStrMembership(){
        return "none";
    }

    public void addCreditAmount(int credits){
        this.credit+= credits;
    }

    public void addCredit() {
        final int REG_CREDITS = 0;
        addCreditAmount(REG_CREDITS);
    }

    public double memberDiscount(){
        final double REG_DISCOUNT = 1;
        return REG_DISCOUNT;
    }

    public boolean libraryFull(){
        final int MAX_LIBRARY_REG = 1;
        return getCustomerLibrary().size() >= MAX_LIBRARY_REG;
    }

    public void removeCredit(int remove){
        this.credit -= remove;
    }

    public Rentable findItem(ArrayList<Rentable> itemsList, int item, String ID){
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

    public String viewInbox(Customer customer){
        return customer.viewInbox();
    }

    public void removeMessage(int index, Customer customer){
        if(index <= customer.getInboxSize()) {
            customer.removeMessage(index);
        }
    }

    public void requestUpgrade(ArrayList<Customer> upgradeRequests, Customer customer){
        upgradeRequests.add(customer);
    }

    public boolean sendMessage(ArrayList<Customer> customerList, String message, String recipientID, Customer sender){
        Customer recipient = getCustomer(customerList, recipientID);
        if (recipient == null){
            return false;
        } else {
            recipient.addMessage(new Message(message, sender.getID()));
            return true;
        }
    }

    public Customer getCustomer(ArrayList<Customer> customerList, String ID){
        for (Customer customer : customerList) {
            if (customer.getID().equals(ID)) {
                return customer;
            }
        }
        return null;
    }

    public String viewUnread(Customer customer){
        return customer.viewUnread();
    }

    public boolean rentItem(Rentable item, String rentDate){
        boolean wasRented = false;
        if (item!= null && item.status && !libraryFull()){
            //SETTING RENT
            try {
                item.setRentDate(rentDate);
            } catch (Exception e) {
                System.out.println("ERROR: Wrong format, assuming rent date is today.");
                item.setAutomaticRentDate();
            }
            addToLibrary(item);
            wasRented = true;
        }
        return wasRented;
    }

    public RentHistoryItem returnItem(String itemID, String review, int rating, String returnDate){
        int CREDIT_COST = 5;
        Rentable item;
        boolean reviewLeft = false;
        boolean ratingLeft = false;
        boolean returned = false;
        long calcDaysResult = 0;
        double rent = 0;
        String itemTitle = "";
        for (int i = 0; i < customerLibrary.size(); i++) {
            if (customerLibrary.get(i).getID().equals(itemID)) {
                item = customerLibrary.get(i);
                try {
                    item.setReturnDate(returnDate);
                } catch (Exception e) {
                    System.out.println("ERROR: Wrong format, assuming return date is today.");
                    item.setAutomaticReturnDate();
                }
                calcDaysResult = Tools.calcDays(item);
                rent = calcDaysResult * memberDiscount() * item.getDailyRent();
                if (getCredit() >= CREDIT_COST){
                    rent = 0;
                    removeCredit(CREDIT_COST);
                }
                addCredit();
                if (rating != 0){
                    item.addRating(rating);
                    if (!review.equals("")) {
                        item.addReview(review);
                        reviewLeft = true;
                    }
                    ratingLeft = true;
                }
                customerLibrary.remove(item);
                addSpent(rent);
                itemTitle = item.getTitle();
                item.setStatus(true);
                item.addRentFrequency();
                returned = true;
            }
        }

        if (reviewLeft) {
            return (new RentHistoryItem(itemTitle, rent, ID, (int)calcDaysResult, itemID, rating, review));
        }
        else if (ratingLeft) {
            return (new RentHistoryItem(itemTitle, rent, ID, (int) calcDaysResult, itemID, rating));
        }
        else if (returned){
            return (new RentHistoryItem(itemTitle, rent, ID, (int)calcDaysResult, itemID));
        } else {
            return null;
        }
    }

    public String viewRented(){
        String rentedItems = "";
        for ( Rentable item : customerLibrary) {
            rentedItems = rentedItems.concat(item.toString() + System.lineSeparator());
        }
        return rentedItems;
    }

    public String viewInbox(){
        String inboxStr = "";
        for(Message message : inbox){
            inboxStr = inboxStr.concat((inbox.indexOf(message) + 1) + ". " + message + System.lineSeparator());
        }
        return inboxStr;
    }

    public void removeMessage(int index){
        inbox.remove(index);
    }

    public String viewUnread(){
        String inboxStr = "";
        for(Message message : inbox){
            if(!message.isRead()){
                inboxStr = inboxStr.concat((inbox.indexOf(message) + 1) + ". " + message + System.lineSeparator());
            }
        }
        return inboxStr;
    }

    public void addMessage(Message message){
        this.inbox.add(message);
    }
    public int getInboxSize(){ return inbox.size(); }
    public void addToLibrary(Rentable item){ customerLibrary.add(item); }

    public String toString(){
        return getID() + " : " + this.name + System.lineSeparator();
    }
}
