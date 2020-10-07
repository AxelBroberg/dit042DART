import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Employee {

    private String ID; //creates String variable named 'ID'
    private String name; //creates String variable named 'name'

    Employee(String name){ this.name = name; ID = Tools.randomizeID();} //Creates a constructor with takes a name

    public void setID(){ ID = Tools.randomizeID(); } // a method that assigns a random id to the variable 'ID'
    public String getID(){ return ID; } // returns the id created by the method above

    public String toString(){

    }


}
