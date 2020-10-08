import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

import static jdk.internal.org.jline.reader.LineReader.END_OF_LINE;

public class Customer {

    private String ID; //creates String variable named 'ID'
    private String name; //creates String variable named 'name'

    Customer(String name){ this.name = name; this.ID = Tools.randomizeID();} //Creates a constructor with takes a name

    public void setID(){ ID = Tools.randomizeID(); } // a method that assigns a random id to the variable 'ID'
    public String getID(){ return ID; } // returns the id created by the method above

    public String toString(){
        return getID() + " : " + this.name + System.lineSeparator();
    }


}
