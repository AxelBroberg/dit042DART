import java.util.ArrayList;
import java.util.Scanner;
public class DartMain {


    static boolean programRunning = true;
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        while(programRunning) {
            Screens.mainMenu(); //Prints the question to User Story 1.1, I do this in a second function because I don't want to see a wall of text here
            char mainMenu; //Initializes a char variable that the user uses to choose who he is, Manager, Employee, Customer or to exit the system
            String correctAnswers = "MECXmecx"; //A string that contains all of the menu choices
            mainMenu = input.next().charAt(0); //Scanner input to a char variable
            Screens.screenChoice(Tools.validateChar(mainMenu, correctAnswers));
        }
    }




    public static void exitProgram(){
        System.exit(0);
    }
}