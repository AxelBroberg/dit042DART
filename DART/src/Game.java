import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private String title;
    private String genre;
    private String ID;
    private double dailyRent;
    private boolean status = true;
    private static LocalDate rentDate;
    private static LocalDate returnDate;



    Game(String title){ this.title = title; } // CONSTRUCTOR

    public void setID(){ ID = Tools.randomizeID(); }
    public String getID (){ return ID; }

    public void setGenre (String genre){ this.genre = genre; }
    public String getGenre (){ return genre; }

    public void setDailyRent(double dailyRent){ this.dailyRent = dailyRent; }
    public double getDailyRent(){ return dailyRent; }

    public void setStatus (boolean status){ this.status = status; }
    public boolean getStatus(){ return status; }

    public static void setRentDate(String rentADate){ rentDate = LocalDate.parse(rentADate); } // Method for assigning a date input to the rentDate variable
    public static void setAutomaticRentDate(){ rentDate = LocalDate.now(); } //Method for automatically assigning a date to the rentDate variable
    public static LocalDate getRentDate(){ return rentDate; }

    public static void setReturnDate(String returnADate){ returnDate = LocalDate.parse(returnADate); } // Method for assigning a date input to the returnDate variable
    public static void setAutomaticReturnDate(){ returnDate = LocalDate.now(); } //Method for automatically assigning a date to the returnDate variable
    public static LocalDate getReturnDate(){ return returnDate; }



    public static void registerGame(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter game title: "); Game g = new Game(input.nextLine()); // OBJECT CREATION
        g.setID(); System.out.println("Random ID <" + g.getID() + "> was assigned.");
        System.out.print("Enter game genre: "); g.setGenre(input.nextLine());
        System.out.print("Enter game daily rent fee: "); g.setDailyRent(input.nextDouble());
        g.setStatus(true);
        setAutomaticRentDate(); // Sets the rent date to now so that it is available in the object, to be changed later
        setAutomaticReturnDate(); // Sets the return date to now so that it is available in the object, to be changed later

        System.out.print("You have added game: ");
        g.printGame();
        gameList.add(g);
        Screens.employeeScreen();
    }



    public static void viewAllGames(){
        gameList.forEach(Game::printGame);
    }

    public static void empViewAllGames(){
        gameList.forEach(Game::printGame);
        Screens.employeeScreen();
    }

    public static void removeGame(){
        Scanner input = new Scanner(System.in);
        boolean removed = false;
        int i;
        System.out.println("Games: ");
        gameList.forEach(Game::printGame);
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
        System.out.print("Status: " );
        if (getStatus()){
            System.out.println("Available");
        }
        else{
            System.out.println("Unavailable");
        }
    }

    public static void fillGames(){ // Method for adding games, used for testing purposes
        String[] gameName = {"The Last of us Part 2", "The Witcher 3 Wild Hunt", "Red Dead Redemption 2"};
        String[] genre = {"action", "comedy", "family"};
        double[] price = {12, 13, 14};

        for(int i = 0; i < gameName.length; i++){
            autoRegisterGame(gameName[i], genre[i], price[i]);
        }
        Screens.employeeScreen();
    }

    public static void autoRegisterGame(String title, String genre, double price){ // Method for adding games, used for testing purposes
        Game g = new Game(title);
        g.setID();
        g.setGenre(genre);
        g.setDailyRent(price);
        g.setStatus(true);
        setAutomaticRentDate(); // Sets the rent date to now so that it is available in the object, to be changed later
        setAutomaticReturnDate(); // Sets the return date to now so that it is available in the object, to be changed later

        System.out.print("You have added game: ");
        g.printGame();
        gameList.add(g);

    }
}
