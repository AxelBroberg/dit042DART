import java.util.Scanner;


public class Screens {
    public static char customerScreen(){
        Scanner inputCustomer = new Scanner(System.in);
        char choice = '1';
        String screens = "123";
        System.out.println("Customer Screen - Type one of the options below:");
        System.out.println("1. Rent a game");
        System.out.println("2. Return a game");
        System.out.println("3. Return to Main Menu");
        choice = inputCustomer.nextLine().charAt(0);
        DART.validateChar(choice, screens);
        return choice;
    }

    public static char managerScreen(){
        Scanner inputManager = new Scanner(System.in);
        char choice;
        String screens = "1234";
        System.out.println("Manager Screen - Type one of the options below:");
        System.out.println("1. Add an employee");
        System.out.println("2. View all employees");
        System.out.println("3. Remove an employee");
        System.out.println("4. Return to Main Menu");
        choice = inputManager.next().charAt(0);
        DART.validateChar(choice, screens);
        switch (choice) {
            case '1' -> Employee.registerEmployee();
            case '2' -> Employee.viewAllEmployee();
            case '3' -> Employee.removeEmployee();
            case '4' -> {
                return choice;
            }
        }
        return choice;
    }

    public static char employeeScreen(){
        Scanner inputEmployee = new Scanner(System.in);
        char choice;
        String screens = "1234567";
        System.out.println("Employee Screen - Type one of the options below:");
        System.out.println("1. Register a game");
        System.out.println("2. Remove a game");
        System.out.println("3. Register a customer");
        System.out.println("4. Remove a customer");
        System.out.println("5. Show total rent profit");
        System.out.println("6. View all games");
        System.out.println("7. Return to Main Menu");
        choice = inputEmployee.next().charAt(0);
        DART.validateChar(choice, screens);
        return choice;
    }
}
