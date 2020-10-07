import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    Scanner input = new Scanner(System.in);

    static ArrayList<Employee> customerList = new ArrayList(); //creates ArrayList named 'customerList' containing Customers
    static ArrayList<Manager> employeeArrayList = new ArrayList();
    static ArrayList<Game> gameList = new ArrayList();

    public void screenChoice(char x){
        switch (x) {
            case 'm','M' -> {
                if (Tools.password("admin1234")) managerScreen(input);
            }
            case 'e','E' -> {
                if (Tools.password("password123")) Screens.employeeScreen();
            }
            case 'c','C' -> Screens.customerScreen();
            case 'x','X' -> DartMain.exitProgram();
        }
    }

    public static void managerScreen(Scanner input){
        char choice;
        String screens = "123456";
        System.out.println("Manager Screen - Type one of the options below:");
        System.out.println("1. Add an employee");
        System.out.println("2. View all employees");
        System.out.println("3. Remove an employee");
        System.out.println("4. Calculate net salary of employee");
        System.out.println("5. Calculate and give bonus to employee");
        System.out.println("6. Return to Main Menu");
        choice = input.next().charAt(0);
        Tools.validateChar(choice, screens);
        switch (choice) {
            case '1' -> Manager.registerEmployee(input);
            case '2' -> Manager.viewAllEmployee();
            case '3' -> Manager.removeEmployee();
            case '4' -> Manager.calcNetSalary();
            case '5' -> Manager.bonus();
            case '6' -> {
            }
        }
    }




}
