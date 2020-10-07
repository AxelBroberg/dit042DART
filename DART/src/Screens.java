import java.util.Scanner;

public class Screens {
    private static double totalProfit;
    Scanner input = new Scanner(System.in);

    public static void screenChoice(char x){
        switch (x) {
            case 'm','M' -> {
                if (Tools.password("admin1234")) managerScreen();
            }
            case 'e','E' -> {
                if (Tools.password("password123")) employeeScreen();
            }
            case 'c','C' -> customerScreen();
            case 'x','X' -> DartMain.exitProgram();
        }
    }

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
        Tools.validateChar(choice, screens);
        switch(choice) {
            case '1' -> Customer.rentGame();
            case '2' -> totalProfit += Customer.returnGame();
            case '3' -> {
            }
        }
    }

    public static void managerScreen(){
        String screens = "123456";
        System.out.println("Manager Screen - Type one of the options below:");
        System.out.println("1. Add an employee");
        System.out.println("2. View all employees");
        System.out.println("3. Remove an employee");
        System.out.println("4. Calculate net salary of employee");
        System.out.println("5. Calculate and give bonus to employee");
        System.out.println("6. Return to Main Menu");
        char choice = Tools.getChar("");
        Tools.validateChar(choice, screens);
        switch (choice) {
            case '1' -> ManagerController.registerEmployee();
            case '2' -> ManagerController.viewAllEmployee();
            case '3' -> ManagerController.removeEmployee();
            case '4' -> ManagerController.calcNetSalary();
            case '5' -> ManagerController.bonus();
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
        Tools.validateChar(choice, screens);
        switch (choice) {
            case '1' -> Game.registerGame();
            case '2' -> Game.removeGame();
            case '3' -> Employee.registerCustomer();
            case '4' -> Employee.removeCustomer();
            case '5' -> System.out.println("Total profit is: " + totalProfit);
            case '6' -> Game.empViewAllGames();
            case '7' -> Employee.viewAllCustomer();
            case '8' -> Game.fillGames();
            case '9' -> {
            }
        }
    }
}
