import java.util.ArrayList;
import java.util.Collections;

public class GameController {
    static ArrayList<Rentable> gameList = new ArrayList<>();

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

    /*public static void gamesSortedByRating(){
        Collections.sort(gameList);
        for ( Rentable game : gameList) {
            System.out.println(game.toString());
        }
    }*/

    public static void empViewAllGames(){
        for ( Rentable game : GameController.gameList) {
            System.out.println(game.toString());
        }
        Screens.employeeScreen();
    }





}
