import java.util.ArrayList;
import java.util.Scanner;

// We created this class because we got feedback on the last milestone that we are supposed to have a controller.
// We decided that we would have one controller per object class
// (customer, employee, game) instead of having one controller for the entire program
//
// In here we have the autoRegisterGame, fillGames, registerCustomer, removeCustomer and viewAllCustomer originating from the Games class
//
// We also created a findCustomer method, which is used to generalize the methods used in the last milestone
//
// In order to implement epic feature 7 we modified the class CustomerController by adding the methods:
// - autoRegisterSong, which is just like the autoRegisterGame but it registers songs
// - registerItem, which is a modification of registerGame, but it can also register songs
// - removeItem, which is a modification of removeGame, but it can also remove songs
//
// In order to implement epic feature 8 we modified the class CustomerController by adding the methods:
// - upgradeCustomer, which is used to upgrade the customers that have requested an upgrade
// - viewAllUpgRequest, which is used to print all upgrade requests
// We also added an ArrayList to hold all of the upgrade requests, which is named upgradeRequestsID

//To implement epic feature 12, most methods now throw an exception to handle incorrect creation of games or song albums
//registerItem() catches and handles any incorrect creation by printing the error to the user and letting them try again
//this repeats until a game/song is created properly

public class EmployeeController {



    /*public static void removeGame(){
        boolean removed = false;
        int i;
        System.out.println("Games: ");
        for ( Rentable game : GameController.gameList) {
            System.out.println(game.toString());
        }
        String removeID = Tools.getString("Enter the ID of the game you want to remove: ");
        for(i = 0; i < GameController.gameList.size(); i++){
            if(GameController.gameList.get(i).getID().equals(removeID)){
                GameController.gameList.remove(i);
                System.out.println("Successfully removed!");
                removed = true;
                i = GameController.gameList.size();
            }
        }
        if(!removed){
            System.out.println("Game with id " + i + " not found.");
        }
        Screens.employeeScreen();
    }
    public static void registerSong(){

        Song s = new Song(Tools.getString("Enter title: "), Tools.getString("Enter artist: "), Tools.getDouble("Enter daily rent: "), Tools.getInt("Enter release year: "), Tools.getDouble("Enter ratings: ")); // OBJECT CREATION

        System.out.print("You have added game: ");
        System.out.println(s.toString());
        SongController.songList.add(s);
        Screens.employeeScreen();
    }

    public static void removeSong(){
        boolean removed = false;
        int i;
        System.out.println("Songs: ");
        for ( Rentable song : SongController.songList) {
            System.out.println(song.toString());
        }


        String removeID = Tools.getString("Enter the ID of the song you want to remove: ");
        for(i = 0; i < SongController.songList.size(); i++){
            if(SongController.songList.get(i).getID().equals(removeID)){
                SongController.songList.remove(i);
                System.out.println("Successfully removed!");
                removed = true;
                i = SongController.songList.size();
            }
        }
        if(!removed){
            System.out.println("Song with id " + removeID + " not found.");
        }
        Screens.employeeScreen();
    }*/












}
