import java.util.ArrayList;

public class CustomerSilver extends Customer {

    CustomerSilver(String name, ArrayList<Rentable> items, String ID, int credit, double amountSpent, ArrayList<Message> inbox, String password){
        super(name, password);
        setCustomerLibrary(items);
        setID(ID);
        setCredit(credit);
        setInbox(inbox);
        setAmountSpent(amountSpent);
    }

    @Override
    public void addCredit() {
        final int SILVER_CREDITS = 1;
        addCreditAmount(SILVER_CREDITS);
    }

    @Override
    public double memberDiscount(){
        final double SILVER_DISCOUNT = 0.9;
        return SILVER_DISCOUNT;
    }

    @Override
    public boolean libraryFull(){
        final int MAX_LIBRARY_SILVER = 3;
        return getCustomerLibrary().size() >= MAX_LIBRARY_SILVER;
    }

    @Override
    public String getStrMembership(){
        return "silver";
    }

}




