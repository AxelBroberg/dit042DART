import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer {

    private String ID;
    private String name;
    private static double rent;
    private static ArrayList<Games> customerGames = new ArrayList();
    private static ArrayList<Customer> customerList = new ArrayList();

    Customer(String name){ this.name = name; }

    public void setID(){ ID = randomID.randomizeID(); }
    public String getID(){ return ID; }

    public static void addGame(Games game){ customerGames.add(game); }
    public static void removeGame(Games game){ customerGames.remove(game); }

    public static void registerCustomer(){
        Scanner input = new Scanner(System.in);
        System.out.println("Creating customer. Please type customer's: \n Name: "); Customer c = new Customer(input.nextLine());
        c.setID(); System.out.println("Random ID <" + c.getID() + "> was assigned.");

        System.out.println("You have added customer: ");
        c.printCustomer();
        customerList.add(c);
        Screens.employeeScreen();
    }



    public static void viewAllCustomer(){ customerList.forEach(Customer::printCustomer); }

    public void printCustomer(){ System.out.print(getID() + " : " + this.name); }

    public static void removeCustomer(){
        Scanner input = new Scanner(System.in);
        boolean removed = false;
        int i;
        System.out.println("Games: ");
        customerList.forEach(Customer::printCustomer);
        System.out.print("Enter the ID of the customer you want to remove: ");

        String removeID = input.nextLine();
        for(i = 0; i < customerList.size(); i++){
            if(customerList.get(i).ID.equals(removeID)){
                customerList.remove(i);
                System.out.println("Successfully removed!");
                removed = true;
                i = customerList.size();
            }
        }
        if(!removed) System.out.println("Customer with id " + i + " not found.");

    }

    public static void rentGame(){
        Scanner input = new Scanner(System.in);
        System.out.println("Current game library: ");
        Games.viewAllGames();
        System.out.println("What game do you want to rent? ");

        String rentID = input.nextLine();
        for(int i = 0; i < Games.gameList.size(); i++){
            if(Games.gameList.get(i).ID.equals(rentID)){
                if(Games.gameList.get(i).status) {
                    addGame(Games.gameList.get(i));
                    Games.gameList.get(i).setStatus(false);
                    Games.setRentDate();
                    System.out.println("Successfully rented");
                    i = Games.gameList.size();
                } else {
                    System.out.println("Game is not available");
                }
            }
        }
    }

    public static void viewAllRentedGames() { customerGames.forEach(Games::printGame); }


    public static double returnGame(){
        Scanner input = new Scanner(System.in);
        //int daysGamed = input.nextInt();

        System.out.println("Current game library: ");
        viewAllRentedGames();
        System.out.println("What game do you want to return? ");
        String rentID = input.nextLine();

        for(int i = 0; i < Games.gameList.size(); i++){
            if(Games.gameList.get(i).ID.equals(rentID)){
                if(!Games.gameList.get(i).status) {
                    removeGame(Games.gameList.get(i));
                    Games.gameList.get(i).setStatus(true);
                    Games.setReturnDate();
                    System.out.println("Successfully returned.");
                    rent = Games.gameList.get(i).getDailyRent()*(ChronoUnit.DAYS.between(Games.gameList.get(i).getRentDate(), Games.gameList.get(i).getReturnDate()));
                    System.out.println("The cost for renting the game for " + ChronoUnit.DAYS.between(Games.gameList.get(i).getRentDate(), Games.gameList.get(i).getReturnDate()) + " days is: " + rent);
                    return rent;
                } else System.out.println("Game is not available");
            }
        }
        return 0;
    }
}
