import java.util.ArrayList;

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


public class Customer {

    //Added amountspent to implement Epic Feature 11 - Rent history
    private String ID; //creates String variable named 'ID'
    private String name; //creates String variable named 'name'
    private String membership;
    private int credit;

    //Got FEEDBACK that library should not be part of customer class, which seems
    //odd since each individual customer object should have their own
    //private and unique library with the games they are currently renting
    //This is NOT a library of all games/songs on the platform, only the games/songs
    //a certain specific customer currently is renting
    private ArrayList<Rentable> library= new ArrayList<>();
    private ArrayList<Message> inbox = new ArrayList<>();
    private double amountSpent;

    public ArrayList<Rentable> getLibrary() {
        return library;
    }

    Customer(String name){
        this.name = name;
        this.membership = "regular";
        this.ID = Tools.randomizeID();
        this.amountSpent = 0;
    } //Creates a constructor with takes a name

    public String getID(){ return ID; } // returns the id created by the method above
    public String getMembership() { return membership; }

    public double getAmountSpent() {
        return amountSpent;
    }

    public void addSpent(double spent){
        this.amountSpent = this.amountSpent + spent;
    }

    public int getCredit() { return credit; }

    public void addCredit() {
        switch (membership){
            case "regular" -> this.credit+= 0;
            case "silver" -> this.credit+= 1;
            case "gold" -> this.credit+= 2;
            case "platinum" -> this.credit+= 3;
        }
    }

    public double memberDiscount() {
        double discount = 1.0;
        switch (membership){
            case "regular" -> discount = 1.0;
            case "silver" -> discount = 0.90;
            case "gold" -> discount = 0.85;
            case "platinum" -> discount = 0.75;
        }
        return discount;
    }

    public void removeCredit(int remove){
        this.credit -= remove;
    }

    //---------------
    public boolean rentItem(Rentable item, String rentDate){
        boolean wasRented = false;
        if (item.status && !libraryFull()){
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
    //---------------
    public RentHistoryItem returnItem(String itemID, String review, int rating, String returnDate){
        int CREDIT_COST = 5;
        Rentable item;
        boolean reviewLeft = false;
        boolean returned = false;
        long calcDaysResult = 0;
        double rent = 0;
        for (int i = 0; i < library.size(); i++) {
            if (library.get(i).getID().equals(itemID)) {
                item = library.get(i);
                try {
                    item.setReturnDate(returnDate);
                } catch (Exception e) {
                    System.out.println("ERROR: Wrong format, assuming return date is today.");
                    item.setAutomaticReturnDate();
                }
                //Lines below is instead of calcRent, which is now unused.
                calcDaysResult = Tools.calcDays(item);
                rent = calcDaysResult * memberDiscount() * item.getDailyRent();
                if (getCredit() >= CREDIT_COST){
                    rent = 0;
                    removeCredit(CREDIT_COST);
                }
                addCredit();
                if (rating != 0){
                    item.addRating(rating);
                    item.addReview(review);
                    reviewLeft = true;
                }
                library.remove(item);
                item.setStatus(true);
                item.addRentFrequency();
                returned = true;
            }
        }
        //Returning a renthistoryitem since it has all info we need
        //and can easily be added to list of renthistory in the controller
        if (reviewLeft) {
            return (new RentHistoryItem(rent, ID, (int)calcDaysResult, itemID, rating, review));
        } else if (returned){
            return (new RentHistoryItem(rent, ID, (int)calcDaysResult, itemID));
        } else {
            return null;
        }
    }



    //-------------


    public String viewRented(){
        String rentedItems = "";
        for ( Rentable item : library) {
            rentedItems = rentedItems.concat(item.toString() + System.lineSeparator());
        }
        return rentedItems;
    }

    public boolean libraryFull(){
        boolean full = false;
        switch (membership){
            case "regular" -> full = (this.library.size() >= 1);
            case "silver" -> full = (this.library.size() >= 3);
            case "gold" -> full = (this.library.size() >= 5);
            case "platinum" -> full = (this.library.size() >= 7);
        }
        return full;
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
    public void addToLibrary(Rentable item){ library.add(item); }
    public void removeFromLibrary(Rentable item){ library.remove(item); }

    public boolean upgradeMembership(){
        switch (membership) {
            case "regular" -> membership = "silver";
            case "silver" -> membership = "gold";
            case "gold" -> membership = "platinum";
        }

        return false;
    }

    public String toString(){
        return getID() + " : " + this.name + ". Membership: " + this.membership + System.lineSeparator();
    }


}
