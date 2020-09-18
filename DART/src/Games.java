import java.util.ArrayList;
import java.util.Scanner;

public class Games {
    String title;
    String ID;
    String genre;
    double dailyRent;
    boolean status = true;
    static ArrayList<Games> gameList = new ArrayList();

    Games(String title){ this.title = title; }

    public void setID(){ ID = randomID.randomizeID(); }
    public String getID (){ return ID; }

    public void setGenre (String genre){ this.genre = genre; }
    public String getGenre (){ return genre; }

    public void setDailyRent(double dailyRent){ this.dailyRent = dailyRent; }
    public double getDailyRent(){ return dailyRent; }

    public void setStatus (boolean status){ this.status = status; }
    public boolean getStatus(){ return status; }

    public static void registerGame(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter game title: "); Games g = new Games(input.nextLine());
        g.setID(); System.out.println("Random ID <" + g.getID() + "> was assigned.");
        System.out.print("Enter game genre: "); g.setGenre(input.nextLine());
        System.out.print("Enter game daily rent fee: "); g.setDailyRent(input.nextDouble());
        g.setStatus(true);

        System.out.print("You have added game: ");
        g.printGame();
        gameList.add(g);
        Screens.employeeScreen();
    }

    public static void viewAllGames(){
        gameList.forEach(Games::printGame);
    }

    public static void empViewAllGames(){
        gameList.forEach(Games::printGame);
        Screens.employeeScreen();
    }

    public static void removeGame(){
        Scanner input = new Scanner(System.in);
        boolean removed = false;
        int i;
        System.out.println("Games: ");
        gameList.forEach(Games::printGame);
        System.out.print("Enter the ID of the game you want to remove: ");

        String removeID = input.nextLine();
        for(i = 0; i < gameList.size(); i++){
            if(gameList.get(i).ID.equals(removeID)){
                gameList.remove(i);
                System.out.println("Successfully removed!");
                removed = true;
                i = gameList.size();
            }
        }
        if(!removed){
            System.out.println("Game with id " + i + " not found.");
        }
    }

    public void printGame(){
        System.out.print(getID() + " : ");
        System.out.print(this.title + " [");
        System.out.print(getGenre() + "]. ");
        System.out.print(getDailyRent() + ". ");
        System.out.println("Status: " + getStatus());
    }

    public static void fillGames(){
        String[] gameName = {"The Last of us Part 2", "The Witcher 3 Wild Hunt", "Red Dead Redemption 2"};
        String[] genre = {"action", "comedy", "family"};
        double[] price = {12, 13, 14};
        for(int i = 0; i < gameName.length; i++){
            autoRegisterGame(gameName[i], genre[i], price[i]);
        }
        Screens.employeeScreen();
    }

    public static void autoRegisterGame(String title, String genre, double price){
        System.out.print("Enter game title: "); Games g = new Games(title);
        g.setID(); System.out.println("Random ID <" + g.getID() + "> was assigned.");
        System.out.print("Enter game genre: "); g.setGenre(genre);
        System.out.print("Enter game daily rent fee: "); g.setDailyRent(price);
        g.setStatus(true);

        System.out.print("You have added game: ");
        g.printGame();
        gameList.add(g);

    }



}
