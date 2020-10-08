import java.util.ArrayList;

public class SongController {

    static ArrayList<Song> songList = new ArrayList();

    public static void customerViewAllSongs(){

        for (Song song : songList) {
            System.out.println(song.toString());
        }
    }

    public static void empViewAllSongs(){
        for (Song song : songList) {
            System.out.println(song.toString());
        }
        Screens.employeeScreen();
    }
}
