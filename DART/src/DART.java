import java.util.Scanner;

public class DART {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        boolean programRunning = true;
        while(programRunning) {
            userStoryPrint11(); //Prints the question to User Story 1.1, I do this in a second function because I don't want to see a wall of text here
            char mainMenu; //Initializes a char variable that the user uses to choose who he is, Manager, Employee, Customer or to exit the system
            String correctAnswers = "MECX"; //A string that contains all of the menu choices
            mainMenu = input.next().charAt(0); //Scanner input to a char variable
            screenChoice(validateChar(mainMenu, correctAnswers));
        }
    }

    public static void screenChoice(char x){
        switch (x) {
            case 'M' -> {
                if (password("admin1234"))
                    managerScreen();
            }
            case 'E' -> {
                if (password("password123"))
                    employeeScreen();
            }
            case 'C' -> customerScreen();
            case 'X' -> exitProgram();
        }
    }

    public static char customerScreen(){
        Scanner inputCustomer = new Scanner(System.in);
        char choice = '1';
        String screens = "123";
        System.out.println("Customer Screen - Type one of the options below:");
        System.out.println("1. Rent a game");
        System.out.println("2. Return a game");
        System.out.println("3. Return to Main Menu");
        System.out.println(choice);
        choice = inputCustomer.nextLine().charAt(0);
        validateChar(choice, screens);
        return choice;
    }

    public static char validateChar(char x, String correct){ //Creates a function that has the purpose to check if the input is correct
        Scanner valChar = new Scanner(System.in); //Creates a new Scanner, which is only used inside of this function
        boolean loop = true;
        while(loop) {
            for (int i = 0; i < correct.length(); i++) { //A for loop that loops the same amount of times as the length of the correct(correctAnswers) string
                if (x == correct.charAt(i)) { //An if statement that is used to check whether the character x(mainMenu) is in the correct(correctAnswers) string
                    i=correct.length(); //Exits the while loop @ loop1
                    loop = false; //Stops the while from going any more times
                }
            }
            if(loop){
                System.out.println("Invalid input, try again.");
                x = valChar.next().charAt(0); //Makes the user input their menu choice again, if the earlier choice wasn't a valid character
            }
        }
        return x; //Returns a char from the function
    }

    public static void userStoryPrint11(){ //Wall of text function
        System.out.println("Main Menu:");
        System.out.println("Welcome to DART, your good old game rental system. The competition has no steam to keep up!");
        System.out.println("Please specify your role by entering one of the options given:");
        System.out.println("1. Enter “M” for Manager");
        System.out.println("2. Enter “E” for Employee");
        System.out.println("3. Enter “C” for Customer");
        System.out.println("4. Enter “X” to exit system ");
    }

    public static char managerScreen(){
        Scanner inputManager = new Scanner(System.in);
        char choice;
        String screens = "123";
        System.out.println("Manager Screen - Type one of the options below:");
        System.out.println("1. Add an employee");
        System.out.println("2. View all employees");
        System.out.println("3. Return to Main Menu");
        choice = inputManager.next().charAt(0);
        validateChar(choice, screens);
        return choice;
    }

    public static boolean password(String correctPassword){
        boolean correct = false;
        System.out.println("Enter password: ");
        Scanner input = new Scanner(System.in);
        String password = input.nextLine();
        if (password.equals(correctPassword)) correct = true;
        return correct;
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
        validateChar(choice, screens);
        return choice;
    }

    public static void exitProgram(){
        System.exit(0);
    }
}