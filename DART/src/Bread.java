import java.util.Scanner;
public class Bread {

    static boolean programRunning = true;
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        while(programRunning) {
            Screens.mainMenu(); //Prints the question to User Story 1.1, I do this in a second function because I don't want to see a wall of text here
            char mainMenu; //Initializes a char variable that the user uses to choose who he is, Manager, Employee, Customer or to exit the system
            String correctAnswers = "MECXmecx"; //A string that contains all of the menu choices
            mainMenu = input.next().charAt(0); //Scanner input to a char variable
            screenChoice(validateChar(mainMenu, correctAnswers));
        }
    }
    public static void screenChoice(char x){
        switch (x) {
            case 'm','M' -> {
                if (password("admin1234")) Screens.managerScreen();
            }
            case 'e','E' -> {
                if (password("password123")) Screens.employeeScreen();
            }
            case 'c','C' -> Screens.customerScreen();
            case 'x','X' -> exitProgram();
        }
    }
    public static char validateChar(char x, String correct){ //Creates a function that has the purpose to check if the input is correct
        Scanner valChar = new Scanner(System.in); //Creates a new Scanner, which is only used inside of this function
        boolean programRunning = true; //Used so we can exit the entire while loop
        while(programRunning) {
            for (int i = 0; i < correct.length(); i++) { //A for loop that loops the same amount of times as the length of the correct(correctAnswers) string
                if (x == correct.charAt(i)) { //An if statement that is used to check whether the character x(mainMenu) is in the correct(correctAnswers) string
                    programRunning = false;
                    return x;
                }
            }
            if(programRunning) {
                System.out.println("Invalid input, try again.");
                x = valChar.next().charAt(0); //Makes the user input their menu choice again, if the earlier choice wasn't a valid character
            }
        }
        return x; //Returns a char from the function
    }
    public static boolean password(String correctPassword){
        boolean correct = false;
        System.out.println("Enter password: ");
        Scanner input = new Scanner(System.in);
        String password = input.nextLine();
        if (password.equals(correctPassword)) correct = true;
        return correct;
    }
    public static void exitProgram(){
        System.exit(0);
        programRunning = false;
    }
}