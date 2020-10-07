import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeController {
    static ArrayList<Employee> customerList = new ArrayList(); //creates ArrayList named 'customerList' containing Customers

    public static void registerCustomer(){ // method that registers a customer
        Scanner input = new Scanner(System.in);
        System.out.println("Creating customer. Please type customer's: \n Name: "); Employee c = new Employee(input.nextLine());
        // prints two lines, and creates a Customer object 'c' with an input
        c.setID(); System.out.println("Random ID <" + c.getID() + "> was assigned."); // sets a random id & then prints it
        System.out.println("You have added customer: ");
        c.toString(); // prints all information about the customer created
        customerList.add(c); // adds the object 'c' to the ArrayList
        Screens.employeeScreen(); // returns the user to the employee screen
}

    public static void viewAllCustomer(){
        customerList.forEach(Employee::toString); Screens.employeeScreen(); } // for each Customer in the 'customerList', it will execute printCustomer

    public static void removeCustomer(){ // method that removes customers
        Scanner input = new Scanner(System.in);
        boolean removed = false; // declares a variable that will decide if the following for loop will continue looping
        int i; // initializes a variable 'i' so it can be used outside of the for loop
        System.out.println("Games: ");
        customerList.forEach(Employee::toString); // prints all of the customers, so it will be easier for the user to see which customer to remove
        System.out.print("Enter the ID of the customer you want to remove: ");

        String removeID = input.nextLine(); // declares a String variable, which is used to remove customers
        for(i = 0; i < customerList.size(); i++){ // for loop that loops the same amount of times as the amount of objects in the customerList
            if(customerList.get(i).getID().equals(removeID)){ // if the customer 'ID' in the customerList is the same as 'removeID' inputted by the user earlier
                customerList.remove(i); // removes user from the customerList
                System.out.println("Successfully removed!");
                removed = true;
                i = customerList.size(); // sets variable 'i' to the same size as customerList, which will make the for loop not loop again
            }
        }
        if(!removed) System.out.println("Customer with id " + i + " not found.");

    }
    public static void registerGame(){

        Game g = new Game(Tools.getString("Enter game title: "), Tools.getString("Enter game genre: "), Tools.getDouble("Enter daily rent: ")); // OBJECT CREATION


        System.out.print("You have added game: ");
        g.toString();
        GameController.gameList.add(g);
        Screens.employeeScreen();
    }
    public static void autoRegisterGame(String title, String genre, double price){ // Method for adding games, used for testing purposes
        Game g = new Game(title, genre, price);


        System.out.print("You have added game: ");
        g.toString();
        GameController.gameList.add(g);

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

    public static void removeGame(){
        Scanner input = new Scanner(System.in);
        boolean removed = false;
        int i;
        System.out.println("Games: ");
        GameController.gameList.forEach(Game::toString);


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
    }

}
