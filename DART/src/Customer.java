import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer {


    public static ArrayList<Game> customerGames = new ArrayList(); //creates ArrayList named 'customerGames' containing Games


    public static void addGame(Game game){ customerGames.add(game); } // a method that adds an object game to the ArrayList 'customerGames'
    public static void removeGame(Game game){ customerGames.remove(game); } // a method that removes an object game to the ArrayList 'customerGames'

    public static void rentGame(){ // method to rent a game, by adding a game to a customers list
        Scanner input = new Scanner(System.in);
        System.out.println("Current game library: ");
        Game.viewAllGames(); // executes viewAllGames from class Games
        System.out.println("What game do you want to rent? ");

        String rentID = input.nextLine(); //Declares a String variable, which is used to remove customers
        for( int i = 0; i < Game.gameList.size(); i++){
            if(Game.gameList.get(i).getID().equals(rentID)){
                if(Game.gameList.get(i).getStatus()) { // if the game is available
                    addGame(Game.gameList.get(i));
                    Game.gameList.get(i).setStatus(false); // makes the game no longer available from the stores game library



                    try {
                        System.out.print("What is the return date? (YYYY-MM-DD)");
                        Game.gameList.get(i).setRentDate(input.nextLine());
                    } catch (Exception e) {
                        System.out.println("Wrong format, assuming return date is today.");
                        Game.gameList.get(i).setAutomaticRentDate();
                    }


                    System.out.println("Successfully rented");
                    i = Game.gameList.size();
                } else {
                    System.out.println("Game is not available");
                }
            }
        }
        Screens.customerScreen();
    }

    public static void viewAllRentedGames() { customerGames.forEach(Game::printGame); } // method that prints all the customers games | for each Games in the customerGames list, it will print the game

    public static long calcDays(int i){ // a method that returns days between game was rented and returned
        return ChronoUnit.DAYS.between(Game.gameList.get(i).getRentDate(), Game.gameList.get(i).getReturnDate());
    }

    // Chronounit: https://stackoverflow.com/questions/27005861/calculate-days-between-two-dates-in-java-8

    public static double returnGame(){ // a method to return games to the store
        double rent; //initializes a variable local to the method
        Scanner input = new Scanner(System.in);
        System.out.println("Current game library: ");
        viewAllRentedGames(); // prints all the games rented by the customer
        System.out.println("What game do you want to return? ");

        String rentID = input.nextLine();
        for( int i = 0; i < Game.gameList.size(); i++){
            if(Game.gameList.get(i).getID().equals(rentID)){
                if(!Game.gameList.get(i).getStatus()) {
                    removeGame(Game.gameList.get(i));
                    Game.gameList.get(i).setStatus(true);


                    try {
                        System.out.print("What is the return date? (YYYY-MM-DD)");
                        Game.gameList.get(i).setReturnDate(input.nextLine());
                    } catch (Exception e) {
                        System.out.println("Wrong format, assuming return date is today.");
                        Game.gameList.get(i).setAutomaticReturnDate();
                    }


                    System.out.println("Successfully returned.");
                    rent = Game.gameList.get(i).getDailyRent() * calcDays(i); // sets the variable 'rent' to the daily rent multiplied by the amount of days
                    System.out.println("The cost for renting the game for " + calcDays(i) + " days is: " + rent);
                    Screens.employeeScreen();
                    return rent; // returns the rent cost
                } else System.out.println("Game is not available");
            }
        }
        Screens.customerScreen();
        return 0;
    }

}
