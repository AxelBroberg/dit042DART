import java.util.ArrayList;

public class CustomerGold extends Customer{

    CustomerGold(String name, ArrayList<Rentable> items, String ID, int credit, double amountSpent, ArrayList<Message> inbox, String password){
        super(name, password);
        setCustomerLibrary(items);
        setID(ID);
        setCredit(credit);
        setInbox(inbox);
        setAmountSpent(amountSpent);
    }


    @Override
    public void addCredit() {
        final int GOLD_CREDITS = 2;
        addCreditAmount(GOLD_CREDITS);
    }

    @Override
    public double memberDiscount(){
        final double GOLD_DISCOUNT = 0.85;
        return GOLD_DISCOUNT;
    }

    @Override
    public boolean libraryFull(){
        final int MAX_LIBRARY_GOLD = 5;
        return getCustomerLibrary().size() >= MAX_LIBRARY_GOLD;
    }

    @Override
    public String getStrMembership(){
        return "gold";
    }
}
