import java.util.Scanner;

public class Screens {
    private static double totalProfit;

    public static void mainMenu(){ //Wall of text function
        System.out.println("Main Menu:");
        System.out.println("Welcome to DART, your good old game rental system. The competition has no steam to keep up!");
        System.out.println("Please specify your role by entering one of the options given:");
        System.out.println("1. Enter “M” for Manager");
        System.out.println("2. Enter “E” for Employee");
        System.out.println("3. Enter “C” for Customer");
        System.out.println("4. Enter “X” to exit system ");
    }

    public static void customerScreen(){
        Scanner inputCustomer = new Scanner(System.in);
        char choice;
        String screens = "123";
        System.out.println("Customer Screen - Type one of the options below:");
        System.out.println("1. Rent a game");
        System.out.println("2. Return a game");
        System.out.println("3. Return to Main Menu");
        choice = inputCustomer.nextLine().charAt(0);
        Bread.validateChar(choice, screens);
        switch(choice) {
            case '1' -> Customer.rentGame();
            case '2' -> totalProfit += Customer.returnGame();
            case '3' -> {
            }
        }
    }
    public static void managerScreen(){
        Scanner inputManager = new Scanner(System.in);
        char choice;
        String screens = "123456";
        System.out.println("Manager Screen - Type one of the options below:");
        System.out.println("1. Add an employee");
        System.out.println("2. View all employees");
        System.out.println("3. Remove an employee");
        System.out.println("4. Calculate net salary of employee");
        System.out.println("5. Calculate and give bonus to employee");
        System.out.println("6. Return to Main Menu");
        choice = inputManager.next().charAt(0);
        Bread.validateChar(choice, screens);
        switch (choice) {
            case '1' -> Employee.registerEmployee();
            case '2' -> Employee.viewAllEmployee();
            case '3' -> Employee.removeEmployee();
            case '4' -> Employee.calcNetSalary();
            case '5' -> Employee.bonus();
            case '6' -> {
            }
        }
    }

    public static void employeeScreen(){
        Scanner inputEmployee = new Scanner(System.in);
        char choice;
        String screens = "123456789";
        System.out.println("Employee Screen - Type one of the options below:");
        System.out.println("1. Register a game");
        System.out.println("2. Remove a game");
        System.out.println("3. Register a customer");
        System.out.println("4. Remove a customer");
        System.out.println("5. Show total rent profit");
        System.out.println("6. View all games");
        System.out.println("7. View all customers");
        System.out.println("8. Fill games");
        System.out.println("9. Return to Main Menu");
        choice = inputEmployee.next().charAt(0);
        Bread.validateChar(choice, screens);
        switch (choice) {
            case '1' -> Games.registerGame();
            case '2' -> Games.removeGame();
            case '3' -> Customer.registerCustomer();
            case '4' -> Customer.removeCustomer();
            case '5' -> System.out.println("Total profit is: " + totalProfit);
            case '6' -> Games.empViewAllGames();
            case '7' -> Customer.viewAllCustomer();
            case '8' -> Games.fillGames();
            case '9' -> {
            }
        }
    }
}
