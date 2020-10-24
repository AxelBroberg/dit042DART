import java.util.ArrayList;

public class CustomerPlatinum extends Customer{


    CustomerPlatinum(String name, ArrayList<Rentable> items, String ID, int credit, double amountSpent, ArrayList<Message> inbox, String password){
        super(name, password);
        setCustomerLibrary(items);
        setID(ID);
        setCredit(credit);
        setInbox(inbox);
        setAmountSpent(amountSpent);
    }


    @Override
    public void addCredit() {
        final int PLAT_CREDITS = 3;
        addCreditAmount(PLAT_CREDITS);
    }

    @Override
    public double memberDiscount(){
        final double PLAT_DISCOUNT = 0.75;
        return PLAT_DISCOUNT;
    }

    @Override
    public boolean libraryFull(){
        final int MAX_LIBRARY_PLAT = 7;
        return getCustomerLibrary().size() >= MAX_LIBRARY_PLAT;
    }

    @Override
    public String getStrMembership(){
        return "plat";
    }

}
