import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private String title;
    private String genre;
    private String ID;
    private double dailyRent;
    private boolean status;
    private static LocalDate rentDate;
    private static LocalDate returnDate;



    Game(String title, String genre, double dailyRent ){
        this.title = title;
        this.genre = genre;
        this.dailyRent = dailyRent;
        this.status = true;

        setAutomaticRentDate(); // Sets the rent date to now so that it is available in the object, to be changed later
        setAutomaticReturnDate(); // Sets the return date to now so that it is available in the object, to be changed later

        this.ID = Tools.randomizeID();

    } // CONSTRUCTOR

    public void setID(){ ID = Tools.randomizeID(); }
    public String getID (){ return ID; }

    public void setGenre (String genre){ this.genre = genre; }
    public String getGenre (){ return genre; }

    public void setDailyRent(double dailyRent){ this.dailyRent = dailyRent; }
    public double getDailyRent(){ return dailyRent; }

    public void setStatus (boolean status){ this.status = status; }
    public boolean getStatus(){ return status; }

    public static void setRentDate(String rentADate){ rentDate = LocalDate.parse(rentADate); } // Method for assigning a date input to the rentDate variable
    public static void setAutomaticRentDate(){ rentDate = LocalDate.now(); } //Method for automatically assigning a date to the rentDate variable
    public static LocalDate getRentDate(){ return rentDate; }

    public static void setReturnDate(String returnADate){ returnDate = LocalDate.parse(returnADate); } // Method for assigning a date input to the returnDate variable
    public static void setAutomaticReturnDate(){ returnDate = LocalDate.now(); } //Method for automatically assigning a date to the returnDate variable
    public static LocalDate getReturnDate(){ return returnDate; }

















    public String toString(){
        String s = getID() + " : " + this.title + " [" + getGenre() + "]. " + getDailyRent() + ". " + "Status: ";

        if (getStatus()){
            s = s + "Available";
        }
        else{
            s = s + "Unavailable";
        }


        return s;
    }


}
