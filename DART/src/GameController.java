import java.util.ArrayList;

public class GameController {
    static ArrayList<Rentable> gameList = new ArrayList();

    public static void customerViewAllGames(){

        for ( Rentable game : gameList) {
            System.out.println(game.toString());
        }
    }

    public static void empViewAllGames(){
        for ( Rentable game : GameController.gameList) {
            System.out.println(game.toString());
        }
        Screens.employeeScreen();
    }



}
