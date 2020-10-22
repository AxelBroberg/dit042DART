import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;


//To implement Epic Feature 7, we modified the class Game by
//creating a new class called Rentable and having both games and songs inherit from it.

public abstract class Rentable implements Comparable<Rentable>{
    //Added profit and rentFrequency to implement Epic Feature 11 - Rent history
    protected String title;
    protected String ID;
    protected double dailyRent;
    protected boolean status;
    protected ArrayList<Integer> ratings;
    protected ArrayList<String> reviews;
    protected int year;
    protected LocalDate rentDate;
    protected LocalDate returnDate;
    protected double profit;
    protected int rentFrequency;

    public Rentable(String title, double dailyRent, int year) throws Exception{
        if (title.isEmpty() || title.equals(" ") ){
            throw new NameEmptyException("Empty title is not allowed.");
        } else {
            this.title = title;
        }
        if (dailyRent < 0){
            throw new NegativeRentException("Negative rent is not allowed.");
        } else {
            this.dailyRent = dailyRent;
        }
        this.status = true;
        this.ratings = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.year = year;

        this.ID = Tools.randomizeID();

        this.rentDate = LocalDate.parse("2020-01-01");
        this.returnDate = LocalDate.parse("2020-01-01");
        this.profit = 0;
        this.rentFrequency = 0;
    }

    public String getID (){ return ID; }

    public void addReview(String review){
        this.reviews.add(review);
    }

    public double getAverageRating() {
        int sum = 0;
        int size = ratings.size() > 0 ? ratings.size() : 1; //Ensures we are not dividing by zero
        for(Integer value : ratings){
            sum += value;
        }
        return ((double)sum / size);
    }

    public void addRating(int rating) {
        this.ratings.add(rating);
    }

    public double getDailyRent(){ return dailyRent; }

    public void setStatus (boolean status){ this.status = status; }
    public boolean getStatus(){ return status; }

    public void setRentDate(String rentADate){ rentDate = LocalDate.parse(rentADate); } // Method for assigning a date input to the rentDate variable
    public void setAutomaticRentDate(){ rentDate = LocalDate.now(); } //Method for automatically assigning a date to the rentDate variable
    public LocalDate getRentDate(){ return rentDate; }

    public void setReturnDate(String returnADate){ returnDate = LocalDate.parse(returnADate); } // Method for assigning a date input to the returnDate variable
    public void setAutomaticReturnDate(){ returnDate = LocalDate.now(); } //Method for automatically assigning a date to the returnDate variable
    public LocalDate getReturnDate(){ return returnDate; }

    public String getTitle() {
        return title;
    }

    public double getProfit() {
        return profit;
    }

    public void addProfit(double profit){
        this.profit = this.profit + profit;
    }

    public int getRentFrequency() {
        return rentFrequency;
    }

    public void addRentFrequency(){
        this.rentFrequency += 1;
    }

    public int getYear() {
        return year;
    }

    public String getGenre(){
        System.out.println("WRONG GENRE");
        return "WRONG GENRE";
    }

    @Override
    public int compareTo(Rentable o) {
        if(this.getAverageRating() < o.getAverageRating()){
            return -1;
        } else if(this.getAverageRating() < o.getAverageRating()){
            return 0;
        } else {
            return 1;
        }
    }


}
