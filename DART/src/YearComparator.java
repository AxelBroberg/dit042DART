import java.util.Comparator;

public class YearComparator implements Comparator<Rentable>
{
    public int compare(Rentable o1, Rentable o2)
    {
        return compareTo(o1, o2);
    }

    public int compareTo(Rentable o1, Rentable o2) {
        if(o1.getYear() < o2.getYear()){
            return -1;
        } else if(o1.getYear() == o2.getYear()){
            return 0;
        } else {
            return 1;
        }
    }
}