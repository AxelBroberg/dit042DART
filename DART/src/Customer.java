import java.util.ArrayList;
import java.util.Scanner;

public class Customer {

    int ID;
    String name;
    static ArrayList<Customer> customerList = new ArrayList();

    Customer(String name){ this.name = name; };

    public void setID(int ID){ this.ID = ID; }
    public int getID(){ return ID; }

    public static void registerCustomer(){
        Scanner input = new Scanner(System.in);
        System.out.println("Creating customer. Please type customer's: /n Name: "); Customer c = new Customer(input.nextLine());
        System.out.println("ID: "); c.setID(input.nextInt());

        System.out.println("You have added customer: ");
        c.printCustomer();
        customerList.add(c);
        Screens.employeeScreen();
    }

    public static void viewAllCustomer(){
        customerList.forEach(Customer::printCustomer);
    }

    public void printCustomer(){
        System.out.print(getID() + " : " + this.name);
    }

    public static void removeCustomer(){
        Scanner input = new Scanner(System.in);
        boolean removed = false;
        int i;
        System.out.println("Games: ");
        customerList.forEach(Customer::printCustomer);
        System.out.print("Enter the ID of the customer you want to remove: ");

        int removeID = input.nextInt();
        for(i = 0; i < customerList.size(); i++){
            if(customerList.get(i).ID == removeID){
                customerList.remove(i);
                System.out.println("Successfully removed!");
                removed = true;
                i = customerList.size();
            }
        }
        if(!removed){
            System.out.println("Customer with id " + i + " not found.");
        }
    }


    public static void rentGame(){

        System.out.println("Current game library: ");
        Games.viewAllGames();
        System.out.println("What game do you want to rent? ");


    }




   /* As a user, I want games to show and change their current status to be able to see if a game is available at the current time.
    To implement the game renting feature you have to allow the users to see the games and their current status

    to choose based on the game’s ID. Upon renting a game, its rent status is changed to not available.
    If the specified game ID does not exist, the following message should be shown: “Game with id <ID> not found”.
    If the specified game ID is already rented, the following message should be shown: “Game with id <ID> is already rented”.

*/
}
