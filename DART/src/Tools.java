import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/*
The UUID suggested for creating unique ID's creates a long string of numbers, letters and dashes.
Such as: "123e4567-e89b-12d3-a456-556642440000"
We found this difficult to read, and hard for the user to input by themselves.
Because of this we created a generator which creates a 6 character long id, which is unique.
*/

/*
https://stackoverflow.com/questions/9543715/generating-human-readable-usable-short-but-unique-ids
 */
public class Tools {
    private static final ArrayList<String> IDCheck = new ArrayList<>(); // Declares an ArrayList holding strings, which will be used to check for duplicates

    public static String randomizeID(){
        boolean loop = true; // Declares a boolean used to exit the while loop
        while(loop) {
            // Initializes a String
            String ID = GetBase62(6); // Assigns the return value of GetBase62(6) to ID
            if (!IDCheck.contains(ID)) IDCheck.add(ID); loop = false;
            // Checks if the ArrayList 'IDCheck' contains the ID generated, if not, it adds it to the list, and then exits the loop
        }
        return IDCheck.get(IDCheck.size() -1); // Returns the last ID added to the ArrayList, in the form of a String
    }

    private static final char[] _base62chars ="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
    // Declares a static char array, containing all numbers and letters

    private static final Random _random = new Random(); // Creates a Random object using the Random class, which is imported

    public static String GetBase62(int length)
    {
        var sb = new StringBuilder(length);
        // Creates a StringBuilder (which is like an arraylist, but for characters?)
        // https://docs.oracle.com/javase/tutorial/java/data/buffers.html
        for (int i=0; i<length; i++)
            sb.append(_base62chars[_random.nextInt(62)]); // Adds a random character from the _base62chars array to the sb variable
        return sb.toString(); // Returns the sb variable as a string
    }

    public static char validateChar(char x, String correct){ //Creates a function that has the purpose to check if the input is correct
        Scanner valChar = new Scanner(System.in); //Creates a new Scanner, which is only used inside of this function
        boolean programRunning = true; //Used so we can exit the entire while loop
        while(programRunning) {
            for (int i = 0; i < correct.length(); i++) { //A for loop that loops the same amount of times as the length of the correct(correctAnswers) string
                if (x == correct.charAt(i)) { //An if statement that is used to check whether the character x(mainMenu) is in the correct(correctAnswers) string
                    programRunning = false;
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
}
