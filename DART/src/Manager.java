
import java.time.Year;

import static jdk.internal.org.jline.reader.LineReader.END_OF_LINE;

public class Manager {

    private String ID;
    private int birthyear;
    private String address;
    private double grossSalary;
    private double netSalary;
    private String name;

    Manager(String name, String address, int birthyear, double grossSalary){
        this.name = name;
        this.address = address;
        this.birthyear = birthyear;
        this.grossSalary = grossSalary;
        this.ID = Tools.randomizeID();
    }

    public void setBirthyear(int birthyear){ this.birthyear = birthyear; }
    public int getBirthyear(){ return birthyear; }

    public void setAddress(String address){ this.address = address; }
    public String getAddress(){ return address; }

    public void setGrossSalary(double grossSalary){ this.grossSalary = grossSalary; }
    public double getGrossSalary(){ return grossSalary; }

    public void setNetSalary(double netSalary){ this.netSalary = netSalary; }
    public double getNetSalary(){ return netSalary; }

    public void setID(){ ID = Tools.randomizeID(); }
    public String getID(){ return ID; }

    public String toString(){
        String s = getID() + " " + this.name + " - " + getBirthyear() + " (" + (Year.now().getValue() - getBirthyear()) + " ): " + "Gross salary: " + getGrossSalary() + " SEK";
        if(getNetSalary()!=0) s = s + " Net salary: " + getNetSalary() + " SEK";
        s = s + END_OF_LINE;
        return s;

    }
}