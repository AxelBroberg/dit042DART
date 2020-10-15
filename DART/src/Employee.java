
import java.time.Year;

// Changes because of the feedback we got on the last milestone:
// Moved the ArrayList employeeArrayList to the EmployeeController
// Moved the methods autoRegisterGame, fillGames, registerCustomer, removeCustomer, viewAllCustomer to the EmployeeController
//
// We also added a toString, which we didnt have in the last milestone
//
// We did some modifications to the Constructor so that it doesn't only take a name as a parameter


public class Employee {

    private String ID;
    private int birthyear;
    private String address;
    private double grossSalary;
    private double netSalary;
    private String name;

    Employee(String name, String address, int birthyear, double grossSalary) throws Exception{
        if (name.isEmpty() || name.equals(" ") ){
            throw new NameEmptyException("Empty name is not allowed.");
        } else {
            this.name = name;
        }
        if (grossSalary < 0) {
            throw new NegativeSalaryException("Salary cannot be less than zero.");
        } else {
            this.grossSalary = grossSalary;
        }
        this.address = address;
        this.birthyear = birthyear;
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
        s = s + System.lineSeparator();
        return s;

    }
}