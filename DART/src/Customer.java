import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer {

    private String ID; //creates String variable named 'ID'
    private final String name; //creates String variable named 'name'
    private static ArrayList<Games> customerGames = new ArrayList(); //creates ArrayList named 'customerGames' containing Games
    private static ArrayList<Customer> customerList = new ArrayList(); //creates ArrayList named 'customerList' containing Games

    Customer(String name){ this.name = name; } //Creates a constructor with takes a name

    public void setID(){ ID = randomID.randomizeID(); } // a method that assigns a random id to the variable 'ID'
    public String getID(){ return ID; } // returns the id created by the method above

    public static void addGame(Games game){ customerGames.add(game); } // a method that adds an object game to the ArrayList 'customerGames'
    public static void removeGame(Games game){ customerGames.remove(game); } // a method that removes an object game to the ArrayList 'customerGames'

    public static void registerCustomer(){ // method that registers a customer
        Scanner input = new Scanner(System.in);
        System.out.println("Creating customer. Please type customer's: \n Name: "); Customer c = new Customer(input.nextLine());
        // prints two lines, and creates a Customer object 'c' with an input
        c.setID(); System.out.println("Random ID <" + c.getID() + "> was assigned."); // sets a random id & then prints it
        System.out.println("You have added customer: ");
        c.printCustomer(); // prints all information about the customer created
        customerList.add(c); // adds the object 'c' to the ArrayList
        Screens.employeeScreen(); // returns the user to the employee screen
    }
    public static void viewAllCustomer(){ customerList.forEach(Customer::printCustomer);Screens.employeeScreen(); } // for each Customer in the 'customerList', it will execute printCustomer

    public void printCustomer(){ System.out.print(getID() + " : " + this.name); } // prints ID of the customer and then the name

    public static void removeCustomer(){ // method that removes customers
        Scanner input = new Scanner(System.in);
        boolean removed = false; // declares a variable that will decide if the following for loop will continue looping
        int i; // initializes a variable 'i' so it can be used outside of the for loop
        System.out.println("Games: ");
        customerList.forEach(Customer::printCustomer); // prints all of the customers, so it will be easier for the user to see which customer to remove
        System.out.print("Enter the ID of the customer you want to remove: ");

        String removeID = input.nextLine(); // declares a String variable, which is used to remove customers
        for(i = 0; i < customerList.size(); i++){ // for loop that loops the same amount of times as the amount of objects in the customerList
            if(customerList.get(i).ID.equals(removeID)){ // if the customer 'ID' in the customerList is the same as 'removeID' inputted by the user earlier
                customerList.remove(i); // removes user from the customerList
                System.out.println("Successfully removed!");
                removed = true;
                i = customerList.size(); // sets variable 'i' to the same size as customerList, which will make the for loop not loop again
            }
        }
        if(!removed) System.out.println("Customer with id " + i + " not found.");

    }

    public static void rentGame(){ // method to rent a game, by adding a game to a customers list
        Scanner input = new Scanner(System.in);
        System.out.println("Current game library: ");
        Games.viewAllGames(); // executes viewAllGames from class Games
        System.out.println("What game do you want to rent? ");

        String rentID = input.nextLine(); //Declares a String variable, which is used to remove customers
        for(int i = 0; i < Games.gameList.size(); i++){
            if(Games.gameList.get(i).getID().equals(rentID)){
                if(Games.gameList.get(i).getStatus()) { // if the game is available
                    addGame(Games.gameList.get(i));
                    Games.gameList.get(i).setStatus(false); // makes the game no longer available from the stores game library
                    Games.setRentDate();  // executes setRentDate from class Games
                    System.out.println("Successfully rented");
                    i = Games.gameList.size();
                } else {
                    System.out.println("Game is not available");
                }
            }
        }
    }

    public static void viewAllRentedGames() { customerGames.forEach(Games::printGame); } // method that prints all the customers games | for each Games in the customerGames list, it will print the game

    public static long calcDays(int i){ // a method that returns days between game was rented and returned
        return ChronoUnit.DAYS.between(Games.gameList.get(i).getRentDate(), // Calculates & returns the day difference
                Games.gameList.get(i).getReturnDate());
    }

    // Chronounit: https://stackoverflow.com/questions/27005861/calculate-days-between-two-dates-in-java-8

    public static double returnGame(){ // a method to return games to the store
        double rent; //initializes a variable local to the method
        Scanner input = new Scanner(System.in);
        System.out.println("Current game library: ");
        viewAllRentedGames(); // prints all the games rented by the customer
        System.out.println("What game do you want to return? ");

        String rentID = input.nextLine();
        for(int i = 0; i < Games.gameList.size(); i++){
            if(Games.gameList.get(i).getID().equals(rentID)){
                if(!Games.gameList.get(i).getStatus()) {
                    removeGame(Games.gameList.get(i));
                    Games.gameList.get(i).setStatus(true);
                    Games.setReturnDate();
                    System.out.println("Successfully returned.");
                    rent = Games.gameList.get(i).getDailyRent() * calcDays(i); // sets the variable 'rent' to the daily rent multiplied by the amount of days
                    System.out.println("The cost for renting the game for " + calcDays(i) + " days is: " + rent);
                    Screens.employeeScreen();
                    return rent; // returns the rent cost
                } else System.out.println("Game is not available");
            }
        }
        Screens.employeeScreen();
        return 0;
    }
}
