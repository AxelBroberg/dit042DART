import java.time.LocalDate;

//To implement Epic Feature 7, we modified the class Game by
//creating a new class called Rentable and having both games and songs inherit from it.

public class Rentable {

    protected String title;
    protected String ID;
    protected double dailyRent;
    protected boolean status;
    protected LocalDate rentDate;
    protected LocalDate returnDate;

    Rentable(String title, double dailyRent){
        this.title = title;
        this.dailyRent = dailyRent;
        this.status = true;

        this.ID = Tools.randomizeID();

        this.rentDate = LocalDate.parse("2020-01-01");
        this.returnDate = LocalDate.parse("2020-01-01");
    }

    public void setID(){ ID = Tools.randomizeID(); }
    public String getID (){ return ID; }

    public void setDailyRent(double dailyRent){ this.dailyRent = dailyRent; }
    public double getDailyRent(){ return dailyRent; }

    public void setStatus (boolean status){ this.status = status; }
    public boolean getStatus(){ return status; }

    public void setRentDate(String rentADate){ rentDate = LocalDate.parse(rentADate); } // Method for assigning a date input to the rentDate variable
    public void setAutomaticRentDate(){ rentDate = LocalDate.now(); } //Method for automatically assigning a date to the rentDate variable
    public LocalDate getRentDate(){ return rentDate; }

    public void setReturnDate(String returnADate){ returnDate = LocalDate.parse(returnADate); } // Method for assigning a date input to the returnDate variable
    public void setAutomaticReturnDate(){ returnDate = LocalDate.now(); } //Method for automatically assigning a date to the returnDate variable
    public LocalDate getReturnDate(){ return returnDate; }



}
