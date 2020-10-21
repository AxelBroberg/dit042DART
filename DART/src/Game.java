import java.util.ArrayList;
import java.util.Comparator;

public class Game extends Rentable {
    static ArrayList<Rentable> gameList = new ArrayList<>();

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


    public static void customerViewAllGames(){

        for ( Rentable game : gameList) {
            System.out.println(game.toString());
        }
    }

    public static void viewGamesByGenre(String genre){
        for (Rentable game : gameList) {
            if(game.getGenre().equals(genre)) {
                System.out.println(game.toString());
            }
        }
    }









}


