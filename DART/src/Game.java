// Changes because of the feedback we got on the last milestone:
// Moved the ArrayList gameList to the GameController
// Moved most of the methods to the Rentable class
//
// We also added a toString, which we didnt have in the last milestone
//
// We did some modifications to the Constructor so that it doesn't only take a title as a parameter
//
// In order to implement Epic Feature 7 we modified the Game class to be a child class of Rentable

import java.util.Comparator;

public class Game extends Rentable {

    private String genre;

    Game(String title, String genre, double dailyRent, int year) throws Exception {
        super(title, dailyRent, year);
        this.genre = genre;
    }

    @Override
    public String getGenre (){ return genre; }

    public String toString(){
        String s = getID() + " : " + this.title + " [" + getGenre() + "]. " + getDailyRent() + ". "
                + "Year: " + getYear()
                + " Rating: " + getAverageRating() + "/5"
                + " Status: ";

        if (getStatus()){
            s = s + "Available";
        }
        else{
            s = s + "Unavailable";
        }
        return s;
    }



}
