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
            case 'c','C' -> preCustomerScreen();
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

    public static void preCustomerScreen(){
        String ID = Tools.getString("Please enter your ID");
        for(int i = 0; i < EmployeeController.customerList.size(); i++){
            if(EmployeeController.customerList.get(i).getID().equals(ID)){
                customerScreen(ID);
            }
            if(i == EmployeeController.customerList.size()){
                System.out.println("Invalid ID. Sending you back to the main menu");
                mainMenu();
            }
        }
    }

    public static void customerScreen(String ID){
        Scanner inputCustomer = new Scanner(System.in);
        char choice;
        String screens = "123456";
        System.out.println("Customer Screen - Type one of the options below:");
        System.out.println("1. Rent a game");
        System.out.println("2. Return a game");
        System.out.println("3. Rent a song");
        System.out.println("4. Return a song");
        System.out.println("5. Request membership upgrade");
        System.out.println("6. Return to Main Menu");
        choice = inputCustomer.nextLine().charAt(0);
        Tools.validateChar(choice, screens);
        switch(choice) {
            case '1' -> CustomerController.rentItem("Game", ID);
            case '2' -> totalProfit += CustomerController.returnItem("Game", ID);
            case '3' -> CustomerController.rentItem("Song", ID);
            case '4' -> totalProfit += CustomerController.returnItem("Song", ID);
            case '5' -> CustomerController.requestUpgrade(ID);
            case '6' -> {
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
            case '2' -> ManagerController.viewAllEmployee(true);
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
        String screens = "1234567890a";
        System.out.println("Employee Screen - Type one of the options below:");
        System.out.println("1. Register a game");
        System.out.println("2. Remove a game");
        System.out.println("3. Register a customer");
        System.out.println("4. Remove a customer");
        System.out.println("5. Show total rent profit");
        System.out.println("6. View all games");
        System.out.println("7. View all customers");
        System.out.println("8. Fill games");
        System.out.println("9. View upgrade requests");
        System.out.println("a. Upgrade member");
        System.out.println("0. Return to Main Menu");
        choice = inputEmployee.next().charAt(0);
        Tools.validateChar(choice, screens);
        switch (choice) {
            case '1' -> EmployeeController.registerGame();
            case '2' -> EmployeeController.removeGame();
            case '3' -> EmployeeController.registerCustomer();
            case '4' -> EmployeeController.removeCustomer();
            case '5' -> {System.out.println("Total profit is: " + totalProfit); employeeScreen();}
            case '6' -> GameController.empViewAllGames();
            case '7' -> EmployeeController.viewAllCustomer();
            case '8' -> EmployeeController.fillGames();
            case '9' -> EmployeeController.viewAllUpgRequest();
            case 'a' -> EmployeeController.upgradeCustomer();
            case '0' -> {
            }
        }
    }
}
