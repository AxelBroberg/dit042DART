import java.util.Scanner;
public class src_Bread {

    public static void main(String[] args) {
    Scanner input =new Scanner(System.in);

        System.out.println("Main Menu:");
        System.out.println("Welcome to DART, your good old game rental system. The competition has no steam to keep up!");

        System.out.println("");
        System.out.println("Please specify your role by entering one of the options given:");

        System.out.println("1. Enter “M” for Manager");
        System.out.println("2. Enter “E” for Employee");
        System.out.println("3. Enter “C” for Customer");
        System.out.println("4. Enter “X” to exit system");

        String roleOption = input.nextLine();

            //while (roleOption != "K"||"M"||"C"||"X") {
                /*System.out.println("Please specify your role by entering one of the options given:");

                System.out.println("1. Enter “M” for Manager");
                System.out.println("2. Enter “E” for Employee");
                System.out.println("3. Enter “C” for Customer");
                System.out.println("4. Enter “X” to exit system");
                roleOption = input.nextLine();

                 */

                if (roleOption.equalsIgnoreCase("M")) {
                    System.out.println("Manager Screen - Type one of the options below:");

                    System.out.println("1. Add an employee");


                    System.out.println("2. View all employees");


                    System.out.println("3. Return to Main Menu");
                    //break;
                } else if (roleOption.equalsIgnoreCase("E")) {
                    System.out.println("Employee Screen - Type one of the options below:");
                    System.out.println("1. Register a game");


                    System.out.println("2. Remove a game");


                    System.out.println("3. Register a customer");


                    System.out.println("4. Remove a customer");


                    System.out.println("5. Show total rent profit");


                    System.out.println("6. View all games");


                    System.out.println("7. Return to Main Menu");
                    //break;
                } else if (roleOption.equalsIgnoreCase("C")) {
                    System.out.println("Customer Screen - Type one of the options below:");

                    System.out.println("1. Rent a game");


                    System.out.println("2. Return a game");


                    System.out.println("3. Return to Main Menu");
                   // break;
                } else if (roleOption.equalsIgnoreCase("X")) {
                    System.exit(0);
                  //  break;

                } else {

                    System.out.println("You have entered a wrong letter , Please try again");
                }
            //}

    input.close();
    }
}