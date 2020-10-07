import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Employee {

    private String ID; //creates String variable named 'ID'
    private String name; //creates String variable named 'name'

    Employee(String name){ this.name = name; } //Creates a constructor with takes a name

    public void setID(){ ID = Tools.randomizeID(); } // a method that assigns a random id to the variable 'ID'
    public String getID(){ return ID; } // returns the id created by the method above


    public static void registerCustomer(){ // method that registers a customer
        Scanner input = new Scanner(System.in);
        System.out.println("Creating customer. Please type customer's: \n Name: "); Employee c = new Employee(input.nextLine());
        // prints two lines, and creates a Customer object 'c' with an input
        c.setID(); System.out.println("Random ID <" + c.getID() + "> was assigned."); // sets a random id & then prints it
        System.out.println("You have added customer: ");
        c.printCustomer(); // prints all information about the customer created
        customerList.add(c); // adds the object 'c' to the ArrayList
        Screens.employeeScreen(); // returns the user to the employee screen
    }
    public static void viewAllCustomer(){
        customerList.forEach(Employee::printCustomer); Screens.employeeScreen(); } // for each Customer in the 'customerList', it will execute printCustomer

    public void printCustomer(){ System.out.print(getID() + " : " + this.name); } // prints ID of the customer and then the name

    public static void removeCustomer(){ // method that removes customers
        Scanner input = new Scanner(System.in);
        boolean removed = false; // declares a variable that will decide if the following for loop will continue looping
        int i; // initializes a variable 'i' so it can be used outside of the for loop
        System.out.println("Games: ");
        customerList.forEach(Employee::printCustomer); // prints all of the customers, so it will be easier for the user to see which customer to remove
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

}
