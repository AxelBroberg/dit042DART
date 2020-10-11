import java.util.ArrayList;


public class Customer {


    private String ID; //creates String variable named 'ID'
    private String name; //creates String variable named 'name'
    private String membership;
    private int credit;
    private ArrayList<Rentable> library= new ArrayList<>();
    private ArrayList<Message> inbox = new ArrayList<>();

    Customer(String name){
        this.name = name;
        this.membership = "regular";
        this.ID = Tools.randomizeID();
    } //Creates a constructor with takes a name

    public void setID(){ ID = Tools.randomizeID(); } // a method that assigns a random id to the variable 'ID'
    public String getID(){ return ID; } // returns the id created by the method above
    public String getMembership() { return membership; }

    public int getCredit() { return credit; }

    public void addCredit() {
        switch (membership){
            case "regular" -> this.credit+= 0;
            case "silver" -> this.credit+= 1;
            case "gold" -> this.credit+= 2;
            case "platinum" -> this.credit+= 3;
        }
    }

    public void removeCredit(int remove){
        this.credit -= remove;
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

    public void viewInbox(){
        for(Message message : inbox){
            System.out.println((inbox.indexOf(message) + 1) + ". " + message + System.lineSeparator());
        }
    }

    public void removeMessage(int index){
        inbox.remove(index);
    }

    public void viewUnread(){
        for(Message message : inbox){
            if(!message.isRead()){
                System.out.println((inbox.indexOf(message) + 1) + ". " + message + System.lineSeparator());
            }
        }
    }

    public void addMessage(Message message){
        this.inbox.add(message);
    }

    public void addToLibrary(Rentable item){ library.add(item); }
    public void removeFromLibrary(Rentable item){ library.remove(item); }

    public void upgradeMembership(){
        switch (membership){
            case "regular" -> membership = "silver";
            case "silver" -> membership = "gold";
            case "gold" -> membership = "platinum";
        }
    }

    public String toString(){
        return getID() + " : " + this.name + ". Membership: " + this.membership + System.lineSeparator();
    }


}
