import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// ---------------------------------------Milestone 2-------------------------------------------
//
// We moved the essential methods that we had floating in our main to a separate Tools class
// validateChar & password were moved here from the main class
// The randomID class was all put inside of this class as well
//
// We created four getters for inputs here, getString, getChar, getInt & getDouble.
// These are used inside of the code instead of every input, so that it will be clearer where we have scanners
//
// ---------------------------------------Milestone 3-------------------------------------------
// In order to implement Epic feature 12 we modified the calcDays method to throw an exception

public class Tools {
    private static Scanner input = new Scanner(System.in);

    public static void exitProgram(){
        input.close();
        System.exit(0);
    }

    public static String getString(String message, Scanner input){
        System.out.println(message);
        String userInput = input.nextLine();
        return userInput;
    }

    public static char getChar(String message){
        System.out.println(message);
        char userInput = input.next().charAt(0);
        return userInput;
    }

    public static int getInt(String message){
        System.out.println(message);
        int userInput = input.nextInt();
        input.nextLine();
        return userInput;
    }

    public static double getDouble(String message){
        System.out.println(message);
        double userInput = input.nextDouble();
        input.nextLine();
        return userInput;
    }

    public static long calcDays(Rentable item){
        if(ChronoUnit.DAYS.between(item.getRentDate(), item.getReturnDate()) <= 0) {
            throw new EarlyDateException("Invalid operation. Upon returning an item, the number of days rented must be positive.");
        } else {
            return ChronoUnit.DAYS.between(item.getRentDate(), item.getReturnDate());
        }
    }

    // The UUID suggested for creating unique ID's creates a long string of numbers, letters and dashes.
    // Such as: "123e4567-e89b-12d3-a456-556642440000"
    // We found this difficult to read, and hard for the user to input by themselves.
    // Because of this we created a generator which creates a 6 character long id, which is unique.
    //
    // https://stackoverflow.com/questions/9543715/generating-human-readable-usable-short-but-unique-ids

    private static final ArrayList<String> IDCheck = new ArrayList<>();

    public static String randomizeID(){
        boolean loop = true;
        while(loop) {
            String ID = GetBase62(6);
            if (!IDCheck.contains(ID)) IDCheck.add(ID); loop = false;
        }
        return IDCheck.get(IDCheck.size() -1);
    }

    private static final char[] _base62chars ="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    private static final Random _random = new Random();

    public static String GetBase62(int length)
    {
        var sb = new StringBuilder(length);
        for (int i=0; i<length; i++)
            sb.append(_base62chars[_random.nextInt(62)]);
        return sb.toString();
    }

    public static boolean validateChar(char x, String correct){
        Scanner valChar = new Scanner(System.in);
        boolean programRunning = true;
        while(programRunning) {
            for (int i = 0; i < correct.length(); i++) {
                if (x == correct.charAt(i)) {
                    programRunning = false;
                    return true;
                }
            }
            if(programRunning) {
                System.out.println("Invalid input, try again.");
                x = valChar.next().charAt(0);
            }
        }
        return false;
    }

    public static boolean password(String correctPassword){
        boolean correct = false;
        System.out.println("Enter password: ");
        Scanner input = new Scanner(System.in);
        String password = input.nextLine();
        if (password.equals(correctPassword)) correct = true;
        return correct;
    }
}
