import java.util.Scanner;

public class DART {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.println("Main Menu:");
        System.out.println("Welcome to DART, your good old game rental system. The competition has no steam to keep up!");
        System.out.println("Please specify your role by entering one of the options given:");
        System.out.println("1. Enter “M” for Manager");
        System.out.println("2. Enter “E” for Employee");
        System.out.println("3. Enter “C” for Customer");
        System.out.println("4. Enter “X” to exit system ");
        char mainMenu;
        String correctAnswers = "MECX";
        mainMenu = input.next().charAt(0);
        validateChar(mainMenu, correctAnswers);
        System.out.println("Success!");
    }

    public static char validateChar(char x, String correct){
        Scanner valChar = new Scanner(System.in);
        loop1:
        while(true) {
            for (int i = 0; i < correct.length(); i++) {
                if (x == correct.charAt(i)) {
                    break loop1;
                }
            }
            System.out.println("Invalid input, try again.");
            x = valChar.next().charAt(0);
        }
        valChar.close();
        return x;
    }
}