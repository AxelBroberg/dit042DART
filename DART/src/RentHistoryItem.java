public class RentHistoryItem {

    private double rentExpense;
    private String customerID;
    private int daysRented;
    private String itemID;
    private int score;
    private String review;


    public RentHistoryItem(double rentExpense, String customerID, int daysRented, String itemID, int score, String review) {
        this.rentExpense = rentExpense;
        this.customerID = customerID;
        this.daysRented = daysRented;
        this.itemID = itemID;
        this.score = score;
        this.review = review;
    }

    public RentHistoryItem(double rentExpense, String customerID, int daysRented, String itemID, int score) {
        this.rentExpense = rentExpense;
        this.customerID = customerID;
        this.daysRented = daysRented;
        this.itemID = itemID;
        this.score = score;
    }

    public RentHistoryItem(double rentExpense, String customerID, int daysRented, String itemID) {
        this.rentExpense = rentExpense;
        this.customerID = customerID;
        this.daysRented = daysRented;
        this.itemID = itemID;
    }

    public String getCustomerID() { return customerID; }
    public int getDaysRented() { return daysRented; }
    public String getItemID() { return itemID; }
    public int getScore() { return score; }
    public String getReview() { return review; }


    @Override
    public String toString() {
        String str = "customerID='" + customerID + '\'' +
                ", daysRented=" + daysRented +
                ", itemID='" + itemID + '\'';

        if(this.score != 0) {
            str = str + ", score=" + score;
        }

        if(this.review != null){
            str = str + ", review='" + review;
        }

        return str;
    }

    public double getRentExpense() {
        return rentExpense;
    }
}
