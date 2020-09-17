import java.util.ArrayList;
import java.util.Random;



class randomID
{
    static String ID;
    static ArrayList IDCheck = new ArrayList();

    public static String randomizeID(){
        boolean loop = true;
        while(loop) {
            ID = GetBase62(6); // create ID of six, base 62 characters
            if (!IDCheck.contains(ID)) IDCheck.add(ID); loop = false;
        }
        return (String) IDCheck.get(IDCheck.size() -1);
    }

    private static char[] _base62chars ="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    private static Random _random = new Random();

    public static String GetBase62(int length)
    {
        var sb = new StringBuilder(length);
        for (int i=0; i<length; i++)
            sb.append(_base62chars[_random.nextInt(62)]);
        return sb.toString();
    }
}

