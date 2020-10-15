import java.util.ArrayList;

// In order to implement epic feature 7 we created a SongController for the Song class

public class SongController {

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
