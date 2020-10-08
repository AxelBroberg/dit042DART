import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

import static jdk.internal.org.jline.reader.LineReader.END_OF_LINE;

public class Customer {


    private String ID; //creates String variable named 'ID'
    private String name; //creates String variable named 'name'
    private String membership;

    Customer(String name){
        this.name = name;
        this.membership = "regular";
        this.ID = Tools.randomizeID();
    } //Creates a constructor with takes a name

    public void setID(){ ID = Tools.randomizeID(); } // a method that assigns a random id to the variable 'ID'
    public String getID(){ return ID; } // returns the id created by the method above
    public String getMembership() { return membership; }



    public void upgradeMembership(){
        switch (membership){
            case "regular" -> membership = "silver";
            case "silver" -> membership = "gold";
            case "gold" -> membership = "platinum";
        }
    }

    public String toString(){
        return getID() + " : " + this.name + ". Membership: " + this.membership + System.lineSeparator();
    }


}
