import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    static ArrayList<Game> gameList = new ArrayList();

    public static void customerViewAllGames(){

        for (Game game : gameList) {
            System.out.println(game.toString());
        }
    }

    public static void empViewAllGames(){
        for (Game game : GameController.gameList) {
            System.out.println(game.toString());
        }
        Screens.employeeScreen();
    }



}
