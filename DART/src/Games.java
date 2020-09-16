import java.util.ArrayList;
import java.util.Scanner;

public class Games {
    String title;
    int ID;
    String genre;
    double dailyRent;
    boolean status = true;
    static ArrayList<Games> gameList = new ArrayList();

    Games(String title){ this.title = title; }

    public void setID (int ID){ this.ID = ID; }
    public int getID (){ return ID; }

    public void setGenre (String genre){ this.genre = genre; }
    public String getGenre (){ return genre; }

    public void setDailyRent(double dailyRent){ this.dailyRent = dailyRent; }
    public double getDailyRent(){ return dailyRent; }

    public void setStatus (boolean status){ this.status = status; }
    public boolean getStatus(){ return status; }

    public static void registerGame(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter game title: "); Games g = new Games(input.nextLine());
        System.out.print("Enter game ID: "); g.setID(input.nextInt());
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
        Screens.customerScreen();
    }

    public static void removeGame(){
        Scanner input = new Scanner(System.in);
        boolean removed = false;
        int i;
        System.out.println("Games: ");
        gameList.forEach(Games::printGame);
        System.out.print("Enter the ID of the game you want to remove: ");

        int removeID = input.nextInt();
        for(i = 0; i < gameList.size(); i++){
            if(gameList.get(i).ID == removeID){
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
        System.out.println("Status: " + status);
    }


}
