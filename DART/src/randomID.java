import java.util.ArrayList;
import java.util.Random;
/*
The UUID suggested for creating unique ID's creates a long string of numbers, letters and dashes.
Such as: "123e4567-e89b-12d3-a456-556642440000"
We found this difficult to read, and hard for the user to input by themselves.
Because of this we created a generator which creates a 6 character long id, which is unique.
*/

/*
https://stackoverflow.com/questions/9543715/generating-human-readable-usable-short-but-unique-ids
 */

class randomID
{
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
}

