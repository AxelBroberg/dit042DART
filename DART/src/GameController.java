import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    static ArrayList<Game> gameList = new ArrayList();

    public static void customerViewAllGames(){

        gameList.forEach(Game::toString);
    }

    public static void empViewAllGames(){
        GameController.gameList.forEach(Game::toString);
        Screens.employeeScreen();
    }



}
