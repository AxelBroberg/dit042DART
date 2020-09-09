import java.util.Scanner;

public class DART {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        userStoryPrint11(); //Prints the question to User Story 1.1, I do this in a second function because I don't want to see a wall of text here
        char mainMenu; //Initializes a char variable that the user uses to choose who he is, Manager, Employee, Customer or to exit the system
        String correctAnswers = "MECX"; //A string that contains all of the menu choices
        mainMenu = input.next().charAt(0); //Scanner input to a char variable
        validateChar(mainMenu, correctAnswers); //Calls the function validateChar
        System.out.println("Success!"); //For testing purposes
    }

    public static char validateChar(char x, String correct){ //Creates a function that has the purpose to check if the input is correct
        Scanner valChar = new Scanner(System.in); //Creates a new Scanner, which is only used inside of this function
        loop1: //Used so we can exit the entire while loop
        while(true) {
            for (int i = 0; i < correct.length(); i++) { //A for loop that loops the same amount of times as the length of the correct(correctAnswers) string
                if (x == correct.charAt(i)) { //An if statement that is used to check whether the character x(mainMenu) is in the correct(correctAnswers) string
                    break loop1; //Exits the while loop @ loop1
                }
            }
            System.out.println("Invalid input, try again.");
            x = valChar.next().charAt(0); //Makes the user input their menu choice again, if the earlier choice wasn't a valid character
        }
        valChar.close(); //Closes the scanner
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
}