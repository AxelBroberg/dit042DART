import java.util.ArrayList;

public class Song extends Rentable{

    // In order to implement epic feature 7 we created a new class Song, which extends Rentable

    protected String artist;

    Song(String title, String artist, double dailyRent, int year) throws Exception {
        super(title, dailyRent, year);
        this.artist = artist;
    }

    public String getArtist() { return artist; }

    public String toString(){
        String s = getID() + " : " + this.title + " - by " + getArtist() + ". Released in " + getYear() + ". Price: " + getDailyRent() + "SEK. "
                + "Rating: " + getAverageRating() + "/5"
                + " Status: ";

        if (getStatus()){
            s = s + "Available";
        }
        else{
            s = s + "Unavailable";
        }
        return s;
    }
    static ArrayList<Rentable> songList = new ArrayList();

    public static void customerViewAllSongs(){
        for ( Rentable song : songList) {
            System.out.println(song.toString());
        }
    }

    public static void viewSongByYear(int year){
        for (Rentable song : songList) {
            if(song.getYear() == year) {
                System.out.println(song.toString());
            }
        }
    }

    public static void empViewAllSongs() throws Exception {
        for ( Rentable song : songList) {
            System.out.println(song.toString());
        }
        Screens.employeeScreen();
    }
}


